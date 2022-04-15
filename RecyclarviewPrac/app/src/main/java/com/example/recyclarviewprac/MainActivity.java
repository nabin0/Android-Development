package com.example.recyclarviewprac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclarviewprac.Adapters.UserAdapter;
import com.example.recyclarviewprac.Models.UserModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclarView);

        ArrayList<UserModel> list = new ArrayList<>();
        list.add(new UserModel(R.drawable.app_logo, "Adam"));
        list.add(new UserModel(R.drawable.user_avtar, "fgsdfh"));
        list.add(new UserModel(R.drawable.app_logo, "fdgs"));
        list.add(new UserModel(R.drawable.app_logo, "d"));
        list.add(new UserModel(R.drawable.user_avtar, "neha"));
        list.add(new UserModel(R.drawable.app_logo, "Adam"));
        list.add(new UserModel(R.drawable.app_logo, "shruti"));
        list.add(new UserModel(R.drawable.app_logo, "Adam"));
        list.add(new UserModel(R.drawable.app_logo, "Adam"));

        UserAdapter adapter = new UserAdapter(list, this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
    }
}