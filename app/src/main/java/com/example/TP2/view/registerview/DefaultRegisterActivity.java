package com.example.TP2.view.registerview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.entity.RegisterUserRequest;
import com.example.TP2.presenter.registerpresenter.DefaultRegisterPresenter;
import com.example.TP2.presenter.registerpresenter.RegisterPresenter;
import com.example.TP2.view.dollarview.DefaultDollarActivity;
import com.example.TP2.view.loginview.DefaultLoginActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultRegisterActivity extends AppCompatActivity implements RegisterActivity {
    private static final String TAG = "RegisterActivity";
    private static final String[] COMMISSIONS = {"1900", "3900"};
    private static final String ENVIRONMENT = "PROD";

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

        // Calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.register_button:
                    EditText txt_name = (EditText) findViewById(R.id.name_register_text);
                    EditText txt_lastname = (EditText) findViewById(R.id.lastname_register_text);
                    EditText txt_dni = (EditText) findViewById(R.id.dni_register_text);
                    EditText txt_email = (EditText) findViewById(R.id.email_register_text);
                    EditText txt_password = (EditText) findViewById(R.id.password_register_text);
                    Spinner spinner_commission = (Spinner) findViewById(R.id.commission_spinner);
                    EditText txt_group = (EditText) findViewById(R.id.group_register_text);
                    Log.i(TAG, "Se hizo click en register");

                    if (!isValidString("Nombre", txt_name, 1) ||
                            !isValidString("Apellido", txt_lastname, 1) ||
                            !isValidString("DNI", txt_dni, 8) ||
                            !isValidEmail(txt_email) ||
                            !isValidString("Contraseña", txt_password, 8) ||
                            !isValidString("Grupo", txt_group, 1)
                    ) {
                        return;
                    }

                    RegisterUserRequest registerUserRequest = new RegisterUserRequest();
                    registerUserRequest.setEnvironment(ENVIRONMENT);
                    registerUserRequest.setName(txt_name.getText().toString());
                    registerUserRequest.setLastName(txt_lastname.getText().toString());
                    registerUserRequest.setDni(Integer.parseInt(txt_dni.getText().toString()));
                    registerUserRequest.setEmail(txt_email.getText().toString());
                    registerUserRequest.setPassword(txt_password.getText().toString());
                    registerUserRequest.setCommission(Integer.parseInt(spinner_commission.getSelectedItem().toString()));
                    registerUserRequest.setGroup(Integer.parseInt(txt_group.getText().toString()));

                    presenter.onRegisterButtonClick(getApplicationContext(), registerUserRequest);

                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };

    private Boolean isValidString(String field, EditText editText, int minLength) {
        if (TextUtils.isEmpty(editText.getText())) {
            editText.setError("El campo " + field + " no puede estar vacío.");
            return false;
        }

        if (editText.getText().length() < minLength) {
            editText.setError("El campo " + field + "debe tener no menos de " + minLength + " caracteres.");
            return false;
        }

        return true;
    }

    private Boolean isValidEmail(EditText editText) {
        if (TextUtils.isEmpty(editText.getText())) {
            editText.setError("El campo email no puede estar vacío.");
            return false;
        }

        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(editText.getText());
        if (!matcher.find()) {
            editText.setError("El campo email tiene un formato incorrecto");
            return false;
        }

        return true;
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setDollarView() {
        Intent intent = new Intent(this, DefaultDollarActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, DefaultLoginActivity.class);
        startActivity(intent);

        finish();
    }
}