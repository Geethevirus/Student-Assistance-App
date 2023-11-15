package com.example.recyclerviewtestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TutorFilesAdapter extends RecyclerView.Adapter<TutorFilesAdapter.TutorFileViewHolder> {

    private int img;
    private Context context;
    private String [] lessonsNames;
    private  UserClickListener userClickListener;

    public TutorFilesAdapter(Context context, int img, String [] lessonsNames,UserClickListener userClickListener)
    {
        this.context = context;
        this.img = img;
        this.lessonsNames = lessonsNames;
        this.userClickListener = userClickListener;

    }

    public interface UserClickListener
    {
        void selectedUser(String lessonName);

    }



    @Override
    public TutorFileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.lesson_row,parent,false);

        return new TutorFileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TutorFileViewHolder holder, int position) {

        holder.imageView.setImageResource(img);
        String lessonName = lessonsNames[position];


        holder.textView.setText(lessonsNames[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userClickListener.selectedUser( lessonName);

            }
        });


    }

    @Override
    public int getItemCount()
    {
        return lessonsNames.length;
    }

    public class TutorFileViewHolder extends RecyclerView.ViewHolder{

        TextView textView ;
        ImageView imageView;
        public TutorFileViewHolder(View itemView) {
            super(itemView);

        textView = itemView.findViewById(R.id.lessonName);
        imageView = itemView.findViewById(R.id.folderImg);

        }
    }
}
