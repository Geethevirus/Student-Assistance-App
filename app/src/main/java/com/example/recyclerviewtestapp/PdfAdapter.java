package com.example.recyclerviewtestapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.PdfViewHolder> {

    ArrayList<FilePdf> pdfs;
    Context context;
    private UserClickListener userClickListener;



    public PdfAdapter(Context context, ArrayList<FilePdf> pdfs, UserClickListener userClickListener)
    {

        this.pdfs = pdfs;
        this.context = context;
        this.userClickListener = userClickListener;

    }
    public interface UserClickListener
    {

        void selectedUser(String filename, String fileurl);

    }
    @Override
    public PdfViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.lesson_row,parent,false);

        return new PdfViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PdfViewHolder holder, int position) {

        holder.textView.setText(pdfs.get(position).getFilename());
        holder.img.setImageResource(R.drawable.pdf_file);

        String filename = pdfs.get(position).getFilename();
        String fileurl = pdfs.get(position).getFileurl();




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

                userClickListener.selectedUser(filename,fileurl);

            }
        });


    }

    @Override
    public int getItemCount() {
        return pdfs.size();
    }

    public class PdfViewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView textView;

        public PdfViewHolder(View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.folderImg);
            textView = itemView.findViewById(R.id.lessonName);

        }
    }
}
