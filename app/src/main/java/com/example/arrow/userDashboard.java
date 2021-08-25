package com.example.arrow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class userDashboard extends AppCompatActivity {

    RecyclerView recommendedRecycler;
    RecyclerView CollegeProfessorsRecycler;
    RecyclerView CurrentProfessorsRecycler;

    RecyclerView.Adapter adapter;

    TextView tv_viewAllCollegeProfs;
    TextView tv_viewAllCurrentProfs;
    LinearLayout ll_viewAllRecommendedProfs;
    LinearLayout ll_recProfessor;
    RelativeLayout rl_collProfessor;

    ImageView ivMyProfile;
    ImageView ivSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.user_dashboard);

        //Hooks
        recommendedRecycler = findViewById(R.id.recommendedProfs_recycler);
        CollegeProfessorsRecycler = findViewById(R.id.college_profs_recycler);
        CurrentProfessorsRecycler = findViewById(R.id.current_profs_recycler);


        //Adapter per section
        recommendedProfessors();
        yourCollegeProfessors();
        CurrentProfessors();

        //Onclick of View All buttons
        this.viewAllCollegeProfs();
        this.viewAllCurrentProfs();
        this.viewAllRecommendedProfs();
        this.viewMyProfile();
        this.searchResults();
    }


    private void viewMyProfile() {
        this.ivMyProfile = findViewById(R.id.iv_profile);
        this.ivMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userDashboard.this, ProfileActivity.class);
                startActivity(i);
            }
        });
    }

    private void searchResults() {
        this.ivSearch = findViewById(R.id.iv_search);
        this.ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userDashboard.this, SearchResultsActivity.class);
                startActivity(i);
            }
        });
    }

    private void viewAllCollegeProfs() {
        this.tv_viewAllCollegeProfs = findViewById(R.id.tv_viewcollegeprofs);
        this.tv_viewAllCollegeProfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userDashboard.this, AllCollegeProfs.class);
                startActivity(i);
            }
        });
    }

    private void viewAllCurrentProfs() {
        this.tv_viewAllCurrentProfs = findViewById(R.id.tv_viewcurrentprofs);
        this.tv_viewAllCurrentProfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userDashboard.this, AllCurrentProfs.class);
                startActivity(i);
            }
        });
    }

    private void viewAllRecommendedProfs() {
        this.ll_viewAllRecommendedProfs = findViewById(R.id.featured_background);
        this.ll_viewAllRecommendedProfs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userDashboard.this, AllRecommendedProfs.class);
                startActivity(i);
            }
        });
    }

    private void recommendedProfessors() {
        recommendedRecycler.setHasFixedSize(true);
        recommendedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<RecommendedHelperClass> recProfs = new ArrayList<>();

        recProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));
        recProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "Team Sports Professor"));
        recProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "History Professor"));


        adapter = new RecommendedAdapter(recProfs);
        recommendedRecycler.setAdapter(adapter);
    }


    private void yourCollegeProfessors() {
        CollegeProfessorsRecycler.setHasFixedSize(true);
        CollegeProfessorsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<RecommendedHelperClass> collProfs = new ArrayList<>();

        collProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));
        collProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "Team Sports Professor"));
        collProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "History Professor"));

        adapter = new CollegeProfAdapter(collProfs);
        CollegeProfessorsRecycler.setAdapter(adapter);

    }
    private void CurrentProfessors() {
        CurrentProfessorsRecycler.setHasFixedSize(true);
        CurrentProfessorsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<RecommendedHelperClass> currProfs = new ArrayList<>();

        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));
        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "Philosophy Professor"));
        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "Team Sports Professor"));


        adapter = new CollegeProfAdapter(currProfs);
        CurrentProfessorsRecycler.setAdapter(adapter);
    }


}