package com.lisau.myabsensi;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lisau.myabsensi.adapter.UserAdapter;
import com.lisau.myabsensi.model.User;

import java.util.ArrayList;
import java.util.List;

public class ListDataActivity extends AppCompatActivity {
    private RecyclerView listData;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<User> list = new ArrayList<>();
    private UserAdapter userAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        listData = findViewById(R.id.list_data);

        progressDialog = new ProgressDialog(ListDataActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Mengambil data...");
        userAdapter = new UserAdapter(getApplicationContext(), list);
        userAdapter.setDialog(new UserAdapter.Dialog()

    {
        @Override
        public void onClick( int pos){
        final CharSequence[] dialogItem = {"Edit", "Hapus"};
        AlertDialog.Builder dialog = new AlertDialog.Builder(ListDataActivity.this);
        dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(), AbsenActivity.class);
                        intent.putExtra("id", list.get(pos).getId());
                        intent.putExtra("name", list.get(pos).getName());
                        intent.putExtra("nim", list.get(pos).getNim());
                        intent.putExtra("jurusan", list.get(pos).getJurusan());
                        startActivity(intent);
                        break;
                    case 1:
                        deleteData(list.get(pos).getId());
                        break;
                }
            }
        });
        dialog.show();
        }
    });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        listData.setLayoutManager(layoutManager);
        listData.addItemDecoration(decoration);
        listData.setAdapter(userAdapter);

}

    @Override
    protected void onStart() {
        super.onStart();
        getData();
    }
    private void getData() {
        progressDialog.show();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document: task.getResult()){
                                User user = new  User(document.getString("name"), document.getString("nim"), document.getString("jurusan"));
                                user.setId(document.getId());
                                list.add(user);
                            }
                            userAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(getApplicationContext(), "Data Gagal di ambil", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    private void deleteData(String id){
        progressDialog.show();
        db.collection("users").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Data gagal di hapus", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });
    }
}