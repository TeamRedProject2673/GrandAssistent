package com.example.grandassistent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private TextInputLayout til_correo_l, til_password_l;
    private Button btn_iniciar;
    private TextView tv_olvidar, tv_registrar;
    private ProgressDialog mProgressBar;
    FirebaseAuth mAuth;

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

        mAuth = FirebaseAuth.getInstance();
        mProgressBar = new ProgressDialog(Login.this);

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_correo_login = "";
                String s_password_login = "";
                s_correo_login = til_correo_l.getEditText().getText().toString();
                s_password_login = til_password_l.getEditText().getText().toString();
                if(s_correo_login.isEmpty() || !s_correo_login.contains("@")){
                    ShowError(til_correo_l, "Correo no Valido");
                }if(s_password_login.isEmpty() || s_password_login.length() < 6){
                    ShowError(til_password_l,"Contraseña Invalida");
                }else {
                    Ingresar(s_correo_login,s_password_login);
                }
            }
        });

        tv_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrar = new Intent(Login.this,Elegir_Cuenta.class);
                startActivity(registrar);
            }
        });
    }


    public void Ingresar(String p_correo, String p_password){

            mProgressBar.setTitle("Iniciando");
            mProgressBar.setMessage("Iniciando, Espere un Momento.....");
            mProgressBar.setCanceledOnTouchOutside(false);
            mProgressBar.show();
            mAuth.signInWithEmailAndPassword(p_correo, p_password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        mProgressBar.dismiss();
                        FirebaseUser user = mAuth.getCurrentUser();
                        startActivity(new Intent(Login.this,Activity_Inicio_Usuario.class));
                        assert user != null;
                        Toast.makeText(Login.this, "Bienvenido(a) " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        mProgressBar.dismiss();
                        Toast.makeText(Login.this,"No se pudo Iniciar", Toast.LENGTH_SHORT).show();
                        Toast.makeText(Login.this,"Correo y/o Contraseña Incorrectos",Toast.LENGTH_SHORT).show();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    mProgressBar.dismiss();
                    Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });


    }

    //FUNCION DE ERROR
    private void ShowError(TextInputLayout inputEditText, String s){
        inputEditText.setError(s);
        inputEditText.requestFocus();
    }//FIN DE LA FUNCION ERROR

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            startActivity(new Intent(Login.this, Activity_Inicio_Usuario.class));
        }
    }
}