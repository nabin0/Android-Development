package com.myapp.lrview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.myapp.lrview.Adapters.MyFoodAdapter;
import com.myapp.lrview.Interfaces.FoodAdapterInterface;
import com.myapp.lrview.Models.MyFoodModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FoodAdapterInterface {
    RecyclerView recyclerView;

    ArrayList<MyFoodModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclarView);

        list.add(new MyFoodModel(R.drawable.food_one, "Itialian Food"));
        list.add(new MyFoodModel(R.drawable.food_two, "Burger Food"));
        list.add(new MyFoodModel(R.drawable.food_one, "Itialian Food"));
        list.add(new MyFoodModel(R.drawable.food_three, "Tasty Food"));
        list.add(new MyFoodModel(R.drawable.food_one, "Itialian Food"));
        list.add(new MyFoodModel(R.drawable.food_one, "Itialian Food"));
        list.add(new MyFoodModel(R.drawable.food_two, "Burger Food"));
        list.add(new MyFoodModel(R.drawable.food_one, "Itialian Food"));
        list.add(new MyFoodModel(R.drawable.food_three, "Tasty Food"));
        list.add(new MyFoodModel(R.drawable.food_one, "Itialian Food"));

        MyFoodAdapter adapter = new MyFoodAdapter(list, this, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);//
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//        recyclerView.setLayoutManager(gridLayoutManager);

//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(staggeredGridLayoutManager);


    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "item clicked " + list.get(position).getName(), Toast.LENGTH_SHORT).show();
    }
}