package com.example.TP2.entity.mapper;

import com.example.TP2.entity.DollarEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class DollarEntityJsonMapper {

    private final Gson gson;

    public DollarEntityJsonMapper() {
        this.gson = new Gson();
    }

    public DollarEntity transformToEntity(String jsonString) throws JsonSyntaxException {
        final Type entityType = new TypeToken<DollarEntity>() {}.getType();
        return this.gson.fromJson(jsonString, entityType);
    }
}

