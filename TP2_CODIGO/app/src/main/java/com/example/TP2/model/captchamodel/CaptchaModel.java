package com.example.TP2.model.captchamodel;

import android.content.Context;

public interface CaptchaModel {
    void generateCaptcha();

    void validateCaptcha(int value);

    void getBatteryPercentage(Context ctx);

    interface OnSendToPresenter {

        void setCaptcha(int captcha);

        void onCaptchaError(String message);

        void onCaptchaSuccess();

        void showBatteryPercentage(String batteryPerc);
    }
}
