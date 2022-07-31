package com.nabin.recyclerviewbestpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textView = findViewById(R.id.textView);

        getIncomings();
    }

    void getIncomings(){
        if(getIntent().hasExtra("name")){
            String name = getIntent().getStringExtra("name");
            textView.setText(name);
        }
    }
}