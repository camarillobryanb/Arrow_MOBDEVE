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

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.FeaturedViewHolder>{
    ArrayList<RecommendedHelperClass> recProfs;
    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_IMG = "KEY_IMG";
    public static final String KEY_DESC = "KEY_DESC";

    public RecommendedAdapter(ArrayList<RecommendedHelperClass> recProfs) {
        this.recProfs = recProfs;
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name, desc;
        LinearLayout ll_recProfessor;


        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.featured_image);
            name = itemView.findViewById(R.id.featured_name);
            desc = itemView.findViewById(R.id.featured_desc);
            ll_recProfessor = itemView.findViewById(R.id.ll_rec_card);
        }
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_card_view, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);

        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        RecommendedHelperClass recommendedHelperClass = recProfs.get(position);

        holder.image.setImageResource(recommendedHelperClass.getImage());
        holder.name.setText(recommendedHelperClass.getName());
        holder.desc.setText(recommendedHelperClass.getDescription());
        holder.ll_recProfessor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfessorProfile.class);
                intent.putExtra(KEY_NAME, recProfs.get(position).getName());
                intent.putExtra(KEY_IMG, recProfs.get(position).getImage());
                intent.putExtra(KEY_DESC, recProfs.get(position).getDescription());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recProfs.size();
    }

}
