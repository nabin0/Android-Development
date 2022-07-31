package com.nabin.recyclerviewbestpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnRvClickListener {
    public static final String TAG = "MyTag";

    RecyclerView recyclerView ;
    ArrayList<String> names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = new ArrayList<>();

        names.add("Nabin");
        names.add("Shiva");
        names.add("Ganesh");
        names.add("Radha");
        names.add("John");

        Log.d(TAG, "initRecyclerView: intializing rv");
        recyclerView = findViewById(R.id.recyclerview);
        CustomAdapter adapter = new CustomAdapter(this, names , this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onItemClick(int pos) {
        Log.d(TAG, "onItemClick: item clicked " + pos);
        Toast.makeText(this, "Position " + pos + "name " + names.get(pos), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("name", names.get(pos));
        startActivity(intent);
    }

}