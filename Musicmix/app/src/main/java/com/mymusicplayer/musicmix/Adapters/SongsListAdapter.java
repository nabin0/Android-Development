package com.mymusicplayer.musicmix.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mymusicplayer.musicmix.Activities.PlaySong;
import com.mymusicplayer.musicmix.Fragments.SongsFragment;
import com.mymusicplayer.musicmix.MainActivity;
import com.mymusicplayer.musicmix.Models.SongModel;
import com.mymusicplayer.musicmix.R;

import java.util.ArrayList;

public class SongsListAdapter extends RecyclerView.Adapter<SongsListAdapter.SongsViewHolder> {

    // Useful list and variables
    private ArrayList<SongModel> songsModels;
    private Context context;


    // Constructor
    public SongsListAdapter(ArrayList<SongModel> songsModels, Context context) {
        this.songsModels = songsModels;
        this.context = context;
        ;
    }

    @NonNull
    @Override
    public SongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate view made to use in recycler view
        View view = LayoutInflater.from(context).inflate(R.layout.songs_list_model_view, parent, false);
        return new SongsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsViewHolder holder, int position) {
        // Set Value in the view according to the position of the model
        SongModel model = songsModels.get(position); // Get model at positon

        holder.songTitle.setText(model.getSongName());
        holder.artist.setText(model.getArtistName());

        // converting duration in hh%mm%ss format
        long millis = Long.parseLong(model.getSongDuration());

        long mins = (long) ((millis / 1000) / 60);
        long seconds = (long) (millis / 1000) % 60;

        String minStr = String.valueOf(mins);
        String secStr = String.valueOf(seconds);

        if (minStr.length() < 2) {
            minStr = "0" + minStr;
        }
        if (secStr.length() < 2) {
            secStr = "0" + secStr;
        }
        String songDuration = minStr + ":" + secStr + " min";
        holder.duration.setText(songDuration);
        Uri uri = Uri.parse(model.getUriString());
        String albumId = model.getAlbumId();
        Uri imagePathUri = Uri.parse(model.getImagePath());

        // Setting album image
        Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (imagePathUri != null) {
                            Glide.with(context).load(imagePathUri).placeholder(R.drawable.logo).into(holder.coverImage);
                        } else {
                            Log.i("AudioImageNotFound", "Audio image not found");
                        }
                    }
                });
            }
        }).start();

        // Setting onclick on recycler view
        holder.songBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playSongIntent = new Intent(v.getContext(), PlaySong.class);
                playSongIntent.putExtra("currentSong", holder.getAdapterPosition());
                playSongIntent.putExtra("mySongList", songsModels);
                playSongIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                v.getContext().startActivity(playSongIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return songsModels.size();
    }

    public class SongsViewHolder extends RecyclerView.ViewHolder {
        private TextView songTitle, artist, duration;
        private ImageView coverImage;
        private ConstraintLayout songBody;

        public SongsViewHolder(@NonNull View itemView) {
            super(itemView);
            songTitle = itemView.findViewById(R.id.songName);
            artist = itemView.findViewById(R.id.artistName);
            duration = itemView.findViewById(R.id.songDuration);
            coverImage = itemView.findViewById(R.id.songImage);
            songBody = itemView.findViewById(R.id.songsUi);

        }


    }


}
