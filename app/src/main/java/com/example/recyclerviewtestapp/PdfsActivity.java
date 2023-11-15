package com.example.recyclerviewtestapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PdfsActivity extends AppCompatActivity implements PdfAdapter.UserClickListener{

    TextView actionBarName;

    Intent i;
    RecyclerView recyclerView;
    FirebaseFirestore rootRef ;
    String lessonName;
    PdfAdapter pdfAdapter;
    ArrayList<FilePdf> pdfList;
    ImageView imageView;
    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfs);

        mtoolbar = findViewById(R.id.toolbarPdfs);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pdf File(s)");


        recyclerView = findViewById(R.id.recyclerView3);
        imageView = findViewById(R.id.folderImg);

        rootRef  = FirebaseFirestore.getInstance();





        i = getIntent();

        pdfList =  new ArrayList<>();

        if(i != null)
        {

            lessonName = i.getStringExtra("lessonName");


        }

        pdfAdapter = new PdfAdapter(PdfsActivity.this,pdfList,this::selectedUser);
        recyclerView.setAdapter(pdfAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rootRef.collection(lessonName).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();

                for(DocumentSnapshot d:list)
                {
                    FilePdf filePdf = d.toObject(FilePdf.class);
                    pdfList.add(filePdf);
                }

                pdfAdapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    public void selectedUser(String filename, String fileurl) {

        Intent i = new Intent(PdfsActivity.this, PdfViewerActivity.class);
        i.putExtra("filename",filename);
        i.putExtra("fileurl",fileurl);

        startActivity(i);

    }
}