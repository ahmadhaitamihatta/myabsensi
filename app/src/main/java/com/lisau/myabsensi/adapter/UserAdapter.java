package com.lisau.myabsensi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lisau.myabsensi.R;
import com.lisau.myabsensi.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private Context mContext;
    private List<User> userList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        //item dari row_user
        public TextView id, nama, nim, tanggal, status;

        public MyViewHolder(View view) {

            super(view);

            //item dari row_user
            id = view.findViewById(R.id.id_row_user);
            nama = view.findViewById(R.id.nama_row_user);
            nim = view.findViewById(R.id.nim_row_user);
            tanggal = view.findViewById(R.id.tanggal_row_user);
            status = view.findViewById(R.id.status_row_user);
        }
    }

    public UserAdapter(Context context, List<User> userList) {

        mContext = context;

        this.userList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_user, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( final MyViewHolder holder,  final int position) {
        final User user = userList.get(position);
        holder.id.setText(user.getId());
        holder.nama.setText(user.getNama());
        holder.nim.setText(user.getNim());
        holder.tanggal.setText(user.getTanggal());

        if(user.getStatus().equals("1")){

            holder.status.setText("  Hadir  ");

        }else if(user.getStatus().equals("0")){

            holder.status.setText("  Tidak Hadir  ");

        }

    }

    @Override
public int getItemCount() { return userList.size();}
}