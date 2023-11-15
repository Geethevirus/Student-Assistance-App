package com.example.recyclerviewtestapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TutorAdapter extends RecyclerView.Adapter<TutorAdapter.TutorViewHolder> {

    private String [] names;
    private ArrayList<Tutors> tutorsList;
    private Context context;


    public TutorAdapter(Context context, String [] names,ArrayList<Tutors> tutors)
    {
        this.context = context;
        this.names = names;
        tutorsList = tutors;



    }
    @Override
    public TutorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.tutor_item,parent,false);

        return new TutorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TutorViewHolder holder, int position) {

        Tutors tutor = tutorsList.get(position);

        //holder.textView.setText(names[position]);

        String email = tutor.getEmail();
        String name = tutor.getName();
        String about = tutor.getAbout();
        String modules = tutor.getModule();

        holder.textViewName.setText(name);
        holder.textViewAbout.setText(about);
        holder.textViewModules.setText(modules);



        String subject = "Assistance";

        holder.btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_SUBJECT, new String[]{subject});
                context.startActivity(Intent.createChooser(intent,"Chooses app"));

            }
        });


    }

    @Override
    public int getItemCount() {
        return tutorsList.size();
    }

    public class TutorViewHolder extends RecyclerView.ViewHolder{

        Button btnEmail;
        TextView textViewName,textViewAbout, textViewModules;
        public TutorViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.tutor_name);
            btnEmail = itemView.findViewById(R.id.emailbtn);
            textViewAbout = itemView.findViewById(R.id.aboutTutor);
            textViewModules = itemView.findViewById(R.id.tutorModules);

        }
    }
}
