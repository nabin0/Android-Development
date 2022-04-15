package com.myapp.threaddemo;

import android.os.Looper;

public class DownloadThread extends Thread{
    public DownloadHandler mHandler;
    private final MainActivity mainActivity;

    public DownloadThread(MainActivity mainActivity) {
        this.mainActivity = mainActivity ;
    }

    @Override
    public void run() {
        Looper.prepare();
        mHandler = new DownloadHandler(mainActivity);
        Looper.loop();
    }
}
