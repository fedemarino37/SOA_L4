package com.example.TP2.presenter.userhistorypresenter;

import android.content.Context;

import com.example.TP2.model.mainmodel.DefaultMainModel;
import com.example.TP2.model.mainmodel.MainModel;
import com.example.TP2.presenter.menupresenter.MenuPresenter;
import com.example.TP2.view.menuview.MenuActivity;
import com.example.TP2.view.userhistoryview.UserHistoryActivity;

public class DefaultUserHistoryPresenter implements MainModel.OnSendToPresenter, UserHistoryPresenter {

    private UserHistoryActivity userHistoryView;
    private final MainModel model; // TODO: CAMBIAR

    public DefaultUserHistoryPresenter(UserHistoryActivity userHistoryView){
        this.userHistoryView = userHistoryView;
        this.model = new DefaultMainModel(); // TODO: CAMBIAR
    }

    @Override
    public void onUserHistoryListUpdate(Context ctx) {
        /*model.getUserHistoryList(ctx)*/;
    }

    @Override
    public void onFinished(String string) {
        this.userHistoryView.setString(string);
    }

    @Override
    public void onButtonClick(Context ctx) {
        this.model.sendMessage(ctx, this);
    }

    @Override
    public void onDestroy() {
        this.userHistoryView = null;
    }
}
