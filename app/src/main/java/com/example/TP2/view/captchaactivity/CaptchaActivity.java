package com.example.TP2.view.captchaactivity;

import android.annotation.SuppressLint;

public interface CaptchaActivity {
    void setLoginView();

    void setErrorMessage(String message);

    void setString(String string);
}