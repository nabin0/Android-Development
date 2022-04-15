package com.myapp.servicesdemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.Random;

public class MyServide extends Service {
    private static final String TAG = "MyTag";
    boolean mIsOn = true;
    private int randomNumber;



    class MyBinder extends Binder {
        public MyServide getService(){
            return MyServide.this;
        }
    }
    private MyBinder mBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: I bind called");

        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Service thread Id : " + Thread.currentThread().getId());
                randomNoGenerator();
            }
        }).start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        turnOff();
        Log.d(TAG, "onDestroy: Service Destroyed");
    }

    private void randomNoGenerator(){
        while(mIsOn){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            randomNumber = new Random().nextInt(100) - 0;
            Log.d(TAG, "randomNoGenerator: " + randomNumber + " Thread id : " + Thread.currentThread().getId());
        }
    }

    void turnOff() {
        mIsOn = false;
    }

    public int getRandomno() {
        return randomNumber;
    }
}
