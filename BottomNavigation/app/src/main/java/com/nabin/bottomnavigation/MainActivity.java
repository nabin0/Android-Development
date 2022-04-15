 package com.nabin.bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.nabin.bottomnavigation.Fragments.Frg1;
import com.nabin.bottomnavigation.Fragments.Frg2;
import com.nabin.bottomnavigation.Fragments.Frg3;

 public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, new Frg1()).commit();

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment tempFragment = null;
                switch (item.getItemId()){
                    case R.id.item_home: tempFragment = new Frg1();
                    break;
                    case R.id.item_call: tempFragment = new Frg2();
                    break;
                    case R.id.item_search: tempFragment = new Frg3();
                    break;
                    default: tempFragment = new Frg1();
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, tempFragment).commit();
                return true;
            }
        });
    }
}