package com.myapp.intentservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyTag";
    public static final String MESSAGE_KEY = "IntetntMessage";
    Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Start invoked", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onClick: Running Download songs in thread " + Thread.currentThread().getId());
                for(String song : MySongs.songs){
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    intent.putExtra(MESSAGE_KEY, song);
                    startService(intent);
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
                Toast.makeText(getApplicationContext(), "Stop invoked", Toast.LENGTH_SHORT).show();
            }
        });
    }

}