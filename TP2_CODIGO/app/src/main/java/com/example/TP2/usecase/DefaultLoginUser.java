package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.entity.LoginUserResponse;
import com.example.TP2.repository.exception.HttpBadRequestErrorException;
import com.example.TP2.repository.exception.HttpUnexpectedErrorException;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.exception.SQLUserNotFoundException;
import com.example.TP2.repository.rest.DefaultUserRepository;
import com.example.TP2.repository.rest.UserRepository;
import com.example.TP2.repository.sharedpreferences.DefaultSharedPreferencesRepository;
import com.example.TP2.repository.sharedpreferences.SharedPreferencesRepository;

import java.io.IOException;

import io.reactivex.Observable;

public class DefaultLoginUser implements LoginUser {

    private final UserRepository userRepository;
    private final SharedPreferencesRepository sharedPreferencesRepository;
    private final SaveUserLogin saveUserLogin;
    private final RegisterEvent registerEvent;

    public DefaultLoginUser() {
        this.userRepository = new DefaultUserRepository();
        this.sharedPreferencesRepository = new DefaultSharedPreferencesRepository();
        this.saveUserLogin = new DefaultSaveUserLogin();
        this.registerEvent = new DefaultRegisterEvent();
    }

    @Override
    public LoginUserResponse execute(Context ctx, LoginUserRequest loginUserRequest) throws NetworkConnectionException, IOException, HttpUnexpectedErrorException, HttpBadRequestErrorException, SQLUserNotFoundException {
        LoginUserResponse loginUserResponse = userRepository.loginUser(ctx, loginUserRequest);
        if (!loginUserResponse.getSuccess()) {
            throw new HttpUnexpectedErrorException(loginUserResponse.getMsg());
        }

        sharedPreferencesRepository.saveToken(ctx, loginUserResponse.getToken());

        saveUserLogin.execute(ctx, loginUserRequest.getEmail());

        registerEvent.execute(ctx, "login-event", "user " + loginUserRequest.getEmail() + " logged.");

        return loginUserResponse;
    }

    @Override
    public Observable<Object> executeWithObservable(Context ctx, LoginUserRequest loginUserRequest) {
        return Observable.create(emitter -> {
            emitter.onNext(execute(ctx, loginUserRequest));
        });
    }
}

