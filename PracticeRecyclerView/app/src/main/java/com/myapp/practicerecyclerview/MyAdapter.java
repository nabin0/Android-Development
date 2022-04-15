package com.myapp.practicerecyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    ArrayList<ModelClass> list;
    Context context;

    public MyAdapter(ArrayList<ModelClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_view, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelClass modelClass = list.get(position);
        holder.imageView.setImageResource(modelClass.getmUserImage());
        holder.tvName.setText(modelClass.getmName());
        holder.tvAddress.setText(modelClass.getmAddress());
        
        holder.userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("name", list.get(position).getmName());
                bundle.putString("address",  list.get(position).getmAddress());
                bundle.putInt("image", list.get(position).getmUserImage());

                Intent intent = new Intent(context, FullScreenUserActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImageActivity.class);
                intent.putExtra("image", list.get(position).getmUserImage());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress;
        ImageView imageView;
        LinearLayout userinfo;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textViewName);
            tvAddress = itemView.findViewById(R.id.textViewAddress);
            imageView = itemView.findViewById(R.id.imageViewUserImage);
            userinfo = itemView.findViewById(R.id.userInfo);
        }
    }
}
