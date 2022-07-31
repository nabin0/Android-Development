package com.nabin.sqlitecontactsdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper db = new DatabaseHelper(this);

        //Insert contacts
        /*
        db.insertContacts(new ContactModel("adam", "4576"));
        db.insertContacts(new ContactModel("deepti", "4543"));
        db.insertContacts(new ContactModel("ram", "8978"));
        db.insertContacts(new ContactModel("sukracharya", "234"));
        */

        //Fetch contacts
        ArrayList<ContactModel> list = new ArrayList<>();
        list = db.fetchAllContacts();

        //Update contact
        ContactModel contactModel = new ContactModel(1, "John snow", "0000000");
        db.updateContact(contactModel);

        //Delete contact
        db.deleteContact(4);
        db.deleteContact(5);
        db.deleteContact(6);
        db.deleteContact(7);
        db.deleteContact(8);

        //Log all contacts
        for (ContactModel c : list) {
            Log.d("MyTag", "Contact id: " + c.getId() + " Contact Name : " + c.getName() + " Phone no : " + c.getContact_no());
        }
    }

}