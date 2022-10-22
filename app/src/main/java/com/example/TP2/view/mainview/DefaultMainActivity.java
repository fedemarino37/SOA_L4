package com.example.TP2.view.mainview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;
import com.example.TP2.presenter.mainpresenter.DefaultMainPresenter;
import com.example.TP2.presenter.mainpresenter.MainPresenter;
import com.example.TP2.view.DialogActivity;
import com.example.TP2.view.dollarview.DefaultDollarActivity;
import com.example.TP2.view.loginview.DefaultLoginActivity;

public class DefaultMainActivity extends AppCompatActivity implements MainActivity {

    private TextView textView;
    private MainPresenter presenter;
    private AlertDialog.Builder builder;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        Button btn = findViewById(R.id.button);
        Button btn_dialog = findViewById(R.id.button_dialog);
        Button btn_alert = findViewById(R.id.alert);
        Button btn_prueba_login = findViewById(R.id.button_prueba_login);
        Button btn_prueba_dolar = findViewById(R.id.button_prueba_dolar);
        ImageButton btn_share = findViewById(R.id.share);

        btn.setOnClickListener(btnListener);
        btn_dialog.setOnClickListener(btnListener);
        btn_alert.setOnClickListener(btnListener);
        btn_share.setOnClickListener(btnListener);
        btn_prueba_login.setOnClickListener(btnListener);
        btn_prueba_dolar.setOnClickListener(btnListener);

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
                case R.id.button:
                    presenter.onButtonClick(getApplicationContext());
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
                case R.id.button_prueba_login:
                    //se genera un Intent para poder lanzar la activity principal
                    intent = new Intent(DefaultMainActivity.this, DefaultLoginActivity.class);

                    //Se le agrega al intent los parametros que se le quieren pasar a la activyt principal
                    //cuando se lanzado
                    intent.putExtra("textoOrigen", textView.getText().toString());

                    //se inicia la activity principal
                    startActivity(intent);

                    finish();

                    break;
                case R.id.button_prueba_dolar:
                    //se genera un Intent para poder lanzar la activity principal
                    intent = new Intent(DefaultMainActivity.this, DefaultDollarActivity.class);

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