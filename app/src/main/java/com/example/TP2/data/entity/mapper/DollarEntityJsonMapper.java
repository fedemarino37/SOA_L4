package com.example.TP2.data.entity.mapper;

import com.example.TP2.data.entity.DollarEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;

public class DollarEntityJsonMapper {

    private final Gson gson;

    @Inject
    public DollarEntityJsonMapper() {
        this.gson = new Gson();
    }

    public DollarEntity transformDollarEntity(String dollarJsonResponse) throws JsonSyntaxException {
        final Type dollarEntityType = new TypeToken<DollarEntity>() {}.getType();
        return this.gson.fromJson(dollarJsonResponse, dollarEntityType);
    }
}
