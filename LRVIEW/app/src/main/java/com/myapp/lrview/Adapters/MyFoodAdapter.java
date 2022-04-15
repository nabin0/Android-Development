package com.myapp.lrview.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.lrview.Interfaces.FoodAdapterInterface;
import com.myapp.lrview.Models.MyFoodModel;
import com.myapp.lrview.R;

import java.util.ArrayList;

public class MyFoodAdapter extends RecyclerView.Adapter<MyFoodAdapter.ViewHolder> {
    ArrayList<MyFoodModel> list;
    Context context;
    private final FoodAdapterInterface foodAdapterInterface;

    public MyFoodAdapter(ArrayList<MyFoodModel> list, Context context, FoodAdapterInterface foodAdapterInterface) {
        this.list = list;
        this.context = context;
        this.foodAdapterInterface = foodAdapterInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_recyclarview, parent, false);
        return new ViewHolder(view, foodAdapterInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyFoodModel model = list.get(position);

        holder.imageView.setImageResource(model.getPic());
        holder.textView.setText(model.getName());

        // One method of using onclick listener to the recycler view
//        holder.imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Item " + model.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView, FoodAdapterInterface foodAdapterInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(foodAdapterInterface != null){
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION){
                            foodAdapterInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


}
