package com.example.arrow;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class userDashboard extends AppCompatActivity {

    RecyclerView featuredRecycler;
    RecyclerView  mostViewedRecycler;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.user_dashboard);

        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        mostViewedRecycler = findViewById(R.id.most_viewed_recycler);



        featuredRecycler();
        yourCollegeProfessors();



    }


    private void yourCollegeProfessors() {
    }

    private void featuredRecycler(){
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> recProfs = new ArrayList<>();

        recProfs.add(new FeaturedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));


        adapter = new FeaturedAdapter(recProfs);
        featuredRecycler.setAdapter(adapter);
    }


}