package com.nabin.notifications;

import static com.nabin.notifications.App.CHANNEL_1_ID;
import static com.nabin.notifications.App.CHANNEL_2_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private NotificationManagerCompat  notificationManagerCompat;
    private EditText editTextTitle, editTextDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notificationManagerCompat = NotificationManagerCompat.from(this);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDesc = findViewById(R.id.edit_text_desc);
    }

    public void sendOnChannelOne(View view) {

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(editTextTitle.getText().toString())
                .setContentText(editTextDesc.getText().toString())
                .setPriority(NotificationManagerCompat.IMPORTANCE_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(1,notification);
    }

    public void sendOnChannelTwo(View view){
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(editTextTitle.getText().toString())
                .setContentText(editTextDesc.getText().toString())
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        notificationManagerCompat.notify(2,notification);
    }

}