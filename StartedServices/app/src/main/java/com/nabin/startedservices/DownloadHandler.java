package com.nabin.startedservices;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class DownloadHandler extends Handler {
    public static final String TAG = "MyTag";
    public static final String messageKey = "MessageKey";
    private Context mContext;
    private Service mService;

    public DownloadHandler() {
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        download(msg.obj.toString());

        // Stop the service otherwise when app crashes service will again start when restart happens
        mService.stopSelf(msg.arg1);

        sendMessageToUI(msg.obj.toString());

    }

    private void sendMessageToUI(String s) {
        Intent intent = new Intent(messageKey);
        intent.putExtra(MainActivity.DATA_KEY, s);

        // send throught local broadcast receiver
        LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
    }

    private void download(String s){
        Log.d(TAG, "download: Starting on " + Thread.currentThread().getId() + " downloading " + s);
        try {
            Thread.sleep(2000); // Download or other task here
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        
        Log.d(TAG, "download: Terminating on " + Thread.currentThread().getId() + " downloaded " + s);

    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setService(Service mService) {
        this.mService = mService;
    }
}
