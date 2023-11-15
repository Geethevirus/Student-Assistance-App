package com.example.recyclerviewtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class TutorsActivity extends AppCompatActivity implements TutorFilesAdapter.UserClickListener{

    RecyclerView recyclerView;
    String [] lessonNames;
    int image;
    private Toolbar mtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutors);

        recyclerView = findViewById(R.id.tutorRecycler);
        image = R.drawable.teaching;
        lessonNames = getResources().getStringArray(R.array.lessonNames);

        mtoolbar = findViewById(R.id.toolbarTutor);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Choose Module");


        TutorFilesAdapter tutorFilesAdapter = new TutorFilesAdapter(TutorsActivity.this, image,lessonNames,this::selectedUser);
        recyclerView.setAdapter(tutorFilesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    public void selectedUser(String lessonName) {

        Intent i = new Intent(TutorsActivity.this, TutorDetailActivity.class);
        i.putExtra("moduleName",lessonName);
        startActivity(i);

    }
}