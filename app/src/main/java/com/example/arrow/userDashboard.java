package com.example.arrow;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class userDashboard extends AppCompatActivity {

    RecyclerView recommendedRecycler;
    RecyclerView CollegeProfessorsRecycler;
    RecyclerView CurrentProfessorsRecycler;

    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.user_dashboard);

        //Hooks
        recommendedRecycler = findViewById(R.id.recommendedProfs_recycler);
        CollegeProfessorsRecycler = findViewById(R.id.college_profs_recycler);
        CurrentProfessorsRecycler = findViewById(R.id.current_profs_recycler);


        recommendedProfessors();
        yourCollegeProfessors();
        CurrentProfessors();


    }

    private void recommendedProfessors() {
        recommendedRecycler.setHasFixedSize(true);
        recommendedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<RecommendedHelperClass> recProfs = new ArrayList<>();

        recProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));

        adapter = new RecommendedAdapter(recProfs);
        recommendedRecycler.setAdapter(adapter);
    }


    private void yourCollegeProfessors() {
        CollegeProfessorsRecycler.setHasFixedSize(true);
        CollegeProfessorsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }
    private void CurrentProfessors() {
        CurrentProfessorsRecycler.setHasFixedSize(true);
        recommendedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }


}