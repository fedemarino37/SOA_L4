package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.RegisterUserRequest;
import com.example.TP2.entity.RegisterUserResponse;
import com.example.TP2.repository.exception.HttpBadRequestErrorException;
import com.example.TP2.repository.exception.HttpUnexpectedErrorException;
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
    CreateSQLUserEntity createSQLUserEntity;

    public RegisterUser() {
        userRepository = new DefaultUserRepository();
        sharedPreferencesRepository = new DefaultSharedPreferencesRepository();
        createSQLUserEntity = new CreateSQLUserEntity();
    }

    public RegisterUserResponse execute(Context ctx, RegisterUserRequest registerUserRequest) throws NetworkConnectionException, IOException, HttpUnexpectedErrorException, HttpBadRequestErrorException {
        RegisterUserResponse registerUserResponse = userRepository.registerUser(ctx, registerUserRequest);
        sharedPreferencesRepository.saveToken(ctx, registerUserResponse.getToken());

        createSQLUserEntity.execute(ctx,registerUserRequest.getName(),registerUserRequest.getLastName(),registerUserRequest.getEmail());

        return registerUserResponse;
    }

    public void executeCreateSQLUserEntity(Context ctx, String name, String lastName, String email) {
        createSQLUserEntity.execute(ctx,name,lastName,email);
    }

    public Observable<Object> executeWithObservable(Context ctx, RegisterUserRequest registerUserRequest) {
        return Observable.create(emitter -> {
            emitter.onNext(execute(ctx, registerUserRequest));
        });
    }
}
