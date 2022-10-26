package com.example.TP2.presenter.loginpresenter;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;

public interface LoginPresenter {
    void onLoginButtonClick(Context ctx, LoginUserRequest loginUserRequest);
    void onDestroy();
}
