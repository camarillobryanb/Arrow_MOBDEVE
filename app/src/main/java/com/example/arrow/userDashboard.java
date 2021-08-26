package com.example.arrow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    ImageView iv_home;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    ArrayList<RecommendedHelperClass> currProfs = new ArrayList<>();

    int collegeProfessorsCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.user_dashboard);

        //Hooks
        recommendedRecycler = findViewById(R.id.recommendedProfs_recycler);
        CollegeProfessorsRecycler = findViewById(R.id.college_profs_recycler);
        CurrentProfessorsRecycler = findViewById(R.id.current_profs_recycler);

        initFirebase();

        //Adapter per section
        recommendedProfessors();
        yourCollegeProfessors();
        getCollegeProfessorsCount();


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

    private void initFirebase() {
        this.mAuth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance("https://arrow-848c3-default-rtdb.asia-southeast1.firebasedatabase.app/");
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

        recProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor", Float.parseFloat("4.3")));
        recProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "Philosophy Professor", Float.parseFloat("2.5")));
        recProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "Team Sports Professor", Float.parseFloat("3.7")));


        adapter = new RecommendedAdapter(recProfs);
        recommendedRecycler.setAdapter(adapter);
    }


    private void yourCollegeProfessors() {
        CollegeProfessorsRecycler.setHasFixedSize(true);
        CollegeProfessorsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ArrayList<RecommendedHelperClass> collProfs = new ArrayList<>();

        collProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor", Float.parseFloat("4.3")));
        collProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "Philosophy Professor", Float.parseFloat("2.5")));
        collProfs.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "Team Sports Professor", Float.parseFloat("3.7")));

        adapter = new CollegeProfAdapter(collProfs);
        CollegeProfessorsRecycler.setAdapter(adapter);

    }

    private void getCollegeProfessorsCount() {
        database.getReference().child("professors")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("FIREBASE", ""+snapshot.getChildrenCount());
                        collegeProfessorsCount = (int) snapshot.getChildrenCount();
                        CurrentProfessors();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void CurrentProfessors() {
        CurrentProfessorsRecycler.setHasFixedSize(true);
        CurrentProfessorsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        for (int i = 1; i <= collegeProfessorsCount; i++){
            database.getReference().child("professors").child(String.format("%07d", i))
                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()){
                        DataSnapshot snapshot = task.getResult();

                        String pronoun = String.valueOf(snapshot.child("pronoun").getValue());
                        String lname = String.valueOf(snapshot.child("lName").getValue());
                        String college = String.valueOf(snapshot.child("college").getValue());
                        float rating = Float.parseFloat(String.valueOf(snapshot.child("overallRating").getValue()));

                        currProfs.add(new RecommendedHelperClass(R.drawable.prof_sample ,pronoun + " " + lname, college, rating));

                        adapter = new CollegeProfAdapter(currProfs);
                        CurrentProfessorsRecycler.setAdapter(adapter);
                    }
                }
            });
        }
    }


}