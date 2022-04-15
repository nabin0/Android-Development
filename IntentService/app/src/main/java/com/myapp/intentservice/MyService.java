package com.myapp.intentservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {
    private static final String TAG = "MyTag";

    public MyService() {
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String song = intent.getStringExtra(MainActivity.MESSAGE_KEY);

        new Thread(new Runnable() {
            @Override
            public void run() {

                downloadSong(song);
            }
        }).start();
        return Service.START_REDELIVER_INTENT;
    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: destroyed() called");
        super.onDestroy();
    }

    void downloadSong(String song){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "downloadSong: Downloading song + " + song + " in thread: " + Thread.currentThread().getId());

    }
}
