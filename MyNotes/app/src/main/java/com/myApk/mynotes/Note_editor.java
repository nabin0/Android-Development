package com.myApk.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashMap;
import java.util.HashSet;

public class Note_editor extends AppCompatActivity {
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText = findViewById(R.id.editNote);
        Intent getNoteIntent = getIntent();
        noteId = getNoteIntent.getIntExtra("noteId", -1);

        if(noteId != -1){
            editText.setText(MainActivity.notes.get(noteId));
        }else{
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size() -1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notes.set(noteId, String.valueOf(charSequence));
                MainActivity.noteAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.myApk.mynotes" , Context.MODE_PRIVATE);
                HashSet<String> noteSet = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes", noteSet).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}