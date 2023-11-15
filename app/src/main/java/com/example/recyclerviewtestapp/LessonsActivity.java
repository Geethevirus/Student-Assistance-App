package com.example.recyclerviewtestapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LessonsActivity extends AppCompatActivity implements LessonAdapter.UserClickListener{
    ImageView backIcon;
    String lessonNames [];
    RecyclerView recyclerView;
    private Toolbar mtoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        lessonNames = getResources().getStringArray(R.array.lessonNames);
        recyclerView = findViewById(R.id.recyclerView1);

        mtoolbar = findViewById(R.id.toolbarTutor);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lessons");






        LessonAdapter lessonAdapter = new LessonAdapter(LessonsActivity.this,lessonNames, this::selectedUser);
        recyclerView.setAdapter(lessonAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        /*ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }*/
    }

    @Override
    public void selectedUser(String lessonName) {

        Intent i = new Intent(LessonsActivity.this, PdfsActivity.class);
        i.putExtra("lessonName", lessonName+" PDFs");
        startActivity(i);

        //Toast.makeText(this, "Selected lesson :"+lessonName,Toast.LENGTH_SHORT).show();

    }
}