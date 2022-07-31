
package com.nabin.servicesinandroid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyBindService extends Service {
    private static final String TAG = "MYTAG";

    private MyLocalBinder myLocalBinder = new MyLocalBinder();

    public MyBindService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: called");
        return myLocalBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: On BInd created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: on BInd ");
        return super.onStartCommand(intent, flags, startId);
    }

    public String getSystemTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ss:mm:hh dd/mm/yyyy", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    public class MyLocalBinder extends Binder{
        MyBindService getServiceInstance(){
            return MyBindService.this;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MYTAG", "onDestroy: bind called");
    }
}