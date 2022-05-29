package com.example.grandassistent;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity_Pantalla_Inicio extends AppCompatActivity {
    private Button btn_buscar,btn_datos;

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pantalla_inicio);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color_btn_iniciar)));
        actionBar.setTitle("  Inicio");
        actionBar.setIcon(R.drawable.icono_draw_logo_white);
        actionBar.setDisplayShowHomeEnabled(true);


        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        btn_datos = (Button) findViewById(R.id.btn_Mis_Datos);
        btn_buscar = (Button) findViewById(R.id.btn_Buscar_Asistente);

        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity_Pantalla_Inicio.this,Activity_Pantalla_Asistente.class));
            }
        });

        btn_datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity_Pantalla_Inicio.this,Activity_Inicio_Usuario.class));
                Toast.makeText(MainActivity_Pantalla_Inicio.this,"Mis Datos",Toast.LENGTH_SHORT).show();
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.color_btn_iniciar));
        }
    }//FIN DEL ONCREATE

    @Override
    protected void onStart() {
        Verificacion();
        super.onStart();
    }

    private void Verificacion(){
        if(firebaseUser != null){
            //Toast.makeText(MainActivity_Pantalla_Inicio.this,"Se ha Iniciado Sesion",Toast.LENGTH_SHORT).show();
        }else {
            startActivity(new Intent(MainActivity_Pantalla_Inicio.this,Login.class));
            finish();
        }
    }
}