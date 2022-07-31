package com.nabin.blog.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.nabin.blog.BlogData.Blog;
import com.nabin.blog.BlogDetailActivity;
import com.nabin.blog.R;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    List<Blog> list;
    Context context;
    private OnItemClickLisener onItemClickLisener;
    public interface OnItemClickLisener{
        void onItemClicked(Blog blog);
    }

    public MainAdapter(List<Blog> list, Context context, OnItemClickLisener clickLisener) {
        this.list = list;
        this.context = context;
        this.onItemClickLisener = clickLisener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new MainViewHolder(view, onItemClickLisener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.bindTo(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView date;
        private ImageView avatar;
        private Blog blog;
        public MainViewHolder(@NonNull View itemView, OnItemClickLisener onItemClickLisener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickLisener.onItemClicked(blog);
                }
            });
            title = itemView.findViewById(R.id.itemTitle);
            date = itemView.findViewById(R.id.date);
            avatar = itemView.findViewById(R.id.avatar);

        }

        void bindTo(Blog blog){
            title.setText(blog.getTitle());
            date.setText(blog.getDate());

            Glide.with(itemView)
                    .load(blog.getAuthor().getAvatarUrl())
                    .transform(new CircleCrop())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(avatar);
        }
    }
}
