package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.RegisterUserRequest;
import com.example.TP2.entity.RegisterUserResponse;
import com.example.TP2.repository.exception.HttpBadRequestErrorException;
import com.example.TP2.repository.exception.HttpUnexpectedErrorException;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.exception.SQLUserNotFoundException;

import java.io.IOException;

import io.reactivex.Observable;

public interface RegisterUser {
    void executeCreateSQLUserEntity(Context ctx, String name, String lastName, String email);
    RegisterUserResponse execute(Context ctx, RegisterUserRequest registerUserRequest) throws NetworkConnectionException, IOException, HttpUnexpectedErrorException, HttpBadRequestErrorException, SQLUserNotFoundException;
    Observable<Object> executeWithObservable(Context ctx, RegisterUserRequest registerUserRequest);
}
