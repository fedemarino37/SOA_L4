package com.example.TP2.entity.mapper;

import com.example.TP2.entity.LoginUserResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class LoginUserResponseJsonMapper {
    private final Gson gson;

    public LoginUserResponseJsonMapper() {
        this.gson = new Gson();
    }

    public LoginUserResponse transformToEntity(String jsonString) throws JsonSyntaxException {
        final Type entityType = new TypeToken<LoginUserResponse>() {}.getType();
        return this.gson.fromJson(jsonString, entityType);
    }
}
