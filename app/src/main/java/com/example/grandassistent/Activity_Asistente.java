package com.example.grandassistent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Activity_Asistente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistente);
        getSupportActionBar().hide();
    }
}