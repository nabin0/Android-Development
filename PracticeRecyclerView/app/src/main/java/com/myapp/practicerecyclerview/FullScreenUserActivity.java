package com.myapp.practicerecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class FullScreenUserActivity extends AppCompatActivity {
    TextView username, useraddress;
    ImageView userimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_user);
        username = findViewById(R.id.userName);
        useraddress = findViewById(R.id.userAddress);
        userimage = findViewById(R.id.userImage);
        Bundle bundle = getIntent().getExtras();

        username.setText(bundle.getString("name"));
        useraddress.setText(bundle.getString("address"));
        userimage.setImageResource(bundle.getInt("image"));

    }
}