package com.example.TP2.presenter.captchapresenter;

import android.content.Context;

import com.example.TP2.model.mainmodel.DefaultMainModel;
import com.example.TP2.model.mainmodel.MainModel;
import com.example.TP2.view.captchaactivity.CaptchaActivity;

public class DefaultCaptchaPresenter implements MainModel.OnSendToPresenter, CaptchaPresenter {

    private CaptchaActivity captchaView;
    private final MainModel model; // TODO: CAMBIAR

    public DefaultCaptchaPresenter(CaptchaActivity captchaView){
        this.captchaView = captchaView;
        this.model = new DefaultMainModel(); // TODO: CAMBIAR
    }

    @Override
    public void onFinished(String string) {
        this.captchaView.setString(string);
    }

    @Override
    public void onButtonClick(Context ctx) {
        this.model.sendMessage(ctx, this);
    }

    @Override
    public void onDestroy() {
        this.captchaView = null;
    }
}
