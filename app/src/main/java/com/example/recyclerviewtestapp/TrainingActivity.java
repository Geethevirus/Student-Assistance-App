package com.example.recyclerviewtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class TrainingActivity extends AppCompatActivity {
    private String[] desciptions;
    private int [] images = {R.drawable.accounts,R.drawable.cone,R.drawable.dna,R.drawable.eng,R.drawable.globe,R.drawable.maths,R.drawable.science,
            R.drawable.stats,R.drawable.zsocial};
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        recyclerView = findViewById(R.id.recyclerView);

        desciptions = getResources().getStringArray(R.array.description);
        recyclerView = findViewById(R.id.recyclerView);

        MyAdapter myAdapter = new MyAdapter(TrainingActivity.this, desciptions, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}