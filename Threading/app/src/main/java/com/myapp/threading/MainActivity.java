package com.myapp.threading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    Handler handler;
    DownloadThread downloadThread;
    private int id;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

//        handler = new Handler(getMainLooper()) {
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                String str = msg.getData().getString(TAG);
//                Toast.makeText(getApplicationContext(), "Download successful !!!" + str, Toast.LENGTH_SHORT).show();
//                Log.d(TAG, "handleMessage: Thread stopped");
//            }
//        };

        downloadThread = new DownloadThread(MainActivity.this);
        downloadThread.setName("thread " + id++);
        downloadThread.start();

    }

    public void startThread(View view) {
        for (String song :
                Playlist.songs) {
            Message msg = Message.obtain();
            msg.obj = song;
            Log.d(TAG, "startThread: " + Thread.currentThread().getId());
            downloadThread.mhandler.sendMessage(msg);
        }
    }

    public void stopThread(View view) {

    }
}