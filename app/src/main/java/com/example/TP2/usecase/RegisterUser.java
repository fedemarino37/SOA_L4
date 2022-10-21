package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.RegisterUserRequest;
import com.example.TP2.entity.RegisterUserResponse;
import com.example.TP2.entity.SQLUserEntity;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.rest.DefaultUserRepository;
import com.example.TP2.repository.rest.UserRepository;
import com.example.TP2.repository.sharedpreferences.DefaultSharedPreferencesRepository;
import com.example.TP2.repository.sharedpreferences.SharedPreferencesRepository;

import java.io.IOException;

import io.reactivex.Observable;

public class RegisterUser {

    UserRepository userRepository;
    SharedPreferencesRepository sharedPreferencesRepository;

    public RegisterUser() {
        userRepository = new DefaultUserRepository();
        sharedPreferencesRepository = new DefaultSharedPreferencesRepository();
    }

    public RegisterUserResponse execute(Context ctx, RegisterUserRequest registerUserRequest) throws NetworkConnectionException, IOException {
        RegisterUserResponse registerUserResponse = userRepository.registerUser(ctx, registerUserRequest);
        sharedPreferencesRepository.saveToken(ctx, registerUserResponse.getToken());

        /*
        *   Cuando se registra un nuevo usuario, ademas de enviarlo a la API, se guarda en una tabla
        *       de la base de datos.
        * */

        SQLUserEntity newUser = new SQLUserEntity();
        newUser.setEmail(registerUserRequest.getEmail());
        newUser.setLastName(registerUserRequest.getLastName());
        newUser.setName(registerUserRequest.getName());

        InsertNewUser insertNewUser = new InsertNewUser(newUser);
        insertNewUser.execute(ctx);

        return registerUserResponse;
    }

    public Observable<RegisterUserResponse> executeWithObservable(Context ctx, RegisterUserRequest registerUserRequest) {
        return Observable.create(emitter -> {
            emitter.onNext(execute(ctx, registerUserRequest));
        });
    }
}
