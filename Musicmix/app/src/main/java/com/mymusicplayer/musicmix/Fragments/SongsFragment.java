package com.mymusicplayer.musicmix.Fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mymusicplayer.musicmix.Activities.PlaySong;
import com.mymusicplayer.musicmix.Adapters.SongsListAdapter;
import com.mymusicplayer.musicmix.FunctionClasses.FetchSongs;
import com.mymusicplayer.musicmix.MainActivity;
import com.mymusicplayer.musicmix.R;

public class SongsFragment extends Fragment {
    public SongsListAdapter songsListAdapter;
    static public RecyclerView songsListRecyclerView;

    public SongsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_songs, container, false);
        songsListRecyclerView = view.findViewById(R.id.mySongsRecyclerView);

        // Check build version and take permissions if device in upper api level 23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

                // Fetch Songs and display them into the view if permission is granted already
                MainActivity.songsList = new FetchSongs(getContext()).getSongs();
                songsListAdapter = new SongsListAdapter(MainActivity.songsList, getActivity());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                songsListRecyclerView.setLayoutManager(linearLayoutManager);
                songsListRecyclerView.setNestedScrollingEnabled(false);
                songsListRecyclerView.setAdapter(songsListAdapter);

            } else {
                //  ask for permission if permission is not granted already or not asked
                checkStoragePermission();
            }
        } else {
            // if device is lower than api level 23(M) then permission is given at installing time so display songs directly
            MainActivity.songsList = new FetchSongs(getContext()).getSongs();


            songsListAdapter = new SongsListAdapter(MainActivity.songsList, getActivity());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            songsListRecyclerView.setLayoutManager(linearLayoutManager);
            songsListRecyclerView.setNestedScrollingEnabled(false);
            songsListRecyclerView.setAdapter(songsListAdapter);
        }
        return view;
    }

    // Check about storage permission
    private void checkStoragePermission() {
        // if is asked for permission already and user have denied in that case show alert dialogue with relative information
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Storage Permission Required")
                    .setMessage("Permission required to fetch songs available in device.")
                    .setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MainActivity.STORAGE_READ_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MainActivity.STORAGE_READ_PERMISSION_CODE);
        }
    }

}