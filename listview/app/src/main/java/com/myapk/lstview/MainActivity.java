package com.myapk.lstview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String[] arr = {"item 1", "Item 2", "item 3", "a", "item 4", "item 5", "iteem 3", "item 1", "Item 2", "item 3", "a", "item 4", "item 5", "iteem 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        // Inbuilt Arrayadapter
        // ArrayAdapter ad = new ArrayAdapter(this.getApplicationContext(), android.R.layout.simple_list_item_1, arr);
        // listView.setAdapter(ad);

        // onclick listener in default adapter
        // listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //    @Override
        //    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //        Toast.makeText(getApplicationContext() , "this is elament no"+ i, Toast.LENGTH_SHORT).show();
        //    }
        // });


        // Custom array adapter
        MyAdapter ad = new MyAdapter(this, R.layout.mylayout, arr);
        listView.setAdapter(ad);
    }
}