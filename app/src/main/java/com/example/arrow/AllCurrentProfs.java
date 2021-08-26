package com.example.arrow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class AllCurrentProfs extends AppCompatActivity {

    RecyclerView.Adapter adapter;
    
    private int featuredCount = 0;
    private ArrayList<String> allFeatured = new ArrayList<String>();
    
    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    // Views
    ArrayList<MyCardHelperClass> dataProfs = new ArrayList<>();
    
    
    RecyclerView currentProfsRecycler;
    ImageView iv_home;
    ImageView iv_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.all_current_profs);

        //Hooks
        currentProfsRecycler = findViewById(R.id.allProfs_Recycler);

        //currentProfessors();

        this.viewHome();
        this.viewProfile();

        initFirebase();

        currentProfsRecycler = findViewById(R.id.allProfs_Recycler);
      
        //displayAllProfessors();

    }


    private void initFirebase() {
        this.mAuth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance("https://arrow-848c3-default-rtdb.asia-southeast1.firebasedatabase.app/");
    }

    private void viewHome() {
        this.iv_home= findViewById(R.id.iv_home);
        this.iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AllCurrentProfs.this, userDashboard.class);
                startActivity(i);
            }
        });
    }

    private void viewProfile() {
        this.iv_profile= findViewById(R.id.iv_profile);
        this.iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AllCurrentProfs.this, ProfileActivity.class);
                startActivity(i);
            }
        });
    }


    private void currentProfessors() {
        currentProfsRecycler.setHasFixedSize(true);
        currentProfsRecycler.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));

        ArrayList<RecommendedHelperClass> currProfs = new ArrayList<>();

        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));
        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "Team Sports Professor"));
        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "History Professor"));
        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));
        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "Team Sports Professor"));
        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "History Professor"));
        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));
        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "Team Sports Professor"));
        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "History Professor"));
        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));



        adapter = new AllCardsAdapter(currProfs);
        currentProfsRecycler.setAdapter(adapter);
    }
}