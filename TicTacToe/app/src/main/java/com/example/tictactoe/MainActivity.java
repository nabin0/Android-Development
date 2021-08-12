package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //0: for 'O, 1: for 'X'
    int[] gameState = {-1, -1, -1, -1, -1, -1, -1, -1, -1};
    int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};
    int activePlayer = 0;
    boolean gameActive = true;

    public void pushIn(View view) {
        ImageView counter = (ImageView) view;
        Button playAgain = (Button) findViewById(R.id.playAgain);
        TextView result = (TextView) findViewById(R.id.resultView);

        int counterTapped = Integer.parseInt(counter.getTag().toString());

        if (gameActive && gameState[counterTapped] == -1) {
            gameState[counterTapped] = activePlayer;
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);

//Checcking for game win condition
            for (int[] pos : winningPosition) {
                if (gameState[pos[0]] == gameState[pos[1]] && gameState[pos[1]] == gameState[pos[2]] && gameState[pos[0]] != -1) {
                    gameActive = false;
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "O";
                    } else {
                        winner = "X";
                    }

                    playAgain.setVisibility(View.VISIBLE);
                    result.setText(winner + " is WINNER!!!");
                    result.setVisibility(View.VISIBLE);
                }
            }
            //Checck if game is draw
            boolean draw = false;
            for (int j : gameState) {
                if (j == -1) {
                    draw = false;
                    break;
                } else {
                    draw = true;
                }
            }
            if (draw) {
                gameActive = false;
                playAgain.setVisibility(View.VISIBLE);
                result.setText("Match Draw!!!");
                result.setVisibility(View.VISIBLE);
            }
        }
    }


    public void playAgain(View view) {

        Button playAgain = (Button) findViewById(R.id.playAgain);
        TextView result = (TextView) findViewById(R.id.resultView);

        Arrays.fill(gameState, -1);
                activePlayer = 0;
        gameActive = true;
        playAgain.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout gridLayout = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.gridLayoutBG);

        for(int i = 0; i < gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}