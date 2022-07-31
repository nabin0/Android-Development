package com.nabin.activitiesandfragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NewActivity extends AppCompatActivity {

    public static final String EXTRA_RES = "resextra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        TextView textView = findViewById(R.id.textView);

        String data = getIntent().getStringExtra(MainActivity.EXTRA_NAME);

        textView.setText(data);

        // Return the result to the parent activity
        Intent resIntent = new Intent();
        resIntent.putExtra(EXTRA_RES,"result sent return");
        setResult(RESULT_OK, resIntent);


    }



    @Override
    protected void onPause() {
        super.onPause();
            // Works here also
//        Intent resIntent = new Intent();
//        resIntent.putExtra(EXTRA_RES,"result sent return");
//        setResult(RESULT_OK, resIntent);
    }



}