package com.example.take_notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;

public class MainActivity2 extends AppCompatActivity {
    int noteId;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent=getIntent();
        noteId = intent.getIntExtra("noteId",-1);
        editText =(EditText) findViewById(R.id.PersonName);

        if(noteId!=-1)
        {editText.setText(MainActivity.notes.get(noteId));}
        else
        {
            MainActivity.notes.add("");
            noteId=MainActivity.notes.size()-1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.notes.set(noteId,String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();
                SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.example.take_notes", Context.MODE_PRIVATE);
                HashSet<String>set=new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("note",set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}