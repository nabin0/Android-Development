package com.myapk.sqlitedbapk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DbHandler handler = new DbHandler(this, "employee", null, 1);
        handler.addEmployee(new Employee(1, "Jonathon", 33.3));
        handler.getEmployee(1);
        handler.close();
    }
}