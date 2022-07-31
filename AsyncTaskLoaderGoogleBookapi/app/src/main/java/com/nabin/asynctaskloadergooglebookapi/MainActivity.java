package com.nabin.asynctaskloadergooglebookapi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private EditText mBookInput;
    private TextView mTitleText, mAuthorText;
    private Button mSearchBookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = findViewById(R.id.searchEditText);
        mTitleText = findViewById(R.id.titleText);
        mAuthorText = findViewById(R.id.autherTextView);
        mSearchBookBtn = findViewById(R.id.searchBtn);

        if(getSupportLoaderManager().getLoader(0) != null){
            getSupportLoaderManager().initLoader(0, null, this);
        }


        mSearchBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String queryString = mBookInput.getText().toString();

                // Hide Keyboard
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(mSearchBookBtn.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }

                //Check Network state and input
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = null;
                if (connectivityManager != null) {
                    networkInfo = connectivityManager.getActiveNetworkInfo();
                }

                if (networkInfo != null && networkInfo.isConnected() && queryString.length() != 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("queryString", queryString);

                    getSupportLoaderManager().restartLoader(0, bundle, MainActivity.this);
                    mTitleText.setText("Loading...");
                    mAuthorText.setText("");
                } else {
                    if (queryString.length() == 0) {
                        mTitleText.setText("Please Enter Valid Input");
                        mAuthorText.setText("");
                    } else {
                        mTitleText.setText("No Network Connection");
                        mAuthorText.setText("");
                    }
                }

            }
        });

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";

        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new BookLoader(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            int i = 0;
            String title = null;
            String authors = null;

            while (i < itemsArray.length() && (authors == null && title == null)) {
                JSONObject book = itemsArray.getJSONObject(i);
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");
                } catch (Exception e) {
                    e.printStackTrace();
                }

                i++;
            }

            if (title != null && authors != null) {
                mTitleText.setText(title);
                mAuthorText.setText(authors);
            } else {
                mTitleText.setText("NO RESULT FOUND!!!");
                mAuthorText.setText("");
            }

        } catch (JSONException e) {
            mTitleText.setText("NO RESULT FOUND!!!");
            mAuthorText.setText("");
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public static class BookLoader extends AsyncTaskLoader<String> {
        private String mQueryString;

        public BookLoader(@NonNull Context context, String queryString) {
            super(context);
            this.mQueryString = queryString;
        }

        @Nullable
        @Override
        public String loadInBackground() {
            return NetworkUtils.getBookInfo(mQueryString);
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            forceLoad();
        }
    }
}