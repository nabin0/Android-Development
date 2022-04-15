package com.example.recyclerviewpractice.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewpractice.Models.Item;
import com.example.recyclerviewpractice.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyCostumviewHolder> {
    ArrayList<Item> list = new ArrayList<>();
    Context context;

    public RecyclerViewAdapter(ArrayList<Item> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyCostumviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recyclerview, parent, false);
        return new MyCostumviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCostumviewHolder holder, int position) {

        Item item = list.get(position);
        holder.img.setImageResource(item.getImage());
        holder.name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyCostumviewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;

        public MyCostumviewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgView);
            name = itemView.findViewById(R.id.name);

        }
    }
}
