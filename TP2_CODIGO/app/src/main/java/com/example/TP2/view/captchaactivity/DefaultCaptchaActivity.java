package com.example.TP2.view.captchaactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.presenter.captchapresenter.CaptchaPresenter;
import com.example.TP2.presenter.captchapresenter.DefaultCaptchaPresenter;
import com.example.TP2.view.loginview.DefaultLoginActivity;

public class DefaultCaptchaActivity extends AppCompatActivity implements CaptchaActivity {

    private static final String TAG = "CaptchaActivity";

    private final CaptchaPresenter presenter;

    public DefaultCaptchaActivity() {
        this.presenter = new DefaultCaptchaPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha);

        Button btn_captcha = findViewById(R.id.captcha_button);
        btn_captcha.setOnClickListener(btnListener);

        presenter.generateCaptcha();
        presenter.getBatteryPercentage(getApplicationContext());
    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.captcha_button:
                    EditText txt_captcha_login = (EditText)findViewById(R.id.captcha_login_text);

                    presenter.validateCaptcha(Integer.parseInt(txt_captcha_login.getText().toString()));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };

    @Override
    public void setCaptcha(int captcha) {
        TextView captchaText = findViewById(R.id.captcha_text);
        captchaText.setText(String.valueOf(captcha));
    }

    @Override
    public void setLoginView() {
        Intent intent = new Intent(this, DefaultLoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

}