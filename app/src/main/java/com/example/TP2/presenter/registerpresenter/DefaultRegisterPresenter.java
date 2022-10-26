package com.example.TP2.presenter.registerpresenter;

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
}
