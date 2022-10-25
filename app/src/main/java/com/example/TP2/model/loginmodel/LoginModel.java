package com.example.TP2.model.loginmodel;

import android.content.Context;

public interface LoginModel {
    interface OnSendToPresenter{
        void onFinished(String string);
    }

    void sendMessage(Context ctx, OnSendToPresenter presenter);
}
