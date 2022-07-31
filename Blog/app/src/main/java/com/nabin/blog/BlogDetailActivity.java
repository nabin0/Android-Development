package com.nabin.blog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.google.android.material.snackbar.Snackbar;
import com.nabin.blog.BlogData.Blog;

import java.util.List;

public class BlogDetailActivity extends AppCompatActivity {
    private ImageView mainImage, imageAvatar, imageBack;
    private TextView textTitle, textDate, textAuthorName, textRating, textViews, textDetail;
    private RatingBar ratingBar;
    private ProgressBar progressBar;
    private static String EXTRA_BLOGS = "EXTRA_BLOGS";

//    private static final String BASE_URL = "https://bitbucket.org/dmytrodanylyk/travel-blog-resources/raw" +
//            "/3eede691af3e8ff795bf6d31effb873d484877be";
//
//    private static final String AVATAR_BASE_URL = "https://bitbucket.org/dmytrodanylyk/travel-blog-resources/raw" +
//            "/3eede691af3e8ff795bf6d31effb873d484877be";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_screen_layout);

        initViews();

        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Start Loading Data
        showData(getIntent().getExtras().getParcelable(EXTRA_BLOGS));
    }



    private void showData(Blog blog) {
        progressBar.setVisibility(View.GONE);
        textTitle.setText(blog.getTitle());
        textDate.setText(blog.getDate());
        textDetail.setText(Html.fromHtml(blog.getDescription()));
        textRating.setText(String.valueOf(blog.getRating()));
        ratingBar.setRating(blog.getRating());
        textViews.setText("(" + blog.getViews() + " Views)");
        textAuthorName.setText(blog.getAuthor().getName());

        Glide.with(this)
                .load(blog.getImageUrl())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(mainImage);

        Glide.with(this)
                .load(blog.getAuthor().getAvatarUrl())
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(new CircleCrop())
                .into(imageAvatar);
    }

    private void initViews(){
        mainImage = findViewById(R.id.mainImage);
        imageAvatar = findViewById(R.id.imageAvatar);
        imageBack = findViewById(R.id.imageBack);
        textTitle = findViewById(R.id.textTitle);
        textDate = findViewById(R.id.textDate);
        textAuthorName = findViewById(R.id.textAuthorName);
        textRating = findViewById(R.id.textRatingPoint);
        textViews = findViewById(R.id.textViews);
        ratingBar = findViewById(R.id.ratingBar);
        textDetail = findViewById(R.id.textDetailParagraph);
        progressBar = findViewById(R.id.progressBar);
    }

}
