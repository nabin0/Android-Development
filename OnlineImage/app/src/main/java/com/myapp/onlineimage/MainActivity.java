package com.myapp.onlineimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Mytag";
    private String[] images;
    private String[] names;
    private JSONObject jsonObject;
    private RecyclerView recyclerView;
    ArrayList<ImageModel> list;
    ImageViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.recyclarView);

        list = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String url = "https://pixabay.com/api/?key=26262648-f819725293fed27841de745a6";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("hits");
                    for(int i = 0; i < array.length(); i++){
                        JSONObject obj = array.getJSONObject(i);
                        String uri =  obj.getString("previewURL");
                        String name = obj.getString("tags");
                        list.add(new ImageModel(uri, name));
                    }

                    adapter = new ImageViewAdapter(list, MainActivity.this);
                    recyclerView.setAdapter(adapter);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    recyclerView.setLayoutManager(linearLayoutManager);

                } catch (JSONException e) {
                    Log.d(TAG, "onResponse: Json error occured " + e);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: Error Occurred");
            }
        });
        requestQueue.add(stringRequest);

    }
}

// Api key : 26262648-f819725293fed27841de745a6