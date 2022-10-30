package com.example.TP2.model.mainmodel;

import android.content.Context;

public interface MainModel {
    interface OnSendToPresenter {
        void onFinished(String string);
    }

    void sendMessage(Context ctx, OnSendToPresenter presenter);
}
