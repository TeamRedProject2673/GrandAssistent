package com.example.grandassistent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {
    private TextInputLayout til_correo_l,til_password_l;
    private Button btn_iniciar;
    private TextView tv_olvidar,tv_registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        btn_iniciar = (Button) findViewById(R.id.btn_Iniciar);
        tv_registrar = (TextView) findViewById(R.id.Text_Registrar);
        tv_olvidar = (TextView) findViewById(R.id.textView_Olvidar);
        til_correo_l = (TextInputLayout) findViewById(R.id.txt_Usuario);
        til_password_l = (TextInputLayout) findViewById(R.id.txt_Password);

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrar = new Intent(Login.this,Elegir_Cuenta.class);
                startActivity(registrar);
            }
        });
    }
}