package com.example.arrow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView rvFeaturedProfs;
    RecyclerView rvCurrentProfs;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        rvFeaturedProfs = findViewById(R.id.myFeat_Recycler);
        rvCurrentProfs = findViewById(R.id.myCurr_Recycler);

        displayMyFeaturedProfessors();
        displayMyCurrentProfessors();

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