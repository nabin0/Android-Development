package com.nabin.fragmentsdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText editName, editAddress, editAge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.nameHere);
        editAddress = findViewById(R.id.addressHere);
        editAge = findViewById(R.id.ageHere);
    }

    public void onclickShow(View view) {

        String st = editName.getText().toString();
        String ad = editAddress.getText().toString();
        Integer ag = Integer.valueOf(editAge.getText().toString());

        Employee employee = new Employee(st, ad, ag);
        DemoFragment fragment = DemoFragment.getInstance(employee);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.showFragmentHere, fragment)
                .commit();
    }

    public void onclickhide(View view) {
        DemoFragment fragment = (DemoFragment) getSupportFragmentManager().findFragmentById(R.id.showFragmentHere);

        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(fragment)
                    .commit();
        }
    }
}