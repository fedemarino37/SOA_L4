package com.example.TP2.view.captchaactivity;

public interface CaptchaActivity {
    void setLoginView();

    void setCaptcha(int captcha);

    void setErrorMessage(String message);

    void setString(String string);

    void showToast(String message);
}