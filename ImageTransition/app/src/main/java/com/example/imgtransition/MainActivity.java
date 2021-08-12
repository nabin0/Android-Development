package com.example.imgtransition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private boolean isImg1 = true;
    public void changeImage(View view){
        ImageView img1 = (ImageView) findViewById(R.id.imageViewImageOne);
        ImageView img2 = (ImageView) findViewById(R.id.imageViewImageTwo);

        if(isImg1){
            isImg1 = false;
            img1.animate().alpha(0).setDuration(2000);
            img2.animate().alpha(1).setDuration(2000);
        }else{
            isImg1 = true;
            img1.animate().alpha(1).setDuration(2000);
            img2.animate().alpha(0).setDuration(2000);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}