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

import javax.annotation.Nonnull;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private Context context;
    private List<User> list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public UserAdapter(Context context, List<User> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_user, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.nim.setText(list.get(position).getNim());
        holder.jurusan.setText(list.get(position).getJurusan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView nim;
        TextView jurusan;

        public MyViewHolder(@Nonnull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            nim = itemView.findViewById(R.id.nim);
            jurusan = itemView.findViewById(R.id.jurusan);
            itemView.setOnClickListener(v -> {
                if (dialog!=null){
                    dialog.onClick(getLayoutPosition());
                }
            });
        }
    }
}