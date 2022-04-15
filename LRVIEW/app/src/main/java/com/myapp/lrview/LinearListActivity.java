package com.myapp.lrview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LinearListActivity extends AppCompatActivity {
    ListView listView;
    String[] names = {"Tokyo", "Beijing", "New Delhi", "Mumbai","Tokyo", "Beijing", "New Delhi", "Mumbai","Tokyo", "Beijing", "New Delhi", "Mumbai","Tokyo", "Beijing", "New Delhi", "Mumbai","Tokyo", "Beijing", "New Delhi", "Mumbai","Tokyo", "Beijing", "New Delhi", "Mumbai","Tokyo", "Beijing", "New Delhi", "Mumbai","Tokyo", "Beijing", "New Delhi", "Mumbai"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_list);
        listView = findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(LinearListActivity.this, android.R.layout.simple_list_item_1,names);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Pressed: " + names[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}