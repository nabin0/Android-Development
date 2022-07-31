package com.nabin.asynctaskloadergooglebookapi;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    // Base url for Books API.
    public static final String BASE_BOOK_URL = "https://www.googleapis.com/books/v1/volumes?";
    // Parameter for search string
    public static final String QUERY_PARAM = "q";
    // Parameter That limits search result
    public static final String MAX_RESULTS = "maxResults";
    // Parameter To filter by print type
    public static final String PRINT_TYPE = "printType";

    static String getBookInfo(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String bookJSONString = null;

        try {
            //Combine all parts to make a URI
            Uri builtURI = Uri.parse(BASE_BOOK_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();

            // Build the URL form URI
            URL requestURL = new URL(builtURI.toString());

            // Open Connection and make request
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Get Input stream
            InputStream inputStream = urlConnection.getInputStream();

            //Create a buffered readerFrom input stream
            reader = new BufferedReader(new InputStreamReader(inputStream));

            //Stringbuilder to hold the incomming respnese
            StringBuilder stringBuilder = new StringBuilder();


            String line;
            while((line = reader.readLine()) != null){
                stringBuilder.append(line);
                stringBuilder.append("\n"); // Not compulsory
            }

            if (stringBuilder.length() == 0){
                return null;
            }

            bookJSONString = stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d(LOG_TAG, bookJSONString);

        return bookJSONString;
    }
}
