package com.nabin.threaddemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyTag";
    TextView logText;
    Button startStop, clear;
    private boolean isTaskActive;
    MyTask task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logText = findViewById(R.id.logText);
        startStop = findViewById(R.id.startStop);
        clear = findViewById(R.id.clear);

        startStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTaskActive && task == null){
                    isTaskActive = true;
                    task = new MyTask();
                    task.execute("a", "b", "c");
                }else{
                    isTaskActive = false;
                    task.cancel(true);
                    task = null;
                }
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logText.setText("");
            }
        });
    }

    class MyTask extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... strings) {
            for(String s : strings){
                    if(!isTaskActive || isCancelled()){
                        break;
                    }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(s);
                Log.d(TAG, "doInBackground: " + s);
            }
            return "download completed";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            log(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            log(s);
            log("Completed");
        }

        @Override
        protected void onCancelled() {
            log("Canceled Task");
        }

        @Override
        protected void onPreExecute() {
            log("Starting task");
        }
    }


    void log(String st){
        logText.append("\n" + st);
    }
}