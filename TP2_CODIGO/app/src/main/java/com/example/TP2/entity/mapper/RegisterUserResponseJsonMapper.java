package com.example.TP2.entity.mapper;

import com.example.TP2.entity.RegisterUserResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class RegisterUserResponseJsonMapper {
    private final Gson gson;

    public RegisterUserResponseJsonMapper() {
        this.gson = new Gson();
    }

    public RegisterUserResponse transformToEntity(String jsonString) throws JsonSyntaxException {
        final Type entityType = new TypeToken<RegisterUserResponse>() {}.getType();
        return this.gson.fromJson(jsonString, entityType);
    }
}
