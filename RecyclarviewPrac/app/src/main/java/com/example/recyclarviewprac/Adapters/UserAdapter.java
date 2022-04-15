package com.example.recyclarviewprac.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclarviewprac.Models.UserModel;
import com.example.recyclarviewprac.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MycustoViewHolder> {

    ArrayList<UserModel> list;

    public UserAdapter(ArrayList<UserModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    Context context;

    @NonNull
    @Override
    public MycustoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recyclarview, parent, false);
        return new MycustoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MycustoViewHolder holder, int position) {
        UserModel model = list.get(position);
        holder.textView.setText(model.getName());
        holder.imageView.setImageResource(model.getPicture());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MycustoViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public MycustoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
