package com.mymusicplayer.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Splash Screen using Thread
//        Thread thread = new Thread(){
//            @Override
//            public void run() {
//                try {
//                    sleep(2000);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//                SplashActivity.this.finish();
//            }
//        };
//        thread.start();

        // Handler For Splash Screen
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(intent);
//                SplashActivity.this.finish();
//            }
//        }, 2000);

    }
}