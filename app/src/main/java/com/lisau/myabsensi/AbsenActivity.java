package com.lisau.myabsensi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AbsenActivity extends AppCompatActivity {
    public static TextView textName;
    private Button btnScan;
    private FirebaseUser firebaseUser;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);
        btnScan = findViewById(R.id.btn_scan);
        textName = findViewById(R.id.nama);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();


        if (firebaseUser != null) {
            textName.setText(firebaseUser.getDisplayName());
        } else {
            textName.setText("Login gagal");
        }

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanActivity.class));
            }
        });


    }
}