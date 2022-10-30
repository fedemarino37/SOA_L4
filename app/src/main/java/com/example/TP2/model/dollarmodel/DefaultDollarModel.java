package com.example.TP2.model.dollarmodel;

import com.example.TP2.usecase.GetDollarList;

public class DefaultDollarModel implements DollarModel {

    private GetDollarList getDollarList;

    public DefaultDollarModel() {
        this.getDollarList = new GetDollarList();
    }
}
