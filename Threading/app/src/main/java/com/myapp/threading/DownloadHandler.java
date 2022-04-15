package com.myapp.threading;

import android.annotation.SuppressLint;
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
        String song  = msg.obj.toString();
        downloadSong(song);
    }

    private void downloadSong(String songName) {
        Log.d(TAG, "Downloading : " + songName);
        Log.d(TAG, "downloadSong: Thread id " + Thread.currentThread().getId() );
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                mainActivity.textView.setText(songName + " Downloaded");
            }
        });
        Log.d(TAG, "Downloaded " + songName);
    }
}
