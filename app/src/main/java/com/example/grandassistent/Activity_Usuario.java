package com.example.grandassistent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class Activity_Usuario extends AppCompatActivity {
    private TextInputEditText text_nombre,text_correo,text_telefono,text_password,text_confirmar_pass;
    private Button btn_registrar;
    private ProgressDialog mProgressBar;


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
        text_nombre = (TextInputEditText) findViewById(R.id.text_nombre);
        text_correo = (TextInputEditText) findViewById(R.id.text_correo);
        text_telefono = (TextInputEditText) findViewById(R.id.text_telefono);
        text_password = (TextInputEditText) findViewById(R.id.text_password);
        text_confirmar_pass = (TextInputEditText) findViewById(R.id.text_confirm_pass);
        btn_registrar = (Button) findViewById(R.id.btn_Registrarme);
        getSupportActionBar().hide();
        mProgressBar =new ProgressDialog(Activity_Usuario.this);


        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registrar();
            }
        });
    }//FIN DEL ONCREATE

    public void Registrar(){
        s_nombre = text_nombre.getEditableText().toString();
        s_correo = text_correo.getEditableText().toString();
        s_telefono = text_telefono.getEditableText().toString();
        s_pass = text_password.getEditableText().toString();
        s_confirmar_pass = text_confirmar_pass.getEditableText().toString();
        if(s_nombre.isEmpty() || s_nombre.length() < 4){
            ShowError(text_nombre,"Debe llenar la casilla");
        }if(s_correo.isEmpty() || !s_correo.contains("@")){
            ShowError(text_correo,"Correo Invalido");
        }if(s_telefono.isEmpty() || s_telefono.length() < 10){
            ShowError(text_telefono,"Debe tener 10 digitos");
        }if(s_pass.isEmpty() || s_pass.length() < 5 ){
            ShowError(text_password,"La Contraseña debe tener mas de 5 caracteres");
        }if(s_confirmar_pass.isEmpty() || !s_confirmar_pass.equals(s_pass)){
            ShowError(text_confirmar_pass,"Las Contraseñas no Coinciden");
        }else{
            mProgressBar.setTitle("Procesando Registro");
            mProgressBar.setMessage("Registrando, Espere un Momento....");
            mProgressBar.setCanceledOnTouchOutside(true);
            mProgressBar.show();
        }
    }


    //FUNCION DE ERROR
    private void ShowError(TextInputEditText inputEditText, String s){
        inputEditText.setError(s);
        inputEditText.requestFocus();
    }//FIN DE LA FUNCION ERROR
}



