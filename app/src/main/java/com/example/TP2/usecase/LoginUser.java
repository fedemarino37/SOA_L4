package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.entity.LoginUserResponse;
import com.example.TP2.repository.exception.HttpBadRequestErrorException;
import com.example.TP2.repository.exception.HttpUnexpectedErrorException;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.rest.DefaultUserRepository;
import com.example.TP2.repository.rest.UserRepository;
import com.example.TP2.repository.sharedpreferences.DefaultSharedPreferencesRepository;
import com.example.TP2.repository.sharedpreferences.SharedPreferencesRepository;

import java.io.IOException;

import io.reactivex.Observable;

public class LoginUser {

    UserRepository userRepository;
    SharedPreferencesRepository sharedPreferencesRepository;
    SaveUserLogin saveUserLogin;

    public LoginUser() {
        userRepository = new DefaultUserRepository();
        sharedPreferencesRepository = new DefaultSharedPreferencesRepository();
        saveUserLogin = new SaveUserLogin();
    }

    public LoginUserResponse execute(Context ctx, LoginUserRequest loginUserRequest) throws NetworkConnectionException, IOException, HttpUnexpectedErrorException, HttpBadRequestErrorException {
        LoginUserResponse loginUserResponse = userRepository.loginUser(ctx, loginUserRequest);
        if (!loginUserResponse.getSuccess()) {
            throw new HttpUnexpectedErrorException(loginUserResponse.getMsg());
        }

        sharedPreferencesRepository.saveToken(ctx, loginUserResponse.getToken());

        // todo: Hay que verificar que si el usuario no esta en la tabla de users, no pueda entrar.
        saveUserLogin.execute(ctx,loginUserRequest.getEmail());

        return loginUserResponse;
    }

    public Observable<Object> executeWithObservable(Context ctx, LoginUserRequest loginUserRequest) {
        return Observable.create(emitter -> {
            emitter.onNext(execute(ctx, loginUserRequest));
        });
    }
}

