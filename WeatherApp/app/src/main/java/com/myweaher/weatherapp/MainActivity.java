package com.myweaher.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText getCity;
    TextView setResult;

    public void getWeather(View view) {
        try {
            String cityName = URLEncoder.encode(getCity.getText().toString(), "UTF-8");

            DownloadData task = new DownloadData();
            task.execute("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=de37869324b4441c774913784a5e5ece");

            InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mgr.hideSoftInputFromWindow(getCity.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Could not find Weather :(", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCity = (EditText) findViewById(R.id.getCityName);
        setResult = (TextView) findViewById(R.id.viewWeatherResult);

    }

    public class DownloadData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection httpURLConnection = null;

            try {
                url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream in = httpURLConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObj = new JSONObject(s);
                String weatherInfo = jsonObj.getString("weather");
                JSONObject tempInfo = (JSONObject) jsonObj.getJSONObject("main");
                Log.i("temp", String.valueOf(tempInfo));
                JSONArray arr = new JSONArray(weatherInfo);
                String msg = "";

                for (int i = 0; i < arr.length(); i++) {
                    JSONObject jsonPart = arr.getJSONObject(i);
                    String main = jsonPart.getString("main");
                    String desc = jsonPart.getString("description");
                    if (!main.equals("") && !desc.equals("")) {
                        msg = main + " : " + desc+"\n";
                    }
                }

                try {

                    String temp = tempInfo.getString("temp");
                    double doubleTemp = Double.parseDouble(temp) - 273.15;
                    String temperature = String.valueOf(Math.round(doubleTemp));
                    String pressure = tempInfo.getString("pressure");
                    String humidity = tempInfo.getString("humidity");

                    if (!temperature.equals("") && !pressure.equals("") && !humidity.equals("")) {
                        msg += "Temperature: " + temperature + " \u2103\n" + "Pressure : " + pressure + " pascal\n" + "Humidity: " + humidity + " %\n";
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

                if (!msg.equals("")) {
                    setResult.setText(msg);
                } else {
                    Toast.makeText(getApplicationContext(), "Could not find Weather1 :(", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Could not find Weather :(", Toast.LENGTH_SHORT).show();
            }
        }

    }
}


//https://api.openweathermap.org/data/2.5/weather?q=london&appid=de37869324b4441c774913784a5e5ece

//{"coord":{"lon":-0.1257,"lat":51.5085},"weather":[{"id":804,"main":"Clouds","description":"overcast clouds","icon":"04d"}],"base":"stations","main":{"temp":289.74,"feels_like":289.8,"temp_min":288.63,"temp_max":291.24,"pressure":1017,"humidity":90},"visibility":10000,"wind":{"speed":5.14,"deg":290},"clouds":{"all":90},"dt":1629620737,"sys":{"type":2,"id":2006068,"country":"GB","sunrise":1629608248,"sunset":1629659381},"timezone":3600,"id":2643743,"name":"London","cod":200}