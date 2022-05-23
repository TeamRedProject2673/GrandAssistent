package com.example.grandassistent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Activity_Asistente extends AppCompatActivity {
    private TextInputLayout til_nombre_a,til_correo_a,til_telefono_a,til_precio_a,til_especializacion_a,til_pass_a,til_confirmar_pass_a;
    private Button btn_registrar_assitente;
    private ProgressDialog mProgressBar;
    FirebaseAuth mAuth;

    private String s_nombre_a = "";
    private String s_correo_a = "";
    private String s_telefono_a = "";
    private String s_precio_a = "";
    private String s_especializacion_a = "";
    private String s_pass_a = "";
    private String s_confirmar_pass_a = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistente);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color_btn_iniciar)));
        actionBar.setTitle("Registrar Asistente");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        til_nombre_a = (TextInputLayout) findViewById(R.id.txt_Nombre_A);
        til_correo_a = (TextInputLayout) findViewById(R.id.txt_Correo_A);
        til_telefono_a = (TextInputLayout) findViewById(R.id.txt_Telefono_A);
        til_precio_a = (TextInputLayout) findViewById(R.id.txt_Precio_A);
        til_especializacion_a = (TextInputLayout) findViewById(R.id.txt_Descripcion_A);
        til_pass_a = (TextInputLayout) findViewById(R.id.txt_Password_A);
        til_confirmar_pass_a = (TextInputLayout) findViewById(R.id.txt_Confirm_Pass_A);
        btn_registrar_assitente = (Button) findViewById(R.id.btn_Registrarme_A);
        mProgressBar = new ProgressDialog(Activity_Asistente.this);
        mAuth = FirebaseAuth.getInstance();

        btn_registrar_assitente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarAsistente();
            }
        });

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.color_btn_iniciar));
        }
    }//FIN DEL ONCREATE

    public void RegistrarAsistente(){
        s_nombre_a = til_nombre_a.getEditText().getText().toString();
        s_correo_a = til_correo_a.getEditText().getText().toString();
        s_telefono_a = til_telefono_a.getEditText().getText().toString();
        s_precio_a = til_precio_a.getEditText().getText().toString();
        s_especializacion_a = til_especializacion_a.getEditText().getText().toString();
        s_pass_a = til_pass_a.getEditText().getText().toString();
        s_confirmar_pass_a = til_confirmar_pass_a.getEditText().getText().toString();

        if(s_nombre_a.isEmpty() || s_nombre_a.length() < 4){
            ShowError(til_nombre_a,"Debe Llenar la Casilla");
        }if(s_correo_a.isEmpty() || !s_correo_a.contains("@")){
            ShowError(til_correo_a,"Correo Invalido");
        }if(s_telefono_a.isEmpty() || s_telefono_a.length() < 10){
            ShowError(til_telefono_a,"Debe tener 10 digitos");
        }if(s_precio_a.isEmpty()){
            ShowError(til_precio_a,"Debe Ingresar la Cantidad");
        }if(s_especializacion_a.isEmpty()){
            ShowError(til_especializacion_a,"Debe Llenar la Casilla");
        }if(s_pass_a.isEmpty() || s_pass_a.length() < 5 ){
            ShowError(til_pass_a,"La Contraseña debe tener mas de 5 caracteres");
        }if(s_confirmar_pass_a.isEmpty() || !s_confirmar_pass_a.equals(s_pass_a)){
            ShowError(til_confirmar_pass_a,"Las Contraseñas no Coinciden");
        }else{
            mProgressBar.setTitle("Procesando Registro");
            mProgressBar.setMessage("Registrando, Espere un Momento....");
            mProgressBar.setCanceledOnTouchOutside(false);
            mProgressBar.show();

            mAuth.createUserWithEmailAndPassword(s_correo_a,s_pass_a).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        mProgressBar.dismiss();
                        FirebaseUser user = mAuth.getCurrentUser();
                        String id_a = user.getUid();
                        HashMap<Object,String> DatosAsistente = new HashMap<>();
                        DatosAsistente.put("UId",id_a);
                        DatosAsistente.put("Nombre",s_nombre_a);
                        DatosAsistente.put("Correo",s_correo_a);
                        DatosAsistente.put("Telefono",s_telefono_a);
                        DatosAsistente.put("Precio_A",s_precio_a);
                        DatosAsistente.put("Especializacion_A",s_especializacion_a);
                        DatosAsistente.put("Contraseña",s_pass_a);
                        DatosAsistente.put("Confirmar_Contraseña",s_confirmar_pass_a);
                        //INICIALIZAR LA INSTANCIA EN LA BASE DE DATOS DE FIREBASE
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        //CREAR BASE DE DATOS
                        DatabaseReference reference = database.getReference("USUARIOS_GRAND_ASSISTENT");
                        reference.child("Asistentes").child(id_a).setValue(DatosAsistente);
                        Toast.makeText(Activity_Asistente.this,"Registro Exitoso",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Activity_Asistente.this,MainActivity_Pantalla_Inicio.class));
                    }else {
                        Toast.makeText(Activity_Asistente.this,"No Se Pudo Registrar",Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Activity_Asistente.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }//FIN DEL METODO REGISTRAR ASISTENTE

    //FUNCION DE ERROR
    private void ShowError(TextInputLayout inputEditText, String s){
        inputEditText.setError(s);
        inputEditText.requestFocus();
    }//FIN DE LA FUNCION ERROR

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}