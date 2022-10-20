package com.example.TP2.repository;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.entity.LoginUserResponse;
import com.example.TP2.entity.RegisterUserRequest;
import com.example.TP2.entity.RegisterUserResponse;
import com.example.TP2.repository.exception.NetworkConnectionException;

import java.io.IOException;

public interface UserRepository {
    RegisterUserResponse registerUser(Context ctx, RegisterUserRequest registerUserRequest) throws NetworkConnectionException, IOException;
    LoginUserResponse loginUser(Context ctx, LoginUserRequest loginUserRequest) throws NetworkConnectionException, IOException;
}
