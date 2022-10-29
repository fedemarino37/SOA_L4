package com.example.TP2.view.mainview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.ClassForTest;
import com.example.TP2.R;
import com.example.TP2.entity.LoginUserRequest;
import com.example.TP2.presenter.mainpresenter.DefaultMainPresenter;
import com.example.TP2.presenter.mainpresenter.MainPresenter;
import com.example.TP2.repository.exception.NetworkConnectionException;
import com.example.TP2.repository.sqlite.DefaultSQLUserRepository;
import com.example.TP2.repository.sqlite.SQLUserRepository;
import com.example.TP2.usecase.LoginUser;
import com.example.TP2.view.DialogActivity;

import java.io.IOException;

public class DefaultMainActivity extends AppCompatActivity implements MainActivity {

    private TextView textView;
    private MainPresenter presenter;
    private AlertDialog.Builder builder;
    private static final String TAG = "MainActivity";

    //Todo: Borrar este atributo.
    // Todo: cuando registro un nuevo usuario, se reemplazan los usuarios anteriores con los datos del nuevo usuario.
    ClassForTest userTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        Button btn = findViewById(R.id.deleteTables);
        Button btn_dialog = findViewById(R.id.button_dialog);
        Button btn_alert = findViewById(R.id.alert);

        Button btn_testLogin = findViewById(R.id.testLogin);
        Button btn_testRegister = findViewById(R.id.testRegister);
        Button btn_testShowUserHistory = findViewById(R.id.testShowUserHistory);
        Button btn_testDeleteUser = findViewById(R.id.testShowUSERS);

        userTest = new ClassForTest();


        ImageButton btn_share = findViewById(R.id.share);

        btn.setOnClickListener(btnListener);
        btn_dialog.setOnClickListener(btnListener);
        btn_alert.setOnClickListener(btnListener);
        btn_share.setOnClickListener(btnListener);

        btn_testLogin.setOnClickListener(btnListener);
        btn_testRegister.setOnClickListener(btnListener);
        btn_testShowUserHistory.setOnClickListener(btnListener);
        btn_testDeleteUser.setOnClickListener(btnListener);

        presenter = new DefaultMainPresenter(this);

        builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_start)
                .setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // START THE GAME!
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        builder.create();

        Log.i(TAG, "Paso al estado Created");
    }

    @SuppressLint("Range")
    @Override
    public void setString(String string) {
        textView.setText(string);
    }

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View view) {
            Intent intent;
            switch (view.getId()) {
                case R.id.deleteTables:
                    SQLUserRepository sqlUserRepository = new DefaultSQLUserRepository();
                    sqlUserRepository.deleteTables(getApplicationContext());
                    break;
                case R.id.button_dialog:
                    //se genera un Intent para poder lanzar la activity principal
                    intent = new Intent(DefaultMainActivity.this, DialogActivity.class);

                    //Se le agrega al intent los parametros que se le quieren pasar a la activyt principal
                    //cuando se lanzado
                    intent.putExtra("textoOrigen", textView.getText().toString());

                    //se inicia la activity principal
                    startActivity(intent);

                    finish();

                    break;
                case R.id.alert:
                    builder.show();
                    break;
                case R.id.share:
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                    sendIntent.setType("text/plain");

                    Intent shareIntent = Intent.createChooser(sendIntent, null);
                    startActivity(shareIntent);
                    break;
                case R.id.testLogin:    // Todo: Usar los executes que correspondan.
                    userTest.testLoginWithAPI(getApplicationContext());
                    System.out.println("USUARIO LOGUEADO");
                    break;
                case R.id.testRegister:
                    userTest.testRegisterWithAPI(getApplicationContext());
                    System.out.println("USUARIO REGISTRADO");
                    break;
                case R.id.testShowUserHistory:
                    userTest.testShowUsersHistoryTable(getApplicationContext());
                    break;
                case R.id.testShowUSERS:
                    userTest.testShowUsers(getApplicationContext());
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "Paso al estado Resumed");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "Paso al estado Stopped");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "Paso al estado Paused");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "Paso al estado Restarted");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "Paso al estado Started");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Paso al estado Destroyed");
        presenter.onDestroy();
    }

}