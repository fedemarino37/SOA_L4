package com.example.TP2.view.registerview;

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
import com.example.TP2.view.loginview.LoginActivity;

public class DefaultRegisterActivity extends AppCompatActivity implements RegisterActivity {

    private TextView textView;
    private MainPresenter presenter;
    private AlertDialog.Builder builder;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn_register = findViewById(R.id.register_button);

        btn_register.setOnClickListener(btnListener);

    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.register_button:
                    EditText txt_name = (EditText)findViewById(R.id.name_register_text);
                    EditText txt_lastname = (EditText)findViewById(R.id.lastname_register_text);
                    EditText txt_dni = (EditText)findViewById(R.id.dni_register_text);
                    EditText txt_email = (EditText)findViewById(R.id.email_register_text);
                    EditText txt_password = (EditText)findViewById(R.id.password_register_text);
                    EditText txt_commission = (EditText)findViewById(R.id.commission_register_text);
                    EditText txt_group = (EditText)findViewById(R.id.group_register_text);
                    Log.i(TAG, "Se hizo click en register");
                    Log.v("Nombre", txt_name.getText().toString());
                    Log.v("Apellido", txt_lastname.getText().toString());
                    Log.v("DNI", txt_dni.getText().toString());
                    Log.v("Email", txt_email.getText().toString());
                    Log.v("Contraseña", txt_password.getText().toString());
                    Log.v("Comisión", txt_commission.getText().toString());
                    Log.v("Grupo", txt_group.getText().toString());
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