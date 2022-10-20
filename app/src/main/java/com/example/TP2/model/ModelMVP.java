package com.example.TP2.model;

import android.content.Context;

public interface ModelMVP {
    interface OnSendToPresenter{
        void onFinished(String string);
    }

    void sendMessage(Context ctx, OnSendToPresenter presenter);
}
