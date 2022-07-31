package com.nabin.customnotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "channel_1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.music, null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        assert bitmapDrawable != null;
        Bitmap bitmap = bitmapDrawable.getBitmap();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            pendingIntent = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_MUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        }else {
            pendingIntent = PendingIntent.getActivity(this, 100, intent,  PendingIntent.FLAG_UPDATE_CURRENT);

        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel 1", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                .bigPicture(bitmap)
                .bigLargeIcon(bitmap)
                .setBigContentTitle("big title")
                .setSummaryText("big summary text");

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle()
                .addLine("1")
                .addLine("2")
                .addLine("3")
                .addLine("4")
                .addLine("5")
                .addLine("6")
                .addLine("7")
                .addLine("8")
                .setBigContentTitle("THis is also big title")
                .setSummaryText("summary text");

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setLargeIcon(bitmap)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("This is notification ")
                .setContentText("This is message ")
                .setStyle(inboxStyle)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationManagerCompat.IMPORTANCE_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        manager.notify(1, notification);
    }

    public void gotoNextActicity(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}