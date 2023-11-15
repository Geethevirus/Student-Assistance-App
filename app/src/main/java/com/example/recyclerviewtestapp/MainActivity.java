package com.example.recyclerviewtestapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    public CardView lessonC, testC, tutorC, pastpC;
    private TextView nameText,drawer_name,drawer_email;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String userId;
    private Toolbar mtoolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    String nameTry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         mtoolbar = (Toolbar) findViewById(R.id.toolbar);
         drawerLayout = findViewById(R.id.drawer_layout);
         navigationView = findViewById(R.id.nav_view);


         /*---------------ToolBar--------------------*/


        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("");

        /*---------------Navigation Drawer Menu--------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,mtoolbar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.home);

        View header = navigationView.getHeaderView(0);

        /*------ Binding views ------*/
        lessonC = (CardView)findViewById(R.id.lessonCard);
        testC = (CardView)findViewById(R.id.testCard);
        tutorC = (CardView)findViewById(R.id.tutorsCard);
        pastpC = (CardView)findViewById(R.id.papersCard);
        nameText = findViewById(R.id.userName);

        /*---- Instances of the firebase ------*/


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("users").document(userId);

        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(DocumentSnapshot documentSnapshot, FirebaseFirestoreException e) {

                if (e == null) {
                    if (documentSnapshot.exists()) {
                        nameText.setText(documentSnapshot.getString("name"));
                        nameTry = documentSnapshot.getString("name");
                        drawer_name = header.findViewById(R.id.drawer_name);
                        drawer_email = header.findViewById(R.id.drawer_email);
                        drawer_name.setText(documentSnapshot.getString("name"));
                        drawer_email.setText(documentSnapshot.getString("email"));
                    } else {

                        Log.d("MAIN", "No document exists");
                    }
                }
            }
        });

        lessonC.setOnClickListener(this);
        testC.setOnClickListener(this);
        tutorC.setOnClickListener(this);
        pastpC.setOnClickListener(this);




        //drawer_name.setText(nameTry);




    }

    @Override
    public void onClick(View view) {

        Intent i;

        switch (view.getId())
        {
            case R.id.lessonCard:
                i = new Intent(MainActivity.this, LessonsActivity.class);
                startActivity(i);
                break;
            case R.id.testCard:
                i = new Intent(MainActivity.this, EventsActivity.class);
                startActivity(i);
                break;
            case R.id.tutorsCard:
                i = new Intent(MainActivity.this, TutorsActivity.class);
                startActivity(i);
                break;
            case R.id.papersCard:
                Toast.makeText(MainActivity.this,"Not yet Active! !",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Intent i;
        switch(item.getItemId())
        {
            case R.id.home:
                i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.lesson_d:
                i = new Intent(MainActivity.this, LessonsActivity.class);
                startActivity(i);
                finish();
                break;
            case R.id.siginout:
                mAuth.signOut();
                i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
}