package com.lisau.myabsensi;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private TextView textName;
    private ImageButton imageLogout;
    private AppCompatButton btnAbsensi;
    private AppCompatButton btnListdata;
    private AppCompatButton btnInfoAplikasi;
    private AppCompatButton btnCatatan;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return true;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textName = findViewById(R.id.nama);
        imageLogout = findViewById(R.id.imageLogout);
        btnAbsensi = findViewById(R.id.btn_absensi);
        btnListdata = findViewById(R.id.btn_listdata);
        btnInfoAplikasi = findViewById(R.id.btn_info);
        btnCatatan = findViewById(R.id.btn_catatan);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser!=null) {
            textName.setText(firebaseUser.getDisplayName());
        }else{
            textName.setText("Login gagal");
        }

        imageLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });

        btnAbsensi.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), AbsenActivity.class));

        });

        btnListdata.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ListDataActivity.class));
        });

        btnInfoAplikasi.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), InfoAplikasiActivity.class));
        });

        btnCatatan.setOnClickListener(v ->  {
            startActivity(new Intent(getApplicationContext(), CatatanActivity.class));
        });
    }
}