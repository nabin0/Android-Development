 package com.nabin.servicesinandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

 public class MainActivity extends AppCompatActivity {
    int count = 0;
    private boolean isBound = false;

    private MyBindService myBindService = new MyBindService();

    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBindService.MyLocalBinder localBinder = (MyBindService.MyLocalBinder) service;
            myBindService = localBinder.getServiceInstance();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MyBindService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

     public void increaseCount(View view) {
         TextView textView = (TextView) findViewById(R.id.textView);
         textView.setText(Integer.toString(count++));
     }

     public void startService(View view) {
         Intent intent = new Intent(this, MyStartedService.class);
         startService(intent);
     }


     public void bindService(View view) {
         TextView textView = (TextView) findViewById(R.id.textView);
         textView.setText(myBindService.getSystemTime());
     }
 }