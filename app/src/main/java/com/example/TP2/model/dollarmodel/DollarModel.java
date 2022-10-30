package com.example.TP2.model.dollarmodel;

import android.content.Context;

import com.example.TP2.entity.DollarEntity;

import java.util.List;

public interface DollarModel {

    void getDollarList(Context ctx);

    interface OnSendToPresenter {
        void setDollarList(List<DollarEntity> dollarList);
    }
}
