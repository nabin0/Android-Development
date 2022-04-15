package com.myapp.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle intent = getIntent().getExtras();
        String data = intent.getString("name");
        @SuppressWarnings("unchecked")
        ArrayList<String> list = (ArrayList<String>) intent.getSerializable("names");
        TextView textView =  findViewById(R.id.textView);
        data += list.get(0);
        data += list.get(1);
        data += list.get(2);
        textView.setText(data);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity2.this, MainActivity3.class);
//                MainActivity2.this.finish();
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);

            }
        });
    }
}