package com.myapp.threading;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class DownloadThread extends Thread {
    private final MainActivity mainActivity;
    public Handler mhandler;
    private static final String TAG = "DownloadThread";

    public DownloadThread(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void run() {
        Looper.prepare();
        Log.d(TAG, "run: " + currentThread().getId() + " Inside run method ");
        mhandler = new DownloadHandler(mainActivity);
        Looper.loop();
    }

}
