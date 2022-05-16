package com.example.grandassistent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Elegir_Cuenta extends AppCompatActivity {
    private ImageView imv_asistente,imv_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_cuenta);
        getSupportActionBar().hide();
        imv_asistente = (ImageView) findViewById(R.id.img_Enfermera);
        imv_usuario = (ImageView) findViewById(R.id.img_Usuario);

        imv_asistente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrar_asistente = new Intent(Elegir_Cuenta.this,Activity_Asistente.class);
                startActivity(registrar_asistente);
            }
        });

        imv_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrar_usuario = new Intent(Elegir_Cuenta.this,Activity_Usuario.class);
                startActivity(registrar_usuario);
            }
        });
    }
}