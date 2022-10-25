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

import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.presenter.mainpresenter.MainPresenter;
import com.example.TP2.view.registerview.DefaultRegisterActivity;

public class DefaultLoginActivity extends AppCompatActivity implements LoginActivity {

    private TextView textView;
    private MainPresenter presenter;
    private AlertDialog.Builder builder;
    private static final String TAG = "LoginActivity";

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
            Intent intent;
            switch (view.getId()) {
                case R.id.login_button:
                    EditText txt_email = (EditText)findViewById(R.id.email_login_text);
                    EditText txt_password = (EditText)findViewById(R.id.password_login_text);
                    Log.i(TAG, "Se hizo click en login");
                    Log.v("Email", txt_email.getText().toString());
                    Log.v("Contraseña", txt_password.getText().toString());
                    presenter.onButtonClick(getApplicationContext());
                    break;
                case R.id.register_button:
                    Log.i(TAG, "Se hizo click en register");
                    //se genera un Intent para poder lanzar la activity principal
                    intent = new Intent(DefaultLoginActivity.this, DefaultRegisterActivity.class);

                    //Se le agrega al intent los parametros que se le quieren pasar a la activyt principal
                    //cuando se lanzado
                    if (textView != null)
                        intent.putExtra("textoOrigen", textView.getText().toString());

                    //se inicia la activity principal
                    startActivity(intent);

                    finish();
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
}