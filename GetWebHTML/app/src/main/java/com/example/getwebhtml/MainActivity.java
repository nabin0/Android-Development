package com.example.getwebhtml;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public void getHtml(View view){
        String htmlData = "";
            EditText urlView = (EditText) findViewById(R.id.urlText);
        TextView showHtml = (TextView) findViewById(R.id.showHtml);
        downloadHtmlContent task = new downloadHtmlContent();
        try {
            String url = urlView.getText().toString();
//            String url = "https://www.google.com";
            htmlData = task.execute(url).get();
        } catch (Exception e) {
            htmlData = "Some error occured!!!";
            e.printStackTrace();
        }
//        showHtml.setText(HtmlCompat.fromHtml(htmlData, 0)); //Shows design according to html tags
        showHtml.setText(htmlData);
        Log.i("html", htmlData);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

class downloadHtmlContent extends AsyncTask<String ,Integer , String>{

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
            while(data!= -1){
                char ch =(char) data;
                result.append(ch);
                data = inpStreamReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
}
