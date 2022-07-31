package com.nabin.recyclerviewandmenu;

import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<ModelClass> objects;
    private ItemClickListener itemClickListener;

    public CustomAdapter(ArrayList<ModelClass> objects, ItemClickListener itemClickListener) {
        this.objects = objects;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_layout, parent, false);
        return new CustomViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.name.setText(objects.get(position).getName());
        holder.time.setText(objects.get(position).getTime());
        holder.desc.setText(objects.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name, time, desc;
        ItemClickListener itemClickListener;

        public CustomViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            desc = itemView.findViewById(R.id.desc);
            this.itemClickListener = itemClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void onItemClick(int positon);
    }
}
