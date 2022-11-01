package com.example.TP2.presenter.userhistorypresenter;

import android.content.Context;

import com.example.TP2.entity.RegisterUserRequest;

public interface UserHistoryPresenter {
    void onButtonClick(Context ctx);
    void onDestroy();
    void onUserHistoryListUpdate(Context applicationContext);
}