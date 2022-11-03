package com.example.TP2.presenter.userhistorypresenter;

import android.content.Context;

public interface UserHistoryPresenter {
    void onDestroy();
    void onUserHistoryListUpdate(Context ctx);
}