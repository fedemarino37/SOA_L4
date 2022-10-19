package com.example.TP2.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.TP2.R;

import butterknife.ButterKnife;

/**
 * Main application screen. This is the app entry point.
 */
public class MainActivity extends BaseActivity {

    //@Bind(R.id.button) Button btn_LoadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(buttonsListeners);
    }

    /**
     * Goes to the user list screen.
     */
    void navigateToDollarList() {
        this.navigator.navigateToDollarList(this);
    }


    private final View.OnClickListener buttonsListeners = new View.OnClickListener() {
        Intent intent;
        public void onClick(View v) {
            if (v.getId() == R.id.button) {
                navigateToDollarList();

                /*intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Boton Cancelar presionado", Toast.LENGTH_SHORT).show();
                finish();*/
            }
        }
    };
}