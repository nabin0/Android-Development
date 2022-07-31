package com.nabin.startedservices;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyTag";
    public static String DATA_KEY = "MYDATA";
    Button button;
    ArrayList<String> list;
    TextView textView;
    Button clearBtn;

//    DownloadThread thread;

    // Broadcast receiver
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            log(intent.getStringExtra(DATA_KEY));
        }
    };

    @Override
    protected void onStart() {
        super.onStart();

        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(broadcastReceiver, new IntentFilter(DownloadHandler.messageKey));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Thread id : " + Thread.currentThread().getId());
        button = findViewById(R.id.button);
        clearBtn = findViewById(R.id.button2);
        textView = findViewById(R.id.textView);

//        thread = new DownloadThread();
//        thread.start();

        list = new ArrayList<>();
        list.add("A song");
        list.add("B song");
        list.add("C song");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "button clicked", Toast.LENGTH_SHORT).show();
                for(String s : list){
                    Intent intent = new Intent(MainActivity.this, MyStartedService.class);
                    intent.putExtra(DATA_KEY, s);
                    startService(intent);

                    // From this activity
//                    Message msg = Message.obtain();
//                    msg.obj = s;
//                    thread.downloadHandler.sendMessage(msg);
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyStartedService.class);
                stopService(intent);
                textView.setText(" ");
            }
        });
    }

        void log(String s){
        textView.append("\n" + s);
        }

}