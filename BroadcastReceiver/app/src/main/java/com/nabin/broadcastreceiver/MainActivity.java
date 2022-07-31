package com.nabin.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String ACTION_CUSTOM_BROADCAST = BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST";

    // Instance of the custom receiver
    private MyCustomReceiver customReceiver = new MyCustomReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Intent Filter to Check the Action that receiver have to receive
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);

        // Register system broadcast receiver
        this.registerReceiver(customReceiver, intentFilter);

        // Register Local broadcast receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(customReceiver, new IntentFilter(ACTION_CUSTOM_BROADCAST));

        findViewById(R.id.sendBroadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent customBroadcastIntent = new Intent(ACTION_CUSTOM_BROADCAST);
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(customBroadcastIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        //Unregister all broadcast receivers
        this.unregisterReceiver(customReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(customReceiver);
        super.onDestroy();
    }
}