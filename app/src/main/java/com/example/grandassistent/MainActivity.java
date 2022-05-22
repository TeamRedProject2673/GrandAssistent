package com.example.grandassistent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_inicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        final int Duracion = 2500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent_inicio = new Intent(MainActivity.this,MainActivity_Pantalla_Inicio.class);
                startActivity(intent_inicio);
            }
        },Duracion);
    }
}