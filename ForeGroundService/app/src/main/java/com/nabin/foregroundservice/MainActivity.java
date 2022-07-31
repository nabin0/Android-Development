package com.nabin.foregroundservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.edit_text_input);
    }

    public void stopService(View view) {
        Intent intent = new Intent(this, ExampleService.class);
        stopService(intent);
    }

    public void startService(View view) {
        String input = editTextInput.getText().toString();

        Intent intent = new Intent(this, ExampleService.class);
        intent.putExtra("inputExtra", input);
        ContextCompat.startForegroundService(this, intent);
    }

}