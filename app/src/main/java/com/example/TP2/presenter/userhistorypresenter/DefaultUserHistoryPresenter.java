package com.example.TP2.presenter.userhistorypresenter;

import android.content.Context;

import com.example.TP2.model.userhistorymodel.DefaultUserHistoryModel;
import com.example.TP2.model.userhistorymodel.UserHistoryModel;
import com.example.TP2.view.userhistoryview.UserHistoryActivity;

public class DefaultUserHistoryPresenter implements UserHistoryModel.OnSendToPresenter, UserHistoryPresenter {

    private UserHistoryActivity userHistoryView;
    private final UserHistoryModel model;

    public DefaultUserHistoryPresenter(UserHistoryActivity userHistoryView){
        this.userHistoryView = userHistoryView;
        this.model = new DefaultUserHistoryModel();
    }

    @Override
    public void onUserHistoryListUpdate(Context ctx) {
        /*model.getUserHistoryList(ctx)*/;
    }


    @Override
    public void onButtonClick(Context ctx) {
        //this.model.sendMessage(ctx, this);
    }

    @Override
    public void onDestroy() {
        this.userHistoryView = null;
    }
}
