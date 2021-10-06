package com.myapk.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edittext);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            //creating shared preferences
            SharedPreferences sharedPreferences = getSharedPreferences("mypref", 0);

            @Override
            public void onClick(View view) {
                String val = editText.getText().toString();

                // Shared preferences (setting data )
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", val);
                editor.apply();
                textView.setText(val);
            }
        });

        // Shared preferences (setting data )
        SharedPreferences sharedPreferences = getSharedPreferences("mypref", 0);
        
        // Retrieve data from shared preferences
        String str =  sharedPreferences.getString("name", "No value");
        textView.setText(str);

    }
}