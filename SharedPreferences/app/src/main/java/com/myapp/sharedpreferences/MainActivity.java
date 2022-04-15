package com.myapp.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyTag";
    private Button save;
    private TextView showTxt;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.saveBtn);
        showTxt = findViewById(R.id.textView);
        editText = findViewById(R.id.editTextTextPersonName);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();

                SharedPreferences preferences = getSharedPreferences("demo", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                Log.d(TAG, "onClick: " + text);
                editor.putString("data", text);
                editor.apply();
                showTxt.setText(text);
            }
        });

        SharedPreferences preferences = getSharedPreferences("demo", MODE_PRIVATE);
        String str = preferences.getString("data", "null");
        Log.d(TAG, "onCreate: " +str);
        showTxt.setText(str);

    }
}