package com.example.TP2.model.captchamodel;

public interface CaptchaModel {
    void generateCaptcha();

    void validateCaptcha(int value);

    interface OnSendToPresenter {

        void setCaptcha(int captcha);

        void onCaptchaError(String message);

        void onCaptchaSuccess();
    }
}
