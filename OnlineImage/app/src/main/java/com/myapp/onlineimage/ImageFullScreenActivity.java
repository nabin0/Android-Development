package com.myapp.onlineimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageFullScreenActivity extends AppCompatActivity {
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full_screen);
        getSupportActionBar().hide();
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        Picasso.get().load(intent.getStringExtra("uri")).placeholder(R.drawable.plcholder).into(imageView);
    }
}