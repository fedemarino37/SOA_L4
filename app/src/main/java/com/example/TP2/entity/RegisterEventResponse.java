package com.example.TP2.entity;

import com.google.gson.annotations.SerializedName;

public class RegisterEventResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("env")
    private String environment;

    @SerializedName("event")
    private EventEntity event;
}
