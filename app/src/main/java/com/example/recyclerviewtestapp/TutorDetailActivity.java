package com.example.recyclerviewtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class TutorDetailActivity extends AppCompatActivity {
    private TextView textView;
    private ViewPager2 viewPager2;
    private TutorAdapter tutorAdapter;
    private  String [] names;
    private Toolbar mtoolbar;
    private Intent i;
    private String module;
    private ArrayList<Tutors> tutorsList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_detail);

        mtoolbar = findViewById(R.id.toolbarTutor);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Swipe");

        i = getIntent();

        if(i != null)
        {

            module = i.getStringExtra("moduleName");

        }

        tutorsList = new ArrayList<>();



        db =FirebaseFirestore.getInstance();


       // EventChangeListener();



        viewPager2 = findViewById(R.id.viewPager);
        names = getResources().getStringArray(R.array.names);

        TutorAdapter tutorAdapter = new TutorAdapter(this, names,tutorsList);
        viewPager2.setAdapter(tutorAdapter);


        db.collection(module).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                for(DocumentSnapshot d:list)
                {
                    Tutors tutor = d.toObject(Tutors.class);
                    tutorsList.add(tutor);
                }
                tutorAdapter.notifyDataSetChanged();
            }
        });








    }
    /*public void EventChangeListener()
    {

        db.collection(module).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot value, FirebaseFirestoreException error) {


                if(error != null)
                {

                    return;

                }
                for(DocumentChange dc : value.getDocumentChanges()){

                    if(dc.getType() == DocumentChange.Type.ADDED)
                    {

                        tutorsList.add(dc.getDocument().toObject(Tutors.class));
                        //tutorAdapter.notifyDataSetChanged();


                    }
                }
            }
        });

    }*/
}