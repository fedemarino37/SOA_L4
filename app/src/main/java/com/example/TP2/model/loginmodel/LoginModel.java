package com.example.TP2.model.loginmodel;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;

public interface LoginModel {

    interface OnSendToPresenter{
        void onLoginUserFinished();
        void onLoginError(String message);
        void onRequestStarted();
        void onRequestFinished();
    }

    void loginUser(Context ctx, LoginUserRequest loginUserRequest);
}
