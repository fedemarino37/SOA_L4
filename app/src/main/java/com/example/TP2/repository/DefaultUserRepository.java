package com.example.TP2.repository;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.entity.LoginUserResponse;
import com.example.TP2.entity.RegisterUserRequest;
import com.example.TP2.entity.RegisterUserResponse;
import com.example.TP2.entity.mapper.LoginUserResponseJsonMapper;
import com.example.TP2.entity.mapper.RegisterUserResponseJsonMapper;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.net.DefaultRestClient;
import com.example.TP2.repository.net.RestClient;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class DefaultUserRepository implements UserRepository {
    private static final String BASE_URL = "http://so-unlam.net.ar/api/api";
    private static final String REGISTER_USER = "/register";
    private static final String LOGIN_USER = "/login";

    private final RestClient client;
    private final RegisterUserResponseJsonMapper registerUserResponseJsonMapper;
    private final LoginUserResponseJsonMapper loginUserResponseJsonMapper;

    public DefaultUserRepository() {
        this.client = new DefaultRestClient();
        this.registerUserResponseJsonMapper = new RegisterUserResponseJsonMapper();
        this.loginUserResponseJsonMapper = new LoginUserResponseJsonMapper();
    }

    @Override
    public RegisterUserResponse registerUser(Context ctx, RegisterUserRequest registerUserRequest) throws NetworkConnectionException, IOException {
        Response response = client.post(ctx, BASE_URL+REGISTER_USER, registerUserRequest.toJson(), null);

        //TODO: validate status code
        String stringBody = response.body().string();

        return registerUserResponseJsonMapper.transformToEntity(stringBody);
    }

    @Override
    public LoginUserResponse loginUser(Context ctx, LoginUserRequest loginUserRequest) throws NetworkConnectionException, IOException {
        Response response = client.post(ctx, BASE_URL+LOGIN_USER, loginUserRequest.toJson(), null);

        //TODO: validate status code
        String stringBody = response.body().string();

        return loginUserResponseJsonMapper.transformToEntity(stringBody);
    }
}
