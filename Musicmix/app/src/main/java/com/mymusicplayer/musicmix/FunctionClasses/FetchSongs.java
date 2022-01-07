package com.mymusicplayer.musicmix.FunctionClasses;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;

import com.mymusicplayer.musicmix.Models.SongModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class FetchSongs {
    private Context context;

    // Constructor to get context of class where it is called
    public FetchSongs(Context context) {
        this.context = context;
    }


    // function that returns list of songs stored in devic
    // TODO: learn about MediaStore in detail

    public ArrayList<SongModel> getSongs() {
        ArrayList<SongModel> tempList = new ArrayList<>();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        final String artist1 = MediaStore.Audio.Media.ARTIST;
        final String data1 = MediaStore.Audio.Media.DATA;
        final String title1 = MediaStore.Audio.Media.TITLE;
        final String id1 = MediaStore.Audio.Media.ALBUM_ID;
        final String album1 = MediaStore.MediaColumns.ALBUM;
        final String duration1 = MediaStore.Audio.Media.DURATION;

        final Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(artist1));
                String album = cursor.getString(cursor.getColumnIndexOrThrow(album1));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(title1));
                String songuri = cursor.getString(cursor.getColumnIndexOrThrow(data1));
                Long albumId = cursor.getLong(cursor.getColumnIndexOrThrow(id1));
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(duration1));
                Uri imagePath = Uri.parse("content://media/external/audio/albumart");
                Uri imagePathUri = ContentUris.withAppendedId(imagePath, albumId);
                if (duration >= 100) {
                    SongModel model = new SongModel(title, album, artist, String.valueOf(duration), String.valueOf(uri), String.valueOf(albumId), String.valueOf(imagePathUri),songuri.toString());
                    tempList.add(model);
                }
            } while (cursor.moveToNext());
        }
        Collections.sort(tempList, new SortByTitle());
        return tempList;
    }
}

class SortByTitle implements Comparator<SongModel>{

    @Override
    public int compare(SongModel o1, SongModel o2) {
        if(o1.getSongName().equals(o2.getSongName())){
            return 0;
        }
        return o1.getSongName().compareToIgnoreCase(o2.getSongName());
    }
}

