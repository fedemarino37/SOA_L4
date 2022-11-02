package com.example.TP2.model.userhistorymodel;

import android.content.Context;

import com.example.TP2.entity.SQLUserEntity;

import java.util.List;

public interface UserHistoryModel {
    void getUserHistoryList(Context ctx);

    interface OnSendToPresenter {
        void setUserHistoryList(List<SQLUserEntity> userHistoryList);

        void onError(String message);
    }
}
