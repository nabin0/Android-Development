package com.nabin.recyclerviewbestpractice;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomHolder> {

    Context context;
    ArrayList<String> names;
    private OnRvClickListener onRvClickListener;

    public CustomAdapter(Context context, ArrayList<String> names, OnRvClickListener onRvClickListener) {
        this.context = context;
        this.names = names;
        this.onRvClickListener = onRvClickListener;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent,false);
        return new CustomHolder(view, onRvClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
        Log.d("MyTag", "onBindViewHolder: " + position);
        holder.textViewName.setText(names.get(position));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    static class CustomHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewName;
        OnRvClickListener onRvClickListener;
        public CustomHolder(@NonNull View itemView, OnRvClickListener onRvClickListener) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.tvName);

            this.onRvClickListener = onRvClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRvClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnRvClickListener {
        void onItemClick(int pos);
    }
}
