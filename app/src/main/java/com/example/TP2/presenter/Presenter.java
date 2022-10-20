package com.example.TP2.presenter;

import android.content.Context;

import com.example.TP2.model.Model;
import com.example.TP2.model.ModelMVP;
import com.example.TP2.view.ViewMVP;

public class Presenter implements ModelMVP.OnSendToPresenter, PresenterMVP{

    private ViewMVP mainView;
    private final ModelMVP model;

    public Presenter(ViewMVP mainView){
        this.mainView = mainView;
        this.model = new Model();
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
