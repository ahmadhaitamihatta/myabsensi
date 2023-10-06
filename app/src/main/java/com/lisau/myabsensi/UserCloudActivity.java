package com.lisau.myabsensi;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lisau.myabsensi.adapter.UserAdapter;
import com.lisau.myabsensi.api.Apiservice;
import com.lisau.myabsensi.api.Apiurl;
import com.lisau.myabsensi.model.User;
import com.lisau.myabsensi.model.Users;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserCloudActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private UserAdapter mAdapter;

    private ArrayList<User> absenArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cloud);

        setTitle("Cloud Daftar Hadir");

        recyclerView = findViewById(R.id.user_cloud);

        absenArrayList = new ArrayList<>();
        mAdapter = new UserAdapter(this, absenArrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));

        getAbsen();
    }

    private void getAbsen(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Apiurl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        Apiservice service = retrofit.create(Apiservice.class);

        Call<Users> call = service.tampilDataAll(Apiurl.API_KEY);

        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {

                assert response.body() != null;

                absenArrayList.clear();

                   // for (int i = 0; i < response.body().getAbsen().size(); i++) {

                    //String id = response.body().getAbsen().get(i).getId_pengguna();

                    //Model m = new Model(idp);

                    // absenArrayList.add(p);
                // }

                mAdapter.notifyDataSetChanged();

                mAdapter = new UserAdapter(getApplicationContext(), response.body().getUserArrayList());

                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

                Log.d("onFailure DCA", t.toString());
            }
        });

    }

}