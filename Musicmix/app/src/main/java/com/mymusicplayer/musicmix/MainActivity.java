package com.mymusicplayer.musicmix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.mymusicplayer.musicmix.Adapters.SongsListAdapter;
import com.mymusicplayer.musicmix.Fragments.EqualizerFragment;
import com.mymusicplayer.musicmix.Fragments.SearchFragment;
import com.mymusicplayer.musicmix.Fragments.SettingFragment;
import com.mymusicplayer.musicmix.Fragments.SongsFragment;
import com.mymusicplayer.musicmix.FunctionClasses.FetchSongs;
import com.mymusicplayer.musicmix.Models.SongModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private long pressedTime;
    BottomNavigationView bottomNavigationView;
    static public int STORAGE_READ_PERMISSION_CODE = 1;
    static public ArrayList<SongModel> songsList;
    private SongsListAdapter songsListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        // Initializing Variables
        songsList = new ArrayList<>();
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Onclick on bottom navigation bar
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.songs:
                        fragmentTransaction.replace(R.id.fragmentContainerView, new SongsFragment());
                        break;

                    case R.id.search:
                        fragmentTransaction.replace(R.id.fragmentContainerView, new SearchFragment());
                        break;

                    case R.id.equalizer:
                        fragmentTransaction.replace(R.id.fragmentContainerView, new EqualizerFragment());
                        break;

                    case R.id.setting:
                        fragmentTransaction.replace(R.id.fragmentContainerView, new SettingFragment());
                        break;
                }
                fragmentTransaction.commit();
                return true;
            }
        });


    }


    // Check if permission is granted or not if granted show songs
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_READ_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Permission Granted.", Toast.LENGTH_SHORT).show();

                // Fetch and display songs in recycler view

                        songsList = new FetchSongs(MainActivity.this).getSongs();

                songsListAdapter = new SongsListAdapter(songsList, MainActivity.this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
               SongsFragment.songsListRecyclerView.setLayoutManager(linearLayoutManager);
                SongsFragment.songsListRecyclerView.setNestedScrollingEnabled(false);
                SongsFragment.songsListRecyclerView.setAdapter(songsListAdapter);

            } else {
                Toast.makeText(MainActivity.this, "Permission Denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        
        if(pressedTime + 2000> System.currentTimeMillis()){
            super.onBackPressed();
            finish();
        }else{
            Toast.makeText(MainActivity.this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

}