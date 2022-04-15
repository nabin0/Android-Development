package com.myapp.practicerecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ModelClass> list;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        list = new ArrayList<>();

        list.add(new ModelClass("Paul Walker", "USA", R.drawable.paul));
        list.add(new ModelClass("Ryan", "US", R.drawable.ryan));
        list.add(new ModelClass("Gal Gadot", "America", R.drawable.gadot));
        list.add(new ModelClass("Name 1", "country one", R.drawable.gadot));
        list.add(new ModelClass("Name 2", "country 2", R.drawable.gadot));
        list.add(new ModelClass("Name 3", "country 3", R.drawable.ryan));
        list.add(new ModelClass("Name 4", "country 4", R.drawable.paul));
        list.add(new ModelClass("Name 5", "country 5", R.drawable.gadot));
        list.add(new ModelClass("Name 1", "country one", R.drawable.gadot));
        list.add(new ModelClass("Name 2", "country 2", R.drawable.gadot));
        list.add(new ModelClass("Name 3", "country 3", R.drawable.ryan));
        list.add(new ModelClass("Name 4", "country 4", R.drawable.paul));
        list.add(new ModelClass("Name 5", "country 5", R.drawable.gadot));

        MyAdapter adapter = new MyAdapter(list, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}