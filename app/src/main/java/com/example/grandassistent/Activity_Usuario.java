package com.example.grandassistent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Activity_Usuario extends AppCompatActivity {
    private TextInputLayout til_nombre,til_correo,til_telefono,til_pass,til_confirmar_pass;
    private Button btn_registrar;
    private ProgressDialog mProgressBar;
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    //VARIABLES GLOBALES PARA LOS EDIT TEXT
    private String s_nombre = "";
    private String s_correo = "";
    private String s_telefono = "";
    private String s_pass = "";
    private String s_confirmar_pass = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        til_nombre = (TextInputLayout) findViewById(R.id.txt_Nombre);
        til_correo = (TextInputLayout) findViewById(R.id.txt_Correo2);
        til_telefono = (TextInputLayout) findViewById(R.id.txt_Telefono);
        til_pass = (TextInputLayout) findViewById(R.id.txt_Password1);
        til_confirmar_pass = (TextInputLayout) findViewById(R.id.txt_Confirm_Password);
        btn_registrar = (Button) findViewById(R.id.btn_Registrarme);
        getSupportActionBar().hide();
        mProgressBar =new ProgressDialog(Activity_Usuario.this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registrar();
            }
        });
    }//FIN DEL ONCREATE

    public void Registrar(){
        s_nombre = til_nombre.getEditText().getText().toString();
        s_correo = til_correo.getEditText().getText().toString();
        s_telefono = til_telefono.getEditText().getText().toString();
        s_pass = til_pass.getEditText().getText().toString();
        s_confirmar_pass = til_confirmar_pass.getEditText().getText().toString();
        if(s_nombre.isEmpty() || s_nombre.length() < 4){
            ShowError(til_nombre,"Debe llenar la casilla");
        }if(s_correo.isEmpty() || !s_correo.contains("@")){
            ShowError(til_correo,"Correo Invalido");
        }if(s_telefono.isEmpty() || s_telefono.length() < 10){
            ShowError(til_telefono,"Debe tener 10 digitos");
        }if(s_pass.isEmpty() || s_pass.length() < 5 ){
            ShowError(til_pass,"La Contraseña debe tener mas de 5 caracteres");
        }if(s_confirmar_pass.isEmpty() || !s_confirmar_pass.equals(s_pass)){
            ShowError(til_confirmar_pass,"Las Contraseñas no Coinciden");
        }else{
            mProgressBar.setTitle("Procesando Registro");
            mProgressBar.setMessage("Registrando, Espere un Momento....");
            mProgressBar.setCanceledOnTouchOutside(false);
            mProgressBar.show();

            mAuth.createUserWithEmailAndPassword(s_correo,s_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        mProgressBar.dismiss();
                        Map<String,Object> map = new HashMap<>();
                        map.put("Nombre",s_nombre);
                        map.put("Correo",s_correo);
                        map.put("Telefono",s_telefono);
                        map.put("Contraseña",s_pass);
                        map.put("Confirmar",s_confirmar_pass);

                        String id = mAuth.getCurrentUser().getUid();
                        mDatabase.child("Usuarios").child("Clientes").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task2) {
                                if(task2.isSuccessful()){
                                    startActivity(new Intent(Activity_Usuario.this,Activity_Inicio_Usuario.class));
                                    Toast.makeText(Activity_Usuario.this,"Registro Exitoso",Toast.LENGTH_SHORT).show();
                                    finish();
                                }else {
                                    Toast.makeText(Activity_Usuario.this,"No se crearon los datos correctamente",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(getApplicationContext(),"No se pudo Registrar",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    //FUNCION DE ERROR
    private void ShowError(TextInputLayout inputEditText, String s){
        inputEditText.setError(s);
        inputEditText.requestFocus();
    }//FIN DE LA FUNCION ERROR
}



