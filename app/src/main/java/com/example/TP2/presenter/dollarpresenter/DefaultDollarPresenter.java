package com.example.TP2.presenter.dollarpresenter;

import android.content.Context;

import com.example.TP2.entity.DollarEntity;
import com.example.TP2.model.dollarmodel.DefaultDollarModel;
import com.example.TP2.model.dollarmodel.DollarModel;
import com.example.TP2.view.dollarview.DollarActivity;

import java.util.List;

public class DefaultDollarPresenter implements DollarModel.OnSendToPresenter, DollarPresenter {

    private DollarModel model;
    private DollarActivity dollarView;

    public DefaultDollarPresenter(DollarActivity dollarView) {
        this.model = new DefaultDollarModel(this);
        this.dollarView = dollarView;
    }

    @Override
    public void onDollarListUpdate(Context ctx) {
        model.getDollarList(ctx);
    }

    @Override
    public void setDollarList(List<DollarEntity> dollarList) {
        dollarView.loadDollarEntityList(dollarList);
    }

    @Override
    public void onError(String message) {
        this.dollarView.showToast(message);
    }
}
