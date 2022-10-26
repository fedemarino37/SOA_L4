package com.example.TP2.view.loginview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.presenter.loginpresenter.DefaultLoginPresenter;
import com.example.TP2.presenter.loginpresenter.LoginPresenter;
import com.example.TP2.view.mainview.DefaultMainActivity;

public class DefaultLoginActivity extends AppCompatActivity implements LoginActivity {

    private static final String TAG = "LoginActivity";

    private final LoginPresenter presenter;

    private TextView textView;
    private AlertDialog.Builder builder;

    public DefaultLoginActivity() {
        this.presenter = new DefaultLoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView = findViewById(R.id.textView);

        Button btn_login = findViewById(R.id.login_button);
        Button btn_register = findViewById(R.id.register_button);

        btn_login.setOnClickListener(btnListener);
        btn_register.setOnClickListener(btnListener);

    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.login_button:
                    EditText txt_email = (EditText)findViewById(R.id.email_login_text);
                    EditText txt_password = (EditText)findViewById(R.id.password_login_text);
                    Log.i(TAG, "Se hizo click en login");
                    Log.v("Email", txt_email.getText().toString());
                    Log.v("Contraseña", txt_password.getText().toString());

                    LoginUserRequest loginUserRequest = new LoginUserRequest(txt_email.getText().toString(), txt_password.getText().toString());

                    presenter.onLoginButtonClick(getApplicationContext(), loginUserRequest);
                    break;
                case R.id.register_button:
                    Log.i(TAG, "Se hizo click en register");

                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };

    @SuppressLint("Range")
    @Override
    public void setString(String string) {
        textView.setText(string);
    }

    @Override
    public void setDollarView() {
        //se genera un Intent para poder lanzar la activity principal
        Intent intent = new Intent(this, DefaultMainActivity.class);

        //Se le agrega al intent los parametros que se le quieren pasar a la activyt principal
        //cuando se lanzado
        if (textView != null)
            intent.putExtra("textoOrigen", textView.getText().toString());

        //se inicia la activity principal
        startActivity(intent);

        finish();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.loadingPanel).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.loadingPanel).setVisibility(View.GONE);
    }
}