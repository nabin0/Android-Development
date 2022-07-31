package com.nabin.startedservices;

import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;

public class DownloadThread extends Thread{
    public DownloadHandler downloadHandler;

    @Override
    public void run() {
        Looper.prepare();
        downloadHandler = new DownloadHandler();
        Looper.loop();
    }
}
