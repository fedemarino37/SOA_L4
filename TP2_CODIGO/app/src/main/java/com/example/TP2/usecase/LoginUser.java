package com.example.TP2.usecase;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.entity.LoginUserResponse;
import com.example.TP2.repository.exception.HttpBadRequestErrorException;
import com.example.TP2.repository.exception.HttpUnexpectedErrorException;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.exception.SQLUserNotFoundException;

import java.io.IOException;

import io.reactivex.Observable;

public interface LoginUser {
    LoginUserResponse execute(Context ctx, LoginUserRequest loginUserRequest) throws NetworkConnectionException, IOException, HttpUnexpectedErrorException, HttpBadRequestErrorException, SQLUserNotFoundException;
    Observable<Object> executeWithObservable(Context ctx, LoginUserRequest loginUserRequest);
}
