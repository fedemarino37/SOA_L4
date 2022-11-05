package com.example.TP2.presenter.userhistorypresenter;

import android.content.Context;

import com.example.TP2.entity.SQLUserEntity;
import com.example.TP2.model.userhistorymodel.DefaultUserHistoryModel;
import com.example.TP2.model.userhistorymodel.UserHistoryModel;
import com.example.TP2.view.userhistoryview.UserHistoryActivity;

import java.util.List;

public class DefaultUserHistoryPresenter implements UserHistoryModel.OnSendToPresenter, UserHistoryPresenter {

    private final UserHistoryActivity userHistoryView;
    private final UserHistoryModel model;

    public DefaultUserHistoryPresenter(UserHistoryActivity userHistoryView){
        this.userHistoryView = userHistoryView;
        this.model = new DefaultUserHistoryModel(this);
    }

    @Override
    public void onUserHistoryListUpdate(Context ctx) {
        userHistoryView.showLoading();
        model.getUserHistoryList(ctx);
    }

    @Override
    public void setUserHistoryList(List<SQLUserEntity> userHistoryList) {
        userHistoryView.loadUserHistoryList(userHistoryList);
        userHistoryView.hideLoading();
    }

    @Override
    public void onError(String message) {
        userHistoryView.hideLoading();
        userHistoryView.showToast(message);
    }
}
