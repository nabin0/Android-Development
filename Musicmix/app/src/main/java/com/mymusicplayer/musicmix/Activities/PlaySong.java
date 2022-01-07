package com.mymusicplayer.musicmix.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mymusicplayer.musicmix.Fragments.BottomSheetSongsListFragment;
import com.mymusicplayer.musicmix.MainActivity;
import com.mymusicplayer.musicmix.Models.SongModel;
import com.mymusicplayer.musicmix.R;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class PlaySong extends AppCompatActivity {
    static ArrayList<SongModel> allSongs;
    static MediaPlayer player;
    ImageView coverImg, favourite, repeat, songsList, dotMenu, previous, playPause, next;
    TextView songName, artistName, currentDuration, totalDuration;
    SeekBar seekBar;
    int currPosition;
    static Uri uri;
    static SongModel currentSong;
    private Handler handler = new Handler();
    private Thread playPauseThread, nextSongThread, prevSongThred;
    BottomSheetSongsListFragment bottomSheetSongsListFragment;
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        Objects.requireNonNull(getSupportActionBar()).hide();

        // Init all views
        intiViews();
        bottomSheetSongsListFragment = new BottomSheetSongsListFragment();

        // Get intents extras
        Intent playSongIntent = getIntent();
        Bundle bundle = playSongIntent.getExtras();
        allSongs = (ArrayList) bundle.getParcelableArrayList("mySongList");
        currPosition = playSongIntent.getIntExtra("currentSong", 0);
        currentSong = allSongs.get(currPosition);
        bitmap = BitmapFactory.decodeByteArray(currentSong.getImagePath().getBytes(), 0,currentSong.getImagePath().length());

        // Onstart activity play song
        if (allSongs != null) {
            uri = Uri.parse(allSongs.get(currPosition).getAudioPath());
        }
        if (player != null) {
            player.stop();
            player.release();
            player = MediaPlayer.create(getApplicationContext(), uri);

            songName.setText(currentSong.getSongName());
            artistName.setText(currentSong.getArtistName());
            totalDuration.setText(formattedSecs(Integer.parseInt(currentSong.getSongDuration())));
            Glide.with(getApplicationContext()).load(Uri.parse(allSongs.get(currPosition).getImagePath())).placeholder(R.drawable.logo).into(coverImg);
            player.start();
        } else {
            player = MediaPlayer.create(getApplicationContext(), uri);
            songName.setText(currentSong.getSongName());
            artistName.setText(currentSong.getArtistName());
            totalDuration.setText(formattedSecs(Integer.parseInt(currentSong.getSongDuration())));
            Glide.with(getApplicationContext()).load(Uri.parse(allSongs.get(currPosition).getImagePath())).placeholder(R.drawable.logo).into(coverImg);
            player.start();
        }

        seekBar.setMax(player.getDuration());


        // Seekbar functions
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (player != null && fromUser) {
                    player.seekTo(progress);
                    currentDuration.setText(formattedSecs(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Thread update seekbar an timePlayed
        updateSeekBar();

        // songslist bottomsheet

        clickBottomsheetSongList();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PlaySong.this, MainActivity.class);
        if (isTaskRoot()) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            super.onBackPressed();
        }
    }


    private void clickBottomsheetSongList() {
        songsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("songsList", allSongs);
                bottomSheetSongsListFragment.setArguments(bundle);
                bottomSheetSongsListFragment.show(getSupportFragmentManager(), bottomSheetSongsListFragment.getTag());
            }
        });

    }

    // Update Seekbar 
    private void updateSeekBar() {
        PlaySong.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int currentSeekbarPos = 0;
                try {
                    if (player != null && currentSeekbarPos < player.getDuration()) {
                        currentSeekbarPos = player.getCurrentPosition();
                        seekBar.setProgress(currentSeekbarPos);
                        currentDuration.setText(formattedSecs(currentSeekbarPos));
                    }
                    handler.postDelayed(this, 1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onPostResume() {
        playPauseThreadBtn();
        nextSongThreadBtn();
        previousSongThreadBtn();
        super.onPostResume();
    }

    // Methods used in onresume which are responsible for music play/pause/next/prev actions
    private void playPauseThreadBtn() {
        playPauseThread = new Thread() {
            @Override
            public void run() {
                super.run();
                playPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        playPauseClicked();
                    }
                });
            }
        };
        playPauseThread.start();
    }

    private void playPauseClicked() {
        if (player.isPlaying()) {
            player.pause();
            playPause.setImageResource(R.drawable.play_button);
            seekBar.setMax(player.getDuration());
            updateSeekBar();
        } else {
            player.start();
            playPause.setImageResource(R.drawable.pause_button);
            seekBar.setMax(player.getDuration());
            updateSeekBar();
        }
    }

    private void nextSongThreadBtn() {
        nextSongThread = new Thread() {
            @Override
            public void run() {
                super.run();
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        nextBtnClicked();
                    }
                });
            }
        };
        nextSongThread.start();
    }

    private void nextBtnClicked() {
        player.stop();
        player.release();

        if (currPosition < allSongs.size() - 1) {
            currPosition++;
        } else {
            currPosition = 0;
        }

        seekBar.setProgress(0);
        currentSong = allSongs.get(currPosition);
        Uri uri = Uri.parse(allSongs.get(currPosition).getAudioPath());
        playPause.setImageResource(R.drawable.pause_button);
        songName.setText(currentSong.getSongName());
        artistName.setText(currentSong.getArtistName());
        totalDuration.setText(formattedSecs(Integer.parseInt(currentSong.getSongDuration())));
        Glide.with(getApplicationContext()).load(Uri.parse(allSongs.get(currPosition).getImagePath())).placeholder(R.drawable.logo).into(coverImg);

        player = MediaPlayer.create(getApplicationContext(), uri);
        player.start();
    }

    private void previousSongThreadBtn() {
        prevSongThred = new Thread() {
            @Override
            public void run() {
                super.run();
                previous.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        previousBtnClicked();
                    }
                });
            }
        };
        prevSongThred.start();
    }

    private void previousBtnClicked() {
        player.stop();
        player.release();

        if (currPosition > 0) {
            currPosition = currPosition - 1;
        } else {
            currPosition = allSongs.size() - 1;
        }

        currentSong = allSongs.get(currPosition);
        Uri uri = Uri.parse(allSongs.get(currPosition).getAudioPath());
        songName.setText(currentSong.getSongName());
        playPause.setImageResource(R.drawable.pause_button);
        artistName.setText(currentSong.getArtistName());
        totalDuration.setText(formattedSecs(Integer.parseInt(currentSong.getSongDuration())));
        Glide.with(getApplicationContext()).load(Uri.parse(allSongs.get(currPosition).getImagePath())).placeholder(R.drawable.logo).into(coverImg);

        player = MediaPlayer.create(getApplicationContext(), uri);
        player.start();
    }

    private String formattedSecs(int currPosition) {
        String formattedTime = "";

        String secs = String.valueOf((currPosition / 1000) % 60);
        String mins = String.valueOf((currPosition / 1000) / 60);
        formattedTime = mins + ":" + secs;
        if (mins.length() < 2) {
            mins = "0" + mins;
        }
        if (secs.length() < 2) {
            secs = "0" + secs;
        }
        formattedTime = mins + ":" + secs;

        return formattedTime;
    }


    private void intiViews() {
        coverImg = findViewById(R.id.albumImageView);
        favourite = findViewById(R.id.favourite);
        repeat = findViewById(R.id.repeat);
        songsList = findViewById(R.id.showSongList);
        dotMenu = findViewById(R.id.playSongDotsMenu);
        previous = findViewById(R.id.previous);
        playPause = findViewById(R.id.playPauseView);
        next = findViewById(R.id.next);
        songName = findViewById(R.id.songNamePlaySong);
        artistName = findViewById(R.id.artistNamePlaySong);
        seekBar = findViewById(R.id.songSeekBar);
        currentDuration = findViewById(R.id.currentDuration);
        totalDuration = findViewById(R.id.songTotalDuration);
        songName.setSelected(true);
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        player.stop();
//        player.release();
//        seekBarUpdate.interrupt();
//    }


}