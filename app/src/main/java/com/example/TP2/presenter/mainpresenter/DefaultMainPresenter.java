package com.example.TP2.presenter.mainpresenter;

import android.content.Context;

import com.example.TP2.model.mainmodel.DefaultMainModel;
import com.example.TP2.model.mainmodel.MainModel;
import com.example.TP2.view.mainview.MainActivity;

public class DefaultMainPresenter implements MainModel.OnSendToPresenter, MainPresenter {

    private MainActivity mainView;
    private final MainModel model;

    public DefaultMainPresenter(MainActivity mainView){
        this.mainView = mainView;
        this.model = new DefaultMainModel();
    }

    @Override
    public void onFinished(String string) {
        this.mainView.setString(string);
    }

    @Override
    public void onButtonClick(Context ctx) {
        this.model.sendMessage(ctx, this);
    }

    @Override
    public void onDestroy() {
        this.mainView = null;
    }
}
