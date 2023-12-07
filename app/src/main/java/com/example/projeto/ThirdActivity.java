package com.example.projeto;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private List<Note> notesList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        MaterialButton addNoteBtn = findViewById(R.id.addnewnotebtn);

        addNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this, AddNoteActivity.class));
            }
        });

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Load notes from SQLite database
        loadNotesFromDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(getApplicationContext(), notesList);
        recyclerView.setAdapter(myAdapter);
    }

    private void loadNotesFromDatabase() {
        notesList = new ArrayList<>();

        Cursor cursor = databaseHelper.getAllNotes();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String title = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE));
                String content = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_CONTENT));
                long createdTime = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_CREATED_TIME));

                Note note = new Note();
                note.setId(id);
                note.setTitle(title);
                note.setContent(content);
                note.setCreatedTime(createdTime);

                notesList.add(note);
            } while (cursor.moveToNext());

            cursor.close();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload notes when the activity resumes
        loadNotesFromDatabase();
        myAdapter.notifyDataSetChanged();
    }
}
