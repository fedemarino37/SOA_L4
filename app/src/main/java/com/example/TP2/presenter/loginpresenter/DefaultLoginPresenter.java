package com.example.TP2.presenter.loginpresenter;

import android.content.Context;

import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.model.loginmodel.DefaultLoginModel;
import com.example.TP2.model.loginmodel.LoginModel;
import com.example.TP2.view.loginview.LoginActivity;

public class DefaultLoginPresenter implements LoginModel.OnSendToPresenter, LoginPresenter {

    private LoginActivity loginView;
    private LoginModel model;

    public DefaultLoginPresenter(LoginActivity loginView){
        this.loginView = loginView;
        this.model = new DefaultLoginModel(this);
    }

    @Override
    public void onFinished(String string) {
        this.loginView.setString(string);
    }

    @Override
    public void onLoginUserFinished() {
        this.loginView.setDollarView();
    }

    @Override
    public void onLoginError(String message) {
        this.loginView.showToast(message);
    }

    @Override
    public void onRequestStarted() {
        this.loginView.showLoading();
    }

    @Override
    public void onRequestFinished() {
        this.loginView.hideLoading();
    }

    @Override
    public void onLoginButtonClick(Context ctx, LoginUserRequest loginUserRequest) {
        this.model.loginUser(ctx, loginUserRequest);
    }

    @Override
    public void onDestroy() {
        this.loginView = null;
        this.model = null;
    }
}
