package com.mymusicplayer.musicmix.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mymusicplayer.musicmix.Adapters.SongsListAdapter;
import com.mymusicplayer.musicmix.MainActivity;
import com.mymusicplayer.musicmix.Models.SongModel;
import com.mymusicplayer.musicmix.R;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    ArrayList<SongModel> filteredSongsList;
    EditText searchTxt;
    RecyclerView searchFRv;
    SongsListAdapter adapter;

    public SearchFragment() {
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchTxt = view.findViewById(R.id.searchTextEv);
        searchFRv = view.findViewById(R.id.searchFragRv);


        // On inserting character in the search field
        searchTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <1) {
                    filteredSongsList.clear();
                    filteredSongsList = null;
                }
                else{
                    filteredSongsList = filterSongs(s.toString());
                    if (filteredSongsList != null && !filteredSongsList.isEmpty()) {
                        adapter = new SongsListAdapter(filteredSongsList, getContext());
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        searchFRv.setAdapter(adapter);
                        searchFRv.setLayoutManager(manager);
                    }else{
                        adapter = new SongsListAdapter(filteredSongsList, getContext());
                        LinearLayoutManager manager = new LinearLayoutManager(getContext());
                        searchFRv.setAdapter(adapter);
                        searchFRv.setLayoutManager(manager);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }


    // Searching songs and returns arraylist of filtered songs
    public ArrayList<SongModel> filterSongs(String query) {
        ArrayList<SongModel> tempList = new ArrayList<>();

        for (SongModel model :
                MainActivity.songsList) {
            if (model.getSongName().toLowerCase().contains(query.toLowerCase())) {
                tempList.add(model);
            } else if (model.getArtistName().toLowerCase().contains(query.toLowerCase())) {
                tempList.add(model);
            }
        }
        return tempList;
    }
}