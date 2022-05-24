package com.example.grandassistent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_Recuperar_Pass extends AppCompatActivity {
    private TextInputLayout til_correo_recuperar;
    private Button btn_enviar_correo;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    DatabaseReference mDatabase;
    ProgressDialog mProgressBar;
    String s_recuperar_pass = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_pass);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.color_btn_iniciar)));
        actionBar.setTitle("Recuperar Contraseña");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        mProgressBar = new ProgressDialog(Activity_Recuperar_Pass.this);

        til_correo_recuperar = (TextInputLayout) findViewById(R.id.txt_Enviar_Correo);
        btn_enviar_correo = (Button) findViewById(R.id.btn_Recuperar_Pass);

        btn_enviar_correo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s_recuperar_pass = til_correo_recuperar.getEditText().getText().toString();
                if (!s_recuperar_pass.isEmpty()){
                    mProgressBar.setMessage("Espere un momento");
                    mProgressBar.setCanceledOnTouchOutside(false);
                    mProgressBar.show();
                    Recuperar_Password();
                }else {
                    ShowError(til_correo_recuperar,"Correo Invalido");
                    Toast.makeText(Activity_Recuperar_Pass.this,"Debe Ingresar Su Correo",Toast.LENGTH_SHORT).show();
                }
            }
        });



        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.color_btn_iniciar));
        }
    }

    public void Recuperar_Password(){
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(s_recuperar_pass).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Activity_Recuperar_Pass.this,"Se ha enviado el correo para restablecer contraseña",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Activity_Recuperar_Pass.this,"No se pudo enviar el correo",Toast.LENGTH_SHORT).show();
                }
                mProgressBar.dismiss();
            }
        });
    }

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