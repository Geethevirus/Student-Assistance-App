package com.example.recyclerviewtestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    private String [] eventNames, desc;
    private int [] eventImages;
    private UserClickListener userClickLister;
    Context context;

    public EventAdapter(Context context,String [] eventNames, int [] eventImages, String[] desc,UserClickListener userClickLister)
    {
        this.context = context;
        this.eventNames = eventNames;
        this.eventImages = eventImages;
        this.userClickLister = userClickLister;
        this.desc = desc;

    }
    public interface UserClickListener{

        void selectedUser(String eventName,int imageRes);
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.event_row,parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {


        holder.imageView.setImageResource(eventImages[position]);
        holder.textView.setText(eventNames[position]);
        holder.descView.setText(desc[position]);

        String eventName = eventNames[position];
        int img = eventImages[position];

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                userClickLister.selectedUser(eventName, img);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventImages.length;
    }

    public class EventViewHolder extends  RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        ImageView imageView;
        TextView textView, descView;
        public EventViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.eventImg);
            textView = itemView.findViewById(R.id.eventName);
            descView = itemView.findViewById(R.id.descEvent);

        }
    }
}
