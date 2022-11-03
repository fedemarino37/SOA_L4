package com.example.TP2.presenter.captchapresenter;

import android.content.Context;

public interface CaptchaPresenter {
    void generateCaptcha();

    void onDestroy();

    void validateCaptcha(int value);

    void getBatteryPercentage(Context ctx);
}