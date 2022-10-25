package com.example.TP2.presenter.loginpresenter;
import android.content.Context;

import com.example.TP2.model.loginmodel.DefaultLoginModel;
import com.example.TP2.model.loginmodel.LoginModel;
import com.example.TP2.presenter.loginpresenter.LoginPresenter;
import com.example.TP2.view.loginview.LoginActivity;

public class DefaultLoginPresenter implements LoginModel.OnSendToPresenter, LoginPresenter {

    private LoginActivity loginView;
    private final LoginModel model;

    public DefaultLoginPresenter(LoginActivity loginView){
        this.loginView = loginView;
        this.model = (LoginModel) new DefaultLoginModel();
    }

    @Override
    public void onFinished(String string) {
        this.loginView.setString(string);
    }

    @Override
    public void onButtonClick(Context ctx) {
        this.model.sendMessage(ctx, this);
    }

    @Override
    public void onDestroy() {
        this.loginView = null;
    }
}
