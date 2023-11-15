package com.example.recyclerviewtestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class EventPageActivity extends AppCompatActivity {

    private Intent i;
    private int picRes;
    ImageView imageView;
    TextView eventName;
    String eName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        imageView = findViewById(R.id.eventPic);
        eventName = findViewById(R.id.EventName);

        i = getIntent();

        if(i !=null)
        {
            picRes = i.getIntExtra("img",0);
            eName = i.getStringExtra("eventName");

        }
        imageView.setImageResource(picRes);
        eventName.setText(eName);

    }
}