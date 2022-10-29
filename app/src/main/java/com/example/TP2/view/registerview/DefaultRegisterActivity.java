package com.example.TP2.view.registerview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.entity.RegisterUserRequest;
import com.example.TP2.presenter.registerpresenter.DefaultRegisterPresenter;
import com.example.TP2.presenter.registerpresenter.RegisterPresenter;
import com.example.TP2.view.loginview.DefaultLoginActivity;
import com.example.TP2.view.mainview.DefaultMainActivity;

public class DefaultRegisterActivity extends AppCompatActivity implements RegisterActivity {
    private static final String TAG = "RegisterActivity";
    private static final String[] COMMISSIONS = { "1900", "3900"};
    private static final String ENVIRONMENT = "TEST";

    private final RegisterPresenter presenter;

    public DefaultRegisterActivity() {
        this.presenter = new DefaultRegisterPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn_register = findViewById(R.id.register_button);
        btn_register.setOnClickListener(btnListener);

        Spinner spinner = (Spinner) findViewById(R.id.commission_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, COMMISSIONS);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register_button:
                    EditText txt_name = (EditText)findViewById(R.id.name_register_text);
                    EditText txt_lastname = (EditText)findViewById(R.id.lastname_register_text);
                    EditText txt_dni = (EditText)findViewById(R.id.dni_register_text);
                    EditText txt_email = (EditText)findViewById(R.id.email_register_text);
                    EditText txt_password = (EditText)findViewById(R.id.password_register_text);
                    Spinner spinner_commission = (Spinner)findViewById(R.id.commission_spinner);
                    EditText txt_group = (EditText)findViewById(R.id.group_register_text);
                    Log.i(TAG, "Se hizo click en register");

                    RegisterUserRequest registerUserRequest = new RegisterUserRequest();
                    registerUserRequest.setEnvironment(ENVIRONMENT);
                    registerUserRequest.setName(txt_name.getText().toString());
                    registerUserRequest.setLastName(txt_lastname.getText().toString());
                    registerUserRequest.setDni(Integer.valueOf(txt_dni.getText().toString()));
                    registerUserRequest.setEmail(txt_email.getText().toString());
                    registerUserRequest.setPassword(txt_password.getText().toString());
                    registerUserRequest.setCommission(Integer.valueOf(spinner_commission.getSelectedItem().toString()));
                    registerUserRequest.setGroup(Integer.valueOf(txt_group.getText().toString()));

                    presenter.onRegisterButtonClick(getApplicationContext(), registerUserRequest);

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
    public void setDollarView() {
        //se genera un Intent para poder lanzar la activity principal
        Intent intent = new Intent(this, DefaultMainActivity.class);

        //se inicia la activity principal
        startActivity(intent);

        finish();
    }

    @Override
    public void onBackPressed() {
        //se genera un Intent para poder lanzar la activity principal
        Intent intent = new Intent(this, DefaultLoginActivity.class);

        //se inicia la activity principal
        startActivity(intent);

        finish();
    }
}