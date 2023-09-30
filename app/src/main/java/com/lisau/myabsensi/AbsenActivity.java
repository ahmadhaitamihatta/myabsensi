package com.lisau.myabsensi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AbsenActivity extends AppCompatActivity {
    private EditText editName, editNim, editJurusan;
    private Button btnSave;
    private ProgressDialog progressDialog;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String id = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absen);
        editName = findViewById(R.id.name);
        editNim = findViewById(R.id.nim);
        editJurusan = findViewById(R.id.jurusan);
        btnSave = findViewById(R.id.btn_save);

        progressDialog = new ProgressDialog(AbsenActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Menyimpan...");


        btnSave.setOnClickListener(v -> {
            if (editName.getText().length() > 0 && editNim.getText().length() > 0 && editJurusan.getText().length() > 0) {
                saveData(editName.getText().toString(), editNim.getText().toString(), editJurusan.getText().toString());
            } else {
                Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getIntent();
        if (intent!=null){
            id = intent.getStringExtra("id");
            editName.setText(intent.getStringExtra("name"));
            editNim.setText(intent.getStringExtra("nim"));
            editJurusan.setText(intent.getStringExtra("jurusan"));
        }
    }

    private void saveData(String name, String nim, String jurusan) {
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("nim", nim);
        user.put("jurusan", jurusan);

        progressDialog.show();
        if (id!=null){
            db.collection("user").document(id)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Berhasil!", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Gagal!", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                        }
                    });
        }else {
            db.collection("user")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Berhasil!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }
}