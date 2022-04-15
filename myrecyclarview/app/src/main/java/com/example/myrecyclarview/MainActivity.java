package com.example.myrecyclarview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<UserModel> list = new ArrayList<>();
    RecyclerView recyclerView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclarview);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetFragment fragment = new BottomSheetFragment();
                fragment.show(getSupportFragmentManager(), fragment.getTag());
            }
        });

        list.add(new UserModel(R.drawable.pic, "Ned"));
        list.add(new UserModel(R.drawable.picc, "Neha"));
        list.add(new UserModel(R.drawable.piccc, "Adam"));
        list.add(new UserModel(R.drawable.piccc, "Noah"));
        list.add(new UserModel(R.drawable.picc, "Ned"));
        list.add(new UserModel(R.drawable.picc, "Heisenburg"));
        list.add(new UserModel(R.drawable.pic, "newton"));

        // Setting adapter to the recyclar view
        MyAdapter adapter = new MyAdapter(list, this);
        recyclerView.setAdapter(adapter);

        // Setting layoutmanager to the recycler view
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }
}