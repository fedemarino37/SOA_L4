package com.example.TP2.model.dollarmodel;

import android.content.Context;

import com.example.TP2.entity.DollarEntity;

import java.util.List;

public interface DollarModel {

    interface OnSendToPresenter {
        void setDollarList(List<DollarEntity> dollarList);
        void onError(String message);
        void showUSBCableStatus(String msg);
        void onTempChange(String temp);
    }

    void getUSBCableStatus(int plugged);
    void getDollarList(Context ctx);
}
