package com.example.arrow;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class MyProfsAdapter extends RecyclerView.Adapter<MyProfsAdapter.FeaturedViewHolder>{
    ArrayList<MyCardHelperClass> dataProfs;
    String KEY_NAME = "KEY_NAME";
    String KEY_IMG = "KEY_IMG";
    String KEY_DESC = "KEY_DESC";

    public MyProfsAdapter(ArrayList<MyCardHelperClass> dataProfs) {
        this.dataProfs = dataProfs;
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, desc;
        LinearLayout ll_professor;


        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.featured_image);
            name = itemView.findViewById(R.id.featured_name);
            desc = itemView.findViewById(R.id.featured_desc);
            ll_professor = itemView.findViewById(R.id.ll_allcard);
        }
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_card, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);

        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        MyCardHelperClass myCardHelperClass = dataProfs.get(position);

        holder.image.setImageResource(myCardHelperClass.getImage());
        holder.name.setText(myCardHelperClass.getName());
        holder.desc.setText(myCardHelperClass.getDescription());
        holder.ll_professor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfessorProfile.class);
                intent.putExtra(KEY_NAME, dataProfs.get(position).getName());
                intent.putExtra(KEY_IMG, dataProfs.get(position).getImage());
                intent.putExtra(KEY_DESC, dataProfs.get(position).getDescription());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataProfs.size();
    }

}
