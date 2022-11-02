package com.example.TP2.view.registerview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.entity.RegisterUserRequest;
import com.example.TP2.presenter.registerpresenter.DefaultRegisterPresenter;
import com.example.TP2.presenter.registerpresenter.RegisterPresenter;
import com.example.TP2.view.dollarview.DefaultDollarActivity;
import com.example.TP2.view.loginview.DefaultLoginActivity;
import com.example.TP2.view.mainview.DefaultMainActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                    try {
                        registerUserRequest.setName(getValidatedString("Nombre", txt_name.getText().toString(), 1));
                        registerUserRequest.setLastName(getValidatedString("Apellido", txt_lastname.getText().toString(), 1));
                        registerUserRequest.setDni(getValidatedInt("DNI", txt_dni.getText().toString()));
                        registerUserRequest.setEmail(getValidatedEmail(txt_email.getText().toString()));
                        registerUserRequest.setPassword(getValidatedString("Contraseña", txt_password.getText().toString(), 8));
                        registerUserRequest.setCommission(getValidatedInt("Comisión", spinner_commission.getSelectedItem().toString()));
                        registerUserRequest.setGroup(getValidatedInt("Grupo", txt_group.getText().toString()));
                    } catch (Exception e) {
                        setErrorMessage(e.getMessage());
                        break;
                    }

                    presenter.onRegisterButtonClick(getApplicationContext(), registerUserRequest);

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

    public String getValidatedString(String field, String str, int minLength) throws Exception {
        if (str.isEmpty())
            throw new Exception("El field " + field + " no puede estar vacío.");
        if (str.length() < minLength)
            throw new Exception(field + " debe tener más de " + String.valueOf(minLength-1) + " caracteres.");
        return str;
    }

    public String getValidatedEmail(String str) throws Exception {
        if (str.isEmpty())
            throw new Exception("El field email no puede estar vacío.");
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find())
            return str;
        else
            throw new Exception("El field email tiene un formato incorrecto.");
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
    public void setErrorMessage(String message) {
        TextView loginError = findViewById(R.id.register_error_text);
        loginError.setText(message);
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
    public void onBackPressed() {
        setLoginView();
    }
}