package com.myapp.threaddemo;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class DownloadHandler extends Handler {

    private static final String TAG = "MyTag";
    private final MainActivity mainActivity;

    public DownloadHandler(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        String song = msg.obj.toString();
        download(song);
    }

    private void download(String songName){
        Log.d(TAG, "runnig in " + Thread.currentThread().getId());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mainActivity.mProgressBar(false);
                mainActivity.log("Download complete");
            }
        });
        Log.d(TAG, "download: Song  downloaded : " + songName);
    }
}
