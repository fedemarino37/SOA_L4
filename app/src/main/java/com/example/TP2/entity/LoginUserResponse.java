package com.example.TP2.entity;

import com.google.gson.annotations.SerializedName;

public class LoginUserResponse {

    @SerializedName("success")
    private Boolean success;

    @SerializedName("token")
    private String token;

    @SerializedName("token_refresh")
    private String tokenRefresh;

    @SerializedName("msg")
    private String msg;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenRefresh() {
        return tokenRefresh;
    }

    public void setTokenRefresh(String tokenRefresh) {
        this.tokenRefresh = tokenRefresh;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
