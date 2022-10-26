package com.example.TP2.model.registermodel;

import com.example.TP2.usecase.RegisterUser;

public class DefaultRegisterModel implements RegisterModel {

    RegisterUser registerUser;
    private final OnSendToPresenter presenter;

    public DefaultRegisterModel(OnSendToPresenter presenter) {
        this.presenter = presenter;
        this.registerUser = new RegisterUser();
    }
}
