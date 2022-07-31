package com.nabin.sqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.nabin.sqlite.models.Contact;
import com.nabin.sqlite.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {


    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + Params.TABLE_NAME + "(" + Params.KEY_ID + " INTEGER PRIMARY KEY, " +
                Params.KEY_NAME + " TEXT, " + Params.KEY_PHONE + " TEXT " + ")";
        db.execSQL(createTable);
        Log.d("MyTag", "Database Created Successfully");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(Contact contact){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Params.KEY_NAME, contact.getName());
        contentValues.put(Params.KEY_PHONE, contact.getPhone_number());

        db.insert(Params.TABLE_NAME, null, contentValues);
        // Close Db
        db.close();

        Log.d("MyTag", "Contact added successfully into the database ");
    }

    public List<Contact> getContacts(){
        List<Contact> contacts = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Params.TABLE_NAME ;
        Cursor cursor = database.rawQuery(selectQuery, null);

        //Loop through

        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone_number(cursor.getString(2));

                contacts.add(contact);
            }while (cursor.moveToNext());
        }
        return contacts;
    }

    public int updateContact(Contact contact){
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Params.KEY_NAME, contact.getName());
        values.put(Params.KEY_PHONE, contact.getPhone_number());

        int affected = database.update(Params.TABLE_NAME, values, Params.KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
        database.close();
        return affected;
    }

    public int deleteContact(Contact contact){
        SQLiteDatabase database = this.getWritableDatabase();

        int affected = database.delete(Params.TABLE_NAME, Params.KEY_ID + "=?", new String[]{String.valueOf(contact.getId())});
        database.close();
        return affected;
    }

    public int getContactsCount(){
        String query = "SELECT * FROM " + Params.TABLE_NAME;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);

        return  cursor.getCount();
    }
}
