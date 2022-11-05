package com.example.TP2.repository.rest;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.entity.LoginUserResponse;
import com.example.TP2.entity.RegisterUserRequest;
import com.example.TP2.entity.RegisterUserResponse;
import com.example.TP2.entity.mapper.LoginUserResponseJsonMapper;
import com.example.TP2.entity.mapper.RegisterUserResponseJsonMapper;
import com.example.TP2.repository.exception.HttpBadRequestErrorException;
import com.example.TP2.repository.exception.HttpUnexpectedErrorException;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.rest.restclient.DefaultRestClient;
import com.example.TP2.repository.rest.restclient.RestClient;
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
    public RegisterUserResponse registerUser(Context ctx, RegisterUserRequest registerUserRequest) throws NetworkConnectionException, IOException, HttpUnexpectedErrorException, HttpBadRequestErrorException {
        Response response = client.post(ctx, BASE_URL + REGISTER_USER, registerUserRequest.toJson(), null);
        verifyStatusCode(response);

        String stringBody = response.body().string();

        return registerUserResponseJsonMapper.transformToEntity(stringBody);
    }

    @Override
    public LoginUserResponse loginUser(Context ctx, LoginUserRequest loginUserRequest) throws NetworkConnectionException, IOException, HttpUnexpectedErrorException, HttpBadRequestErrorException {
        Response response = client.post(ctx, BASE_URL + LOGIN_USER, loginUserRequest.toJson(), null);
        verifyStatusCode(response);

        String stringBody = response.body().string();

        return loginUserResponseJsonMapper.transformToEntity(stringBody);
    }

    private void verifyStatusCode(Response response) throws HttpBadRequestErrorException, HttpUnexpectedErrorException {
        if (response.code() >= 400) {
            if (response.code() == 400) {
                throw new HttpBadRequestErrorException();
            }
            throw new HttpUnexpectedErrorException(response.message());
        }
    }
}
