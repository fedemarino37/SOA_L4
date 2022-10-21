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

    public LoginUser() {
        userRepository = new DefaultUserRepository();
        sharedPreferencesRepository = new DefaultSharedPreferencesRepository();
    }

    public LoginUserResponse execute(Context ctx, LoginUserRequest loginUserRequest) throws NetworkConnectionException, IOException {
        LoginUserResponse loginUserResponse = userRepository.loginUser(ctx, loginUserRequest);
        sharedPreferencesRepository.saveToken(ctx, loginUserResponse.getToken());

        /* Paso el mail al constructor para mantener que el execute solo reciba ctx.
          Ademas, en Model, la funcion trabaja solo con context.    */
        SaveUserLogin saveUserLogin = new SaveUserLogin(loginUserRequest.getEmail());
        saveUserLogin.execute(ctx);

        return loginUserResponse;
    }

    public Observable<LoginUserResponse> executeWithObservable(Context ctx, LoginUserRequest loginUserRequest) {
        return Observable.create(emitter -> {
            emitter.onNext(execute(ctx, loginUserRequest));
        });
    }
}

