package com.nabin.multithreading;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private static final String TAG = "MyTag";
    public static String DATA_KEY = "myData";
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Log.d(TAG, "onCreate: Thread id " + Thread.currentThread().getId());
    }

    public void runCode(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(DATA_KEY, "Url of some resource");
        LoaderManager.getInstance(this).restartLoader(100, bundle, this).forceLoad();
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        return new MyLoader(this, args, list);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d(TAG, "onLoadFinished: " + Thread.currentThread().getId());
        log(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        
    }

    private static class MyLoader extends AsyncTaskLoader<String>{
        private  Bundle mBundle;
        ArrayList<String> mList;

        public MyLoader(@NonNull Context context, Bundle args, ArrayList<String> list) {
            super(context);
            mBundle = args;
            mList = list;
        }

        @Nullable
        @Override
        public String loadInBackground() {
            String data = mBundle.getString(DATA_KEY);
            Log.d(TAG, "loadInBackground: started " + Thread.currentThread().getId());
            Log.d(TAG, "loadInBackground: url " + data);
            for(String s : mList){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Log.d(TAG, "loadInBackground: loading " + s);
            }
            Log.d(TAG, "loadInBackground: Terminated " + Thread.currentThread().getId() );
            return data + " download complete ";
        }

        @Override
        public void deliverResult(@Nullable String data) {
            data += " Data is changed using deliver result";
            super.deliverResult(data);
        }
    }

    void log(String s){
        textView.append("\n" + s);
    }
}