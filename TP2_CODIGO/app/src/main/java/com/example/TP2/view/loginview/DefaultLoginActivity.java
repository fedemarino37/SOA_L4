package com.example.TP2.view.loginview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.presenter.loginpresenter.DefaultLoginPresenter;
import com.example.TP2.presenter.loginpresenter.LoginPresenter;
import com.example.TP2.view.dollarview.DefaultDollarActivity;
import com.example.TP2.view.registerview.DefaultRegisterActivity;

public class DefaultLoginActivity extends AppCompatActivity implements LoginActivity {

    private static final String TAG = "LoginActivity";

    private final LoginPresenter presenter;


    public DefaultLoginActivity() {
        this.presenter = new DefaultLoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                    EditText txt_email = (EditText) findViewById(R.id.email_login_text);
                    EditText txt_password = (EditText) findViewById(R.id.password_login_text);
                    Log.i(TAG, "Se hizo click en login");
                    Log.v("Email", txt_email.getText().toString());
                    Log.v("Contraseña", txt_password.getText().toString());

                    LoginUserRequest loginUserRequest = new LoginUserRequest(txt_email.getText().toString(), txt_password.getText().toString());

                    presenter.onLoginButtonClick(getApplicationContext(), loginUserRequest);
                    break;
                case R.id.register_button:
                    Log.i(TAG, "Se hizo click en register");
                    setRegisterView();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };

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


    @Override
    public void requestRegister(String email) {
        View layout = getLayoutInflater().inflate(R.layout.activity_login_not_recognized, null);
        AlertDialog.Builder ADbuilder = new AlertDialog.Builder(this);
        ADbuilder.setMessage("Ingrese su nombre y apellido: ")
                .setView(layout)
                .setTitle("Usuario no registrado en la base de datos")
                .setPositiveButton("Registrar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        final EditText etName = layout.findViewById(R.id.idName);
                        final EditText etLastName = layout.findViewById(R.id.idLastName);

                        presenter.saveSQLUser(getApplicationContext(), email, etName.getText().toString(), etLastName.getText().toString());
                    }
                })
                .setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), "CANCELADO", Toast.LENGTH_SHORT).show();
                    }
                });
        ADbuilder.create();
        ADbuilder.show();
    }

    @Override
    public void setDollarView() {
        Intent intent = new Intent(this, DefaultDollarActivity.class);
        startActivity(intent);

        finish();
    }

    private void setRegisterView() {
        Intent intent = new Intent(this, DefaultRegisterActivity.class);
        startActivity(intent);

        finish();
    }


}