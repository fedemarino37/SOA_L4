package com.example.TP2.presenter.dollarpresenter;

import com.example.TP2.model.dollarmodel.DefaultDollarModel;
import com.example.TP2.model.dollarmodel.DollarModel;
import com.example.TP2.view.dollarview.DollarActivity;

public class DefaultDollarPresenter implements DollarModel.OnSendToPresenter, DollarPresenter {

    private DollarModel model;
    private DollarActivity dollarView;

    public DefaultDollarPresenter(DollarActivity dollarView) {
        this.model = new DefaultDollarModel();
        this.dollarView = dollarView;
    }

}
