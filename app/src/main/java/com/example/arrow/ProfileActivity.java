package com.example.arrow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView rvFeaturedProfs;
    RecyclerView rvCurrentProfs;
    RecyclerView.Adapter adapter;
    ImageView iv_home;
    TextView tvLogout;
    TextView tvFeatProf;

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        initFirebase();

        rvFeaturedProfs = findViewById(R.id.myFeat_Recycler);
        rvCurrentProfs = findViewById(R.id.myCurr_Recycler);

        tvLogout = findViewById(R.id.logout_name);

        displayMyFeaturedProfessors();
        displayMyCurrentProfessors();

        this.viewAddFeaturedPage();
        this.viewHome();

        tvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void viewAddFeaturedPage() {
        this.tvFeatProf = findViewById(R.id.edit_feat_profs);
        this.tvFeatProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, AddFeaturedProfActivity.class);
                startActivity(i);
            }
        });
    }

    private void initFirebase() {
        this.mAuth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance("https://arrow-848c3-default-rtdb.asia-southeast1.firebasedatabase.app/");
    }

    private void displayMyFeaturedProfessors() {
        rvFeaturedProfs.setHasFixedSize(true);
        rvFeaturedProfs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MyCardHelperClass> dataProfs = new ArrayList<>();

        dataProfs.add(new MyCardHelperClass("Mrs. Santos", "Team Sports Professor" , R.drawable.prof_sample));
        dataProfs.add(new MyCardHelperClass("Mrs. Cruz", "IT Professor", R.drawable.prof_sample));
        dataProfs.add(new MyCardHelperClass("Mr. Perez", "History Professor", R.drawable.prof_sample));

        adapter = new MyProfsAdapter(dataProfs);
        rvFeaturedProfs.setAdapter(adapter);
    }

    private void viewHome() {
        this.iv_home= findViewById(R.id.iv_home);
        this.iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, userDashboard.class);
                startActivity(i);
            }
        });
    }

    private void displayMyCurrentProfessors() {
        rvCurrentProfs.setHasFixedSize(true);
        rvCurrentProfs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<MyCardHelperClass> dataProfs = new ArrayList<>();

        dataProfs.add(new MyCardHelperClass("Mrs. Santos", "Team Sports Professor" , R.drawable.prof_sample));
        dataProfs.add(new MyCardHelperClass("Mrs. Cruz", "IT Professor", R.drawable.prof_sample));
        dataProfs.add(new MyCardHelperClass("Mr. Perez", "History Professor", R.drawable.prof_sample));


        adapter = new MyProfsAdapter(dataProfs);
        rvCurrentProfs.setAdapter(adapter);
    }
}