package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.entity.LoginUserResponse;
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

    public LoginUserResponse execute(Context ctx, LoginUserRequest loginUserRequest) throws NetworkConnectionException, IOException {
        LoginUserResponse loginUserResponse = userRepository.loginUser(ctx, loginUserRequest);
        sharedPreferencesRepository.saveToken(ctx, loginUserResponse.getToken());

        saveUserLogin.execute(ctx,loginUserRequest.getEmail());

        return loginUserResponse;
    }

    public Observable<LoginUserResponse> executeWithObservable(Context ctx, LoginUserRequest loginUserRequest) {
        return Observable.create(emitter -> {
            emitter.onNext(execute(ctx, loginUserRequest));
        });
    }
}

