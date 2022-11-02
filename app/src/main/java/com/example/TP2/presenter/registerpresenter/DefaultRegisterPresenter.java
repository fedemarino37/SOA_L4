package com.example.TP2.presenter.registerpresenter;

import android.content.Context;

import com.example.TP2.entity.RegisterUserRequest;
import com.example.TP2.model.registermodel.DefaultRegisterModel;
import com.example.TP2.model.registermodel.RegisterModel;
import com.example.TP2.view.registerview.RegisterActivity;

public class DefaultRegisterPresenter implements RegisterPresenter, RegisterModel.OnSendToPresenter {

    private RegisterActivity registerView;
    private RegisterModel model;

    public DefaultRegisterPresenter(RegisterActivity registerView) {
        this.registerView = registerView;
        this.model = new DefaultRegisterModel(this);
    }

    @Override
    public void onRegisterButtonClick(Context ctx, RegisterUserRequest registerUserRequest) {
        model.registerUser(ctx, registerUserRequest);
    }

    @Override
    public void onDestroy() {
        this.registerView = null;
        this.model = null;
    }

    @Override
    public void onRegisterError(String message) {
        this.registerView.setErrorMessage(message);
    }

    @Override
    public void onRegisterUserFinished() {
        this.registerView.setLoginView();
    }
}
