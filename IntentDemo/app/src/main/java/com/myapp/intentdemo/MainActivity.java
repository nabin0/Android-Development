package com.myapp.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button gotoweb, call, newActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gotoweb = findViewById(R.id.goToWeb);
        call = findViewById(R.id.call);
        newActivity = findViewById(R.id.newActivity);
        TextView edtxt = findViewById(R.id.edtxt);

        gotoweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( );
                intent.setAction(Intent.ACTION_WEB_SEARCH);
                intent.setData(Uri.parse("http://www.google.com"));
                startActivity(intent);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9350042959"));
                startActivity(intent);
            }
        });

        newActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<>();
                list.add("Noah");
                list.add("Adam");
                list.add("Henry");

                String str = edtxt.getText().toString();
                Bundle b = new Bundle();
                b.putSerializable("names", list);
                b.putString("name", str);
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}