package com.example.TP2.presenter.captchapresenter;

public interface CaptchaPresenter {
    void generateCaptcha();

    void validateCaptcha(int value);
}