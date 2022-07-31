package com.nabin.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.nabin.sqlite.data.MyDbHandler;
import com.nabin.sqlite.models.Contact;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        MyDbHandler dbHandler = new MyDbHandler(MainActivity.this);

        // Create contacts
        Contact a = new Contact("a", "9898989898247");

        Contact b = new Contact("b", "21323425435344");

        //add contacts
        dbHandler.addContact(a);
        dbHandler.addContact(b);

        // Update contact
//        int affected = dbHandler.updateContact(new Contact(2,"akka", "234234"));
//        Log.d("MyTag", "onCreate: affected rows are " + affected);
//
//        // Delete Contact
//        int d = dbHandler.deleteContact(new Contact(2));
//        Log.d("MyTag", "onCreate: affected rows are " + d);

        //Log all contacts

        ArrayList<Contact> contacts = (ArrayList<Contact>) dbHandler.getContacts();

//        Log.d("MyTag", "No of contacts are : " + dbHandler.getContactsCount());


        MyAdapter adapter = new MyAdapter(MainActivity.this, contacts);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}