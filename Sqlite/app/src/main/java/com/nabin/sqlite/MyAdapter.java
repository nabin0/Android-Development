package com.nabin.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nabin.sqlite.models.Contact;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHodler> {
    Context context;
    ArrayList<Contact> list;

    public MyAdapter(Context context, ArrayList<Contact> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        return new MyHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHodler holder, int position) {
        Contact contact = list.get(position);
        holder.phoneno.setText(contact.getPhone_number());
        holder.name.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyHodler extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageView;
        TextView name, phoneno;
        public MyHodler(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rvImage);
            name = itemView.findViewById(R.id.rvName);
            phoneno = itemView.findViewById(R.id.rvPhone);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "pos " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
        }
    }

}
