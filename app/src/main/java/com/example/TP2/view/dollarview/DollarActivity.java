package com.example.TP2.view.dollarview;

import com.example.TP2.entity.DollarEntity;

import java.util.List;

public interface DollarActivity {
    void loadDollarEntityList(List<DollarEntity> dollarEntityList);

    void showToast(String message);
    void showLoading();
    void hideLoading();
}
