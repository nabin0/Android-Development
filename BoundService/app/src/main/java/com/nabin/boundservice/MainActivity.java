package com.nabin.boundservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nabin.boundservice.Constants.Constants;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyTag";
    private TextView logtext;
    private Button bindBtn, unBindBtn, clearBtn, playBtn, pauseBtn;

    private MusicPlayerService playerService;
    private boolean mMusicServiceConnected = false;
    public final static String MESSAGE_KEY = "messageKey";

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String data = intent.getStringExtra(MESSAGE_KEY);
            if(data == "completed")
                playBtn.setText("play");
        }
    };

    private final ServiceConnection mcon = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicPlayerService.MyMusicBinder binder = (MusicPlayerService.MyMusicBinder) service;
            playerService = binder.getService();
            mMusicServiceConnected = true;

            Log.d(TAG, " Mainactivity service Connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMusicServiceConnected = false;
            Log.d(TAG, "MainActivity service disconnected: ");
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "MainActivity onstart -> binding service and register receiver ");
        Intent intent = new Intent(MainActivity.this, MusicPlayerService.class);
        bindService(intent, mcon, Context.BIND_AUTO_CREATE);

        //Register broadcast receiver for song completion
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(mReceiver, new IntentFilter(MusicPlayerService.SONG_COMPLETED));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logtext = findViewById(R.id.logText);
        bindBtn = findViewById(R.id.btnBind);
        unBindBtn = findViewById(R.id.btnUnbind);
        clearBtn = findViewById(R.id.btnClear);

        playBtn = findViewById(R.id.playBtn);
        pauseBtn = findViewById(R.id.pauseBtn);


        bindBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log(playerService.data());
            }
        });

//        unBindBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mMusicServiceConnected) {
//                    unbindService(mcon);
//                    mMusicServiceConnected = false;
//                    Log.d(TAG, "onClick: unvind called");
//                }
//            }
//        });


        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMusicServiceConnected){
                    if(!playerService.isPlaying()){
                        Intent intent = new Intent(MainActivity.this, MusicPlayerService.class);
                        intent.setAction(Constants.MUSIC_SERVICE_ACTION_START);
                        startService(intent);

                        playerService.play();
                        playBtn.setText("pause");
                    }else{
                        playerService.pause();
                        playBtn.setText("play");
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMusicServiceConnected) {
            unbindService(mcon);
            mMusicServiceConnected = false;
            Log.d(TAG, "MainActivity destroy unbind service");
        }
    }

    public  void log(String  s){
        logtext.append("\n" + s);
    }
}