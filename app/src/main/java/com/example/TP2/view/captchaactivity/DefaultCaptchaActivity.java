package com.example.TP2.view.captchaactivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.presenter.captchapresenter.CaptchaPresenter;
import com.example.TP2.presenter.captchapresenter.DefaultCaptchaPresenter;
import com.example.TP2.view.loginview.DefaultLoginActivity;

public class DefaultCaptchaActivity extends AppCompatActivity implements CaptchaActivity {

    private static final String TAG = "CaptchaActivity";
    private static int maxCaptcha = 999999999;
    private static int minCaptcha = 100000000;
    private int captcha;

    private final CaptchaPresenter presenter;

    private AlertDialog.Builder builder;

    public DefaultCaptchaActivity() {
        this.presenter = new DefaultCaptchaPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha);

        Button btn_captcha = findViewById(R.id.captcha_button);
        btn_captcha.setOnClickListener(btnListener);
        setCaptcha();
    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.captcha_button:
                    EditText txt_captcha_login = (EditText)findViewById(R.id.captcha_login_text);
                    Log.i(TAG, "Se hizo click en login");
                    Log.v("Captcha", String.valueOf(captcha));
                    Log.v("Captcha Login", txt_captcha_login.getText().toString());

                    try {
                        if(captcha != getValidatedInt("captcha", txt_captcha_login.getText().toString())) {
                            setErrorMessage("El captcha no coincide.");
                            setCaptcha();
                            break;
                        }
                    } catch (Exception e) {
                        setErrorMessage(e.getMessage());
                        setCaptcha();
                        break;
                    }
                    setLoginView();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };

    public int getValidatedInt(String field, String strNumber) throws Exception {
        try {
            int number = Integer.valueOf(strNumber);
            return number;
        } catch (NumberFormatException e) {
            throw new Exception("El field " + field + " debe contener números.");
        }
    }

    public void setCaptcha() {
        captcha = (int) Math.floor(Math.random()*(maxCaptcha-minCaptcha+1)+minCaptcha);
        TextView captchaText = findViewById(R.id.captcha_text);
        captchaText.setText(String.valueOf(captcha));
    }

    @Override
    public void setLoginView() {
        //se genera un Intent para poder lanzar la activity principal
        Intent intent = new Intent(this, DefaultLoginActivity.class);

        //se inicia la activity principal
        startActivity(intent);

        finish();
    }

    @Override
    public void setErrorMessage(String message) {
        TextView loginError = findViewById(R.id.captcha_error_text);
        loginError.setText(message);
    }

    @SuppressLint("Range")
    @Override
    public void setString(String string) {}
}