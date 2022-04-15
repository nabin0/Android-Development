package com.nabin.tabdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem item1, item2, item3;
    ViewPager2 viewPager;
    ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        tabLayout = findViewById(R.id.tabLayout);
        item1 = findViewById(R.id.tab1);
        item2 = findViewById(R.id.tab2);
        item3 = findViewById(R.id.tab3);
        viewPager = findViewById(R.id.viewPager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            String tabName = "";
            switch (position){
                case 0: tabName = "home";
                break;
                case 1: tabName = "chat";
                break;
                case 2 : tabName = "call";
                break;
                default: tabName = "Default";
            }
            tab.setText(tabName);
        }).attach();


    }
}