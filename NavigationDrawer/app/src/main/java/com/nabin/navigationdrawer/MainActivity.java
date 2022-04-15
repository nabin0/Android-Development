package com.nabin.navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportFragmentManager().beginTransaction().add(R.id.framecontainer, new HomeFragment()).commit();

        navigationView = findViewById(R.id.navigation_menu);
        drawerLayout = findViewById(R.id.drawer_layout);

        // Toggle drawer using action bar
        toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment tempFragment = null;
                int itemId = item.getItemId();

                if(R.id.item_home == itemId){
                    tempFragment = new HomeFragment();
                }else if(R.id.item_setting == itemId){
                    tempFragment = new SettingFragment();
                }else if(R.id.item_call == itemId){
                    tempFragment = new CallFragment();
                }else{
                    tempFragment = new HomeFragment();
                }
                getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framecontainer, tempFragment)
                .commit();

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }
}