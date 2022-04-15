package com.myapp.onlineimage;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewAdapter.MyCustomHolder> {
    ArrayList<ImageModel> models;
    Context context;

    public ImageViewAdapter(ArrayList<ImageModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public MyCustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_view, parent, false);
        return new MyCustomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomHolder holder, int position) {
        ImageModel obj = models.get(position);
        holder.textView.setText(obj.getImageTags());
        Picasso.get().load(obj.getImageuri()).placeholder(R.drawable.plcholder).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageFullScreenActivity.class);
                intent.putExtra("uri", obj.getImageuri());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class MyCustomHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyCustomHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.viewImage);
            textView = itemView.findViewById(R.id.imageName);
        }
    }

}
