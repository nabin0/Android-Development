package com.newpubgnewstatewalpaper.pubgnewstatewalpapers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> images = new ArrayList<>(); // To store images links
    ArrayList<String> names = new ArrayList<>(); // TO store names of images
    RecyclerView recyclerView;
    ArrayList<ImageSetModel> list;


    // Get Web content (html ) and it runs in background
    class downloadHtmlContent extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inpstream = urlConnection.getInputStream();
                InputStreamReader inpStreamReader = new InputStreamReader(inpstream);
                int data = inpStreamReader.read();
                while (data != -1) {
                    char ch = (char) data;
                    result.append(ch);
                    data = inpStreamReader.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result.toString();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting web html and using regex to separate links form the result and storing them int0 array list
        String htmlData = "";
        downloadHtmlContent task = new downloadHtmlContent();
        try {
            String url = "https://wallpapercave.com/pubg-new-state-wallpapers";
            htmlData = task.execute(url).get();
        } catch (Exception e) {
            htmlData = "Some error occured!!!";
            e.printStackTrace();
        }

        // Storing Images links
        String url = "https://wallpapercave.com";
        Pattern pattern = Pattern.compile("slug=\"pubg-new-state-wallpapers\" src=\"(.*?)\" ");
        Matcher matcher = pattern.matcher(htmlData);

        while (matcher.find()) {
            images.add(url + matcher.group(1));
            Log.d("links ", matcher.group(1));
        }

        // Storing image names
        pattern = Pattern.compile("alt=\"(.*?)\" class=\"wpimg\"");
        matcher = pattern.matcher(htmlData);
        while (matcher.find()) {
            names.add(matcher.group(1));
        }

        // Setting data to recycler view
        recyclerView = findViewById(R.id.imageRecyclarview);
        list = new ArrayList<>();

        // Creating arraylist of models
        int i = 2;
        while(i < images.size()) {
            list.add(new ImageSetModel(images.get(i), names.get(i)));
            i++;
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        ImageAdapter adapter = new ImageAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }
}