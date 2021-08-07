package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView kgVal;
    private TextView poundRes;
    private Button convertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convertBtn = findViewById(R.id.convertBtn);
        kgVal = findViewById(R.id.kgVal);
        poundRes = findViewById(R.id.poundRes);

        convertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = kgVal.getText().toString();
                int kg = Integer.parseInt(s);
                double pound = Math.round(((kg * 2.205f)*100.0)/100.0);
                poundRes.setText("The Pound value is: " + pound);
//                Toast.makeText( getApplicationContext(), "Hello How are you", Toast.LENGTH_SHORT).show();
            }
        });
    }
}