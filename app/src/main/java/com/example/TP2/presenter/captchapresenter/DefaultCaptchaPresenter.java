package com.example.TP2.presenter.captchapresenter;

import android.content.Context;

import com.example.TP2.model.captchamodel.CaptchaModel;
import com.example.TP2.model.captchamodel.DefaultCaptchaModel;
import com.example.TP2.view.captchaactivity.CaptchaActivity;

public class DefaultCaptchaPresenter implements CaptchaModel.OnSendToPresenter, CaptchaPresenter {

    private CaptchaActivity captchaView;
    private final DefaultCaptchaModel model;

    public DefaultCaptchaPresenter(CaptchaActivity captchaView){
        this.captchaView = captchaView;
        this.model = new DefaultCaptchaModel(this);
    }

    @Override
    public void generateCaptcha() {
        model.generateCaptcha();
    }

    @Override
    public void onDestroy() {
        this.captchaView = null;
    }

    @Override
    public void validateCaptcha(int value) {
        model.validateCaptcha(value);
    }

    @Override
    public void getBatteryPercentage(Context ctx) {
        model.getBatteryPercentage(ctx);
    }

    @Override
    public void setCaptcha(int captcha) {
        captchaView.setCaptcha(captcha);
    }

    @Override
    public void onCaptchaError(String message) {
        captchaView.showToast(message);
    }

    @Override
    public void onCaptchaSuccess() {
        captchaView.setLoginView();
    }

    @Override
    public void showBatteryPercentage(String batteryPerc) {
        captchaView.showToast("ESTADO DE LA BATERIA: " + batteryPerc + "%");
    }
}
