package com.example.grandassistent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private Button btn_iniciar;
    private TextView tv_olvidar,tv_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        btn_iniciar = (Button) findViewById(R.id.btn_Iniciar);
        tv_registrar = (TextView) findViewById(R.id.Text_Registrar);

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrar = new Intent(Login.this,Elegir_Cuenta.class);
                startActivity(registrar);
            }
        });
    }
}