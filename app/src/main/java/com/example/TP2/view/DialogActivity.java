package com.example.TP2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.TP2.R;

public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button btnCancelar = findViewById(R.id.btnCancelar);
        TextView txtDestino = findViewById(R.id.txtDestino);

        btnCancelar.setOnClickListener(botonesListeners);

        //se crea un objeto Bundle para poder recibir los parametros enviados por la activity Inicio
        //al momeento de ejecutar stratActivity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String texto = extras.getString("textoOrigen");
        txtDestino.setText(texto);

    }

    private final View.OnClickListener botonesListeners = new View.OnClickListener() {
        Intent intent;
        public void onClick(View v) {
            if (v.getId() == R.id.btnCancelar) {
                intent = new Intent(DialogActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Boton Cancelar presionado", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };
}