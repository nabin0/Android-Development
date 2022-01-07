package com.mymusicplayer.musicmix.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.mymusicplayer.musicmix.Activities.PlaySong;
import com.mymusicplayer.musicmix.Adapters.SongsListAdapter;
import com.mymusicplayer.musicmix.MainActivity;
import com.mymusicplayer.musicmix.Models.SongModel;
import com.mymusicplayer.musicmix.R;

import java.util.ArrayList;


public class BottomSheetSongsListFragment extends BottomSheetDialogFragment {

    private RecyclerView songsRv;
    private ArrayList<SongModel> songsList;



    public BottomSheetSongsListFragment() {
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
        View view =  inflater.inflate(R.layout.fragment_bottom_sheet_songs_list, container, false);
        songsRv = view.findViewById(R.id.bottomSheetSongsRv);
        songsList = MainActivity.songsList;

        SongsListAdapter adapter = new SongsListAdapter(songsList, getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        songsRv.setAdapter(adapter);
        songsRv.setLayoutManager(manager);

        return view;
    }



    @Override
    public void dismiss() {
        super.dismiss();
    }
}