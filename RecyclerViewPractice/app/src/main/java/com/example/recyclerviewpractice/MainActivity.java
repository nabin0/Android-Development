package com.example.recyclerviewpractice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerviewpractice.Adapters.RecyclerViewAdapter;
import com.example.recyclerviewpractice.Models.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Item> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);


        list = new ArrayList<>();
        list.add(new Item(R.drawable.ic_launcher_background, "name 1"));
        list.add(new Item(R.drawable.ic_launcher_background, "name 2"));
        list.add(new Item(R.drawable.ic_launcher_background, "name 3"));
        list.add(new Item(R.drawable.ic_launcher_background, "name 4"));
        list.add(new Item(R.drawable.ic_launcher_background, "name 5"));
        list.add(new Item(R.drawable.ic_launcher_background, "name 6"));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(list, getApplicationContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);

    }
}