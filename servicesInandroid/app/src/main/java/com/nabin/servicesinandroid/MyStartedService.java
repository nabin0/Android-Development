package com.nabin.servicesinandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyStartedService extends Service {
    private static final String TAG = "MyTag";

    public MyStartedService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: Service Created");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: Service Started");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i < 5; i++){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "onStartCommand: Service Executing task : " + i);
                }
            }
        }).start();

        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Service destroyed");
    }
}