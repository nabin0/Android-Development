package com.nabin.otpverification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    EditText phoneNo;
    CountryCodePicker countryCodePicker;
    AppCompatButton getOtpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNo = findViewById(R.id.phoneNoEditText);
        countryCodePicker = findViewById(R.id.countryCodePicker);
        countryCodePicker.registerCarrierNumberEditText(phoneNo);
        getOtpBtn = findViewById(R.id.getOtp);

        Toast.makeText(this, phoneNo.getText().toString(), Toast.LENGTH_SHORT).show();

        getOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,countryCodePicker.getFullNumberWithPlus().replace(" ", "").toString() , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ProcessOtpActivity.class);
                intent.putExtra("phone_no", countryCodePicker.getFullNumberWithPlus().replace(" ", ""));
                startActivity(intent);
            }
        });
    }
}