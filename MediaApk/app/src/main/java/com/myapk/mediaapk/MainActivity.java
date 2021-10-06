package com.myapk.mediaapk;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button play;
    private Button pause;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get view from layout
        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        seekBar = findViewById(R.id.seekBar);

        //Creating instance of media player class using local resource
        // MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.music);


        //Media player using remote source
        //Step 1
        MediaPlayer mediaPlayer = new MediaPlayer();
        try{
//            mediaPlayer.setDataSource("http://penguinradio.dominican.edu/Sound%20FX%20Collection/Beach%20Waves.mp3");
            mediaPlayer.setDataSource("http://www.dixiedrifter.com/music/mp3/0001-BillHaleyAndHisComets_RockAroundTheClock2000.mp3");
        }catch (Exception e){
            e.printStackTrace();
        }

        // Step 2
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                Toast.makeText(MainActivity.this, "Ready to play", Toast.LENGTH_SHORT).show();
                mediaPlayer.start();
                seekBar.setMax(mediaPlayer.getDuration());
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        if(b){
                            mediaPlayer.seekTo(i);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
            }
        });

        // step 3
        mediaPlayer.prepareAsync();


        // Onclick events
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.pause();
            }
        });
    }
}