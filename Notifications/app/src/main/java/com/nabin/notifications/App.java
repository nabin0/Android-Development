package com.nabin.notifications;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Notification Channel One",
                    NotificationManager.IMPORTANCE_HIGH);
            channel1.setDescription("This is channel 1 ");

            NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID,
                    "Notification Channel Two",
                    NotificationManager.IMPORTANCE_LOW);
            channel2.setDescription("This is channel 2");


            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
            notificationManager.createNotificationChannel(channel2);

        }
    }
}
