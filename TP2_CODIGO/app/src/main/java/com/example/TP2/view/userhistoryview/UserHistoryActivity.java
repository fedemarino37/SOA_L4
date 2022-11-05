package com.example.TP2.view.userhistoryview;

import com.example.TP2.entity.SQLUserEntity;

import java.util.List;

public interface UserHistoryActivity {
    void showLoading();
    void hideLoading();

    void showToast(String message);

    void loadUserHistoryList(List<SQLUserEntity> userHistoryList);
}
