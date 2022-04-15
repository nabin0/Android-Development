package com.myapp.asynctaskpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyTag";
    Button start, stop;
    TextView textView;
    private MyTask myTask;
    private boolean mTaskIsRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        start = findViewById(R.id.startbtn);
        stop = findViewById(R.id.stopBtn);
        textView = findViewById(R.id.textView);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mTaskIsRunning){
                    mTaskIsRunning = true;
                    myTask = new MyTask();
                    myTask.execute("apple", "ball", "cat", "dog");
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mTaskIsRunning && myTask != null){
                    myTask.cancel(true);
                    mTaskIsRunning = false;
                }
                textView.setText("");
            }
        });
    }

    void log(String s){
        String text = (String) textView.getText();
        textView.setText(text + "\n" +s);
    }

    class MyTask extends AsyncTask<String, String,String>{

        @Override
        protected String doInBackground(String... strings) {
            for (String data :
                    strings) {
                if(isCancelled()){
                    publishProgress("TAsk Cancelled");
                    break;
                }
                Log.d(TAG, "doInBackground: " + data);
                publishProgress(data);
                try {
                    Thread.sleep(3000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return  "Task Completed... ";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            log(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            log(s);
        }

        @Override
        protected void onCancelled() {
            log("Task is cancelled");
        }
    }
}