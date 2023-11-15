package com.example.recyclerviewtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class EventsActivity extends AppCompatActivity implements EventAdapter.UserClickListener {

    private RecyclerView recyclerView;
    String [] eventNames, desc;
    private Toolbar mtoolbar;
    int [] eventPics = {R.drawable.heckaton, R.drawable.group,R.drawable.atom, R.drawable.programing, R.drawable.meeting};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        recyclerView = findViewById(R.id.eventRecyclerView);

        mtoolbar = findViewById(R.id.toolbarEvent);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Events");

        eventNames = getResources().getStringArray(R.array.eventNames);
        desc = getResources().getStringArray(R.array.descrip);



        EventAdapter eventAdapter = new EventAdapter(EventsActivity.this, eventNames, eventPics,desc,this::selectedUser);
        recyclerView.setAdapter(eventAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    public void selectedUser(String eventName,int img) {


        Intent i = new Intent(EventsActivity.this,EventPageActivity.class);
        i.putExtra("eventName",eventName);
        i.putExtra("img",img);
        startActivity(i);

    }
}