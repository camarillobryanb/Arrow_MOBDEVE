package com.example.arrow;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class AllRecommendedProfs extends AppCompatActivity {

    RecyclerView recommendedProfsRecycler;

    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.all_recommended_profs);

        //Hooks
        recommendedProfsRecycler = findViewById(R.id.allRecommended_Recycler);

        recommendedProfessors();


    }

    private void recommendedProfessors() {
        recommendedProfsRecycler.setHasFixedSize(true);
        recommendedProfsRecycler.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));

        ArrayList<RecommendedHelperClass> recommendedProfs = new ArrayList<>();

        recommendedProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));
        recommendedProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "Team Sports Professor"));
        recommendedProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "History Professor"));
        recommendedProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));
        recommendedProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "Team Sports Professor"));
        recommendedProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "History Professor"));
        recommendedProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));
        recommendedProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "Team Sports Professor"));
        recommendedProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "History Professor"));
        recommendedProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));



        adapter = new AllCardsAdapter(recommendedProfs);
        recommendedProfsRecycler.setAdapter(adapter);
    }
}