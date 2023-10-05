package com.lisau.myabsensi;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lisau.myabsensi.adapter.UserAdapter;
import com.lisau.myabsensi.model.Database;
import com.lisau.myabsensi.model.User;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {
    private Database db;
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        setTitle("List Data");

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Tunggu sebentar...");
        progressDialog.show();

        db = new Database(this);

        recyclerView = findViewById(R.id.list_data);

        userArrayList = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new UserAdapter(this, userArrayList);
        tampilData();
        progressDialog.dismiss();
    }

    private void tampilData() {

        userArrayList.clear();

        Cursor c = db.TampilDataAbsen();

        while (c.moveToNext())
        {
            String id = c.getString(0);

            String nim = c.getString(1);

            String nama = c.getString(2);

            String tanggal = c.getString(3);

            String jam = c.getString(4);

            String status = c.getString(5);

            String telat = c.getString(6);

            User p = new User(id, nim, nama, tanggal, jam, status, telat);

            userArrayList.add(p);
        }

        if(!(userArrayList.size() < 1))
        {
            recyclerView.setAdapter(mAdapter);
        }


    }
}