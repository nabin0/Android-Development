package com.newpubgnewstatewalpaper.pubgnewstatewalpapers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    ArrayList<ImageSetModel> list;
    Context context;

    public ImageAdapter(ArrayList<ImageSetModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_recyclerview_model, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        ImageSetModel model = list.get(position);
        holder.imagName.setText(model.getImageName());
        Picasso.get().load(model.getImageUrl()).error(R.drawable.img_placeholder).
                placeholder(R.drawable.img_placeholder).into(holder.imageView);


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.imageView.getContext();
                Intent intent = new Intent(context, ShowImageInFullDisplay.class);
                String uri = model.getImageUrl();
                intent.putExtra("imageUrl", uri);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView imagName;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            imagName = itemView.findViewById(R.id.imageName);
        }
    }
}
