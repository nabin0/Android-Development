package com.myapp.servicesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyTag";
    private Intent serviceIntent;
    
    private MyServide myServide;
    private ServiceConnection serviceConnection;
    private boolean isBind;
    
    Button start, stop, bind, unbind, getRandomNumber;
    TextView tvRandomno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        serviceIntent = new Intent(getApplicationContext(), MyServide.class);
        start =findViewById(R.id.startService);
        stop = findViewById(R.id.endService);
        bind = findViewById(R.id.bind);
        unbind = findViewById(R.id.unbind);
        getRandomNumber = findViewById(R.id.getRandomNo);
        tvRandomno = findViewById(R.id.tvRandom);

        Log.d(TAG, "Main Thread is: " + Thread.currentThread().getId());
        
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(serviceIntent);

            }
        });
        
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(serviceIntent);
            }
        });

        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService();
            }
        });

        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbind();
            }
        });

        getRandomNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRandomNo();
            }
        });
    }
    private void bindService(){
            if(serviceConnection == null){
                serviceConnection = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.d(TAG, "Service connecting");
                        MyServide.MyBinder binder = (MyServide.MyBinder) service;
                        myServide = binder.getService();
                        isBind = true;
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        isBind = false;
                    }
                };
            }
        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbind(){
        if(isBind){
            unbindService(serviceConnection);
            isBind = false;
        }
    }

    private void setRandomNo(){
        if(isBind){
            tvRandomno.setText( " Random no is " +  myServide.getRandomno());
        }else{
            tvRandomno.setText("Service Not bounded");
        }
    }

}