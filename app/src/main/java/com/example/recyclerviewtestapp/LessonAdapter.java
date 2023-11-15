package com.example.recyclerviewtestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.MyViewHolder>{

    private Context context;
    private String lessonNames [];
    public UserClickListener userClicKListener;

    public LessonAdapter(Context context, String lessonNames[],UserClickListener userClicKListener)
    {
        this.context = context;
        this.lessonNames = lessonNames;
        this.userClicKListener = userClicKListener;
    }
    public interface UserClickListener
    {
        void selectedUser(String lessonName);

    }


    @Override
    public LessonAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.lesson_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LessonAdapter.MyViewHolder holder, int position) {

        String lesson_name = lessonNames[position];
        holder.text1.setText(lessonNames[position]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userClicKListener.selectedUser(lesson_name);

            }
        });

    }

    @Override
    public int getItemCount() {
        return lessonNames.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView text1;
        public MyViewHolder(View itemView) {
            super(itemView);

            text1 = itemView.findViewById(R.id.lessonName);
        }
    }
}
