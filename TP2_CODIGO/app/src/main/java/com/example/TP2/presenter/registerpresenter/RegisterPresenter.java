package com.example.TP2.presenter.registerpresenter;

import android.content.Context;

import com.example.TP2.entity.RegisterUserRequest;

public interface RegisterPresenter {
    void onRegisterButtonClick(Context ctx, RegisterUserRequest registerUserRequest);
}
