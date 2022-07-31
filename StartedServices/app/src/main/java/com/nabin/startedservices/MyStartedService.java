package com.nabin.startedservices;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyStartedService extends Service {
    private static final String TAG = "MyTag";
    DownloadThread downloadThread;

    @Override
    public void onCreate() {
        super.onCreate();
        downloadThread = new DownloadThread();
        downloadThread.start();

        while (downloadThread.downloadHandler == null){

        }
        downloadThread.downloadHandler.setService(this);
        downloadThread.downloadHandler.setContext(getApplicationContext());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: Started onstart command");
        final String data = intent.getStringExtra(MainActivity.DATA_KEY);

        Message message = Message.obtain();
        message.obj = data;
        message.arg1 = startId;
        downloadThread.downloadHandler.sendMessage(message);
        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: destroyed service");
    }
}
