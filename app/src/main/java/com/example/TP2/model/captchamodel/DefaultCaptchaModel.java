package com.example.TP2.model.captchamodel;

public class DefaultCaptchaModel implements CaptchaModel{

    private static int minValue = 100000000;
    private static int maxValue = 999999999;
    private int captcha;

    private final OnSendToPresenter presenter;

    public DefaultCaptchaModel(OnSendToPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void generateCaptcha() {
        captcha = (int) Math.floor(Math.random()*(maxValue-minValue+1)+minValue);

        presenter.setCaptcha(captcha);
    }

    @Override
    public void validateCaptcha(int value) {
        if (value != captcha) {
            presenter.onCaptchaError("El captcha no coincide");
            generateCaptcha();
            return;
        }

        presenter.onCaptchaSuccess();
    }
}
