package com.nabin.boundservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.nabin.boundservice.Constants.Constants;

public class MusicPlayerService extends Service {
    private static final String TAG = "MyTag";
    private final Binder binder = new MyMusicBinder();
    private  MediaPlayer mplayer;
    public final static String SONG_COMPLETED = "songCompleted";

    public MusicPlayerService() {
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "MusicPlayer oncreate ");
        super.onCreate();
        mplayer = MediaPlayer.create(this, R.raw.song1);
        mplayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Intent intent = new Intent(SONG_COMPLETED);
                intent.putExtra(MainActivity.MESSAGE_KEY, "completed");

                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

                stopSelf();
            }
        });
    }

    public class MyMusicBinder extends Binder{
        MusicPlayerService getService(){
            return MusicPlayerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "Onbind of Musicplayer: ");
       return binder;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        switch (intent.getAction()){

            case Constants.MUSIC_SERVICE_ACTION_PLAY:{
                Log.d(TAG, "onStartCommand: play action");
                play();
                break;
            }
            case Constants.MUSIC_SERVICE_ACTION_PAUSE:{
                Log.d(TAG, "onStartCommand: pause action ");
                pause();
                break;
            }case Constants.MUSIC_SERVICE_ACTION_STOP:{
                Log.d(TAG, "onStartCommand: stop action");

                stopForeground(true);
                stopSelf();
            }
            case Constants.MUSIC_SERVICE_ACTION_START:{
                Log.d(TAG, "onStartCommand: show action");
                showNotfication();
                break;
            }
            default:
                stopSelf();
        }


        Log.d(TAG, "Musicplayer onStartCommand: ");
        return START_NOT_STICKY;
    }

    public void showNotfication(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("channelId", "my notification channel"
            , NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"channelId");

        //Intent for play action
         Intent pIntent = new Intent(this, MusicPlayerService.class);
         pIntent.setAction(Constants.MUSIC_SERVICE_ACTION_PLAY);

        PendingIntent playIntent = PendingIntent.getService(this, 100, pIntent, 0);


        //Intent for play action
         Intent psIntent = new Intent(this, MusicPlayerService.class);
         pIntent.setAction(Constants.MUSIC_SERVICE_ACTION_PAUSE);

        PendingIntent pauseIntent = PendingIntent.getService(this, 100, psIntent, 0);


        //Intent for play action
         Intent sIntent = new Intent(this, MusicPlayerService.class);
         pIntent.setAction(Constants.MUSIC_SERVICE_ACTION_STOP);

        PendingIntent stopIntent = PendingIntent.getService(this, 100, sIntent, 0);

        builder.setContentTitle("Bound service MusicPlayer")
                .setContentText("demo musicplayer")
                .setSmallIcon(R.mipmap.ic_launcher)
                .addAction(R.drawable.ic_baseline_play_arrow_24,"play", playIntent)
                .addAction(R.drawable.ic_baseline_play_arrow_24,"pause", pauseIntent)
                .addAction(R.drawable.ic_baseline_play_arrow_24,"stop", stopIntent);

        startForeground(113, builder.build());
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "Musicplayer onUnbind: " );
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "onRebind: Musicpplayer");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Musicplayer onDestroy: " );
        super.onDestroy();
        if(mplayer.isPlaying()){
            mplayer.stop();
        }
        mplayer.release();
    }

    // Public client methods
    public boolean isPlaying(){
        return mplayer.isPlaying();
    }

    public void play(){
        mplayer.start();
    }

    public void pause(){
        mplayer.pause();
    }

    public String data(){
        return  "data";
    }
}