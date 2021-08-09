package com.example.logindesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void logInFunctioin(View view){
        TextView username = (TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        Log.i("name", "The name is : "+ username.getText().toString());
        Log.i("password", "The password is : "+ password.getText().toString());
        Toast.makeText(this, "Username: " + username.getText().toString() +" \nPassword: " + password.getText().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}