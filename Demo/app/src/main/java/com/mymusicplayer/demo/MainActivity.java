package com.mymusicplayer.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView f,fw,s,sw,t,tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        f = findViewById(R.id.tvFirst);
        fw = findViewById(R.id.tvFirstWay);
        s = findViewById(R.id.second);
        sw = findViewById(R.id.secondWay);

        f.setOnClickListener(this);
        fw.setOnClickListener(this);

        // usning set Onclicklistener
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Second ", Toast.LENGTH_SHORT).show();
            }
        });
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Second way ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tvFirst:
                Toast.makeText(this, "Using Interface", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvFirstWay:
                Toast.makeText(this, "First Way of implementation", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void third(View view) {
        Toast.makeText(this, "Third", Toast.LENGTH_SHORT).show();
    }
    public void thirdWay(View v){
        Toast.makeText(this, "third view", Toast.LENGTH_SHORT).show();
    }
}