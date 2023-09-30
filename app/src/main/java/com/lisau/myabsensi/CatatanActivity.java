package com.lisau.myabsensi;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

public class CatatanActivity extends AppCompatActivity {
    private AppCompatEditText inpCatatan;
    private AppCompatButton btnDisimpan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catatan);
        inpCatatan = findViewById(R.id.inp_catatan);
        btnDisimpan = findViewById(R.id.btn_catatan);

    }
}