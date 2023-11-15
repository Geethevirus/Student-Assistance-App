package com.example.recyclerviewtestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

     String description [];
     int imgs [];
     Context ct;

    public MyAdapter(Context ct, String desc[], int images[])
    {
        this.ct = ct;
        this.description = desc;
        this.imgs = images;


    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.my_row,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( MyAdapter.MyViewHolder holder, int position) {

        holder.txt.setText(description[position]);
        holder.img.setImageResource(imgs[position]);
    }

    @Override
    public int getItemCount() {
        return imgs.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt ;
        ImageView img;
        public MyViewHolder( View itemView) {
            super(itemView);

            txt = itemView.findViewById(R.id.name_txt);
            img = itemView.findViewById(R.id.imageView);

        }
    }
}
