package com.example.TP2.presenter.captchapresenter;

public interface CaptchaPresenter {
    void generateCaptcha();

    void onDestroy();

    void validateCaptcha(int value);
}