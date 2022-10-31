package com.example.TP2.model.loginmodel;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;

public interface LoginModel {

    void registerNewUser(Context ctx, String email, String name, String lastName);

//    void loginNewUser(Context ctx, String email);

    interface OnSendToPresenter{
        void onLoginUserFinished();
        void onLoginError(String message);
        void onRequestStarted();
        void onRequestFinished();

        void onSQLError(String email);
    }

    void loginUser(Context ctx, LoginUserRequest loginUserRequest);
}
