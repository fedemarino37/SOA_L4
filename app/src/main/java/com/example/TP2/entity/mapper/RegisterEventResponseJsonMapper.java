package com.example.TP2.entity.mapper;

import com.example.TP2.entity.RegisterEventResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class RegisterEventResponseJsonMapper {
    private final Gson gson;

    public RegisterEventResponseJsonMapper() {
        this.gson = new Gson();
    }

    public RegisterEventResponse transformToEntity(String jsonString) throws JsonSyntaxException {
        final Type entityType = new TypeToken<RegisterEventResponse>() {}.getType();
        return this.gson.fromJson(jsonString, entityType);
    }
}
