package com.example.TP2.model.registermodel;

import android.content.Context;

import com.example.TP2.entity.RegisterUserRequest;

public interface RegisterModel {
    interface OnSendToPresenter {
        void onRegisterError(String message);

        void onRegisterUserFinished();
    }

    void registerUser(Context ctx, RegisterUserRequest registerUserRequest);
}
