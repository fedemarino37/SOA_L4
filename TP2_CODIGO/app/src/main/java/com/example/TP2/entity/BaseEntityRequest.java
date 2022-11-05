package com.example.TP2.entity;

import com.google.gson.Gson;

public class BaseEntityRequest {
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
