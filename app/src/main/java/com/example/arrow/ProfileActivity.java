package com.example.arrow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    RecyclerView rvFeaturedProfs;
    RecyclerView rvCurrentProfs;
    RecyclerView.Adapter adapter;
    ImageView iv_home;
    TextView tvLogout;
    TextView tvFeatProf;
    TextView tvFname;
    TextView tvLname;

    // Counts
    private int featuredCount = 0;
    private ArrayList<String> allFeatured = new ArrayList<String>();
    private int currentCount = 0;
    private ArrayList<String> allCurrent = new ArrayList<String>();

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    // Views
    ArrayList<MyCardHelperClass> dataProfs = new ArrayList<>();
    ArrayList<MyCardHelperClass> dataProfsCurr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);

        initFirebase();

        tvFname = findViewById(R.id.profile_firstName);
        tvLname = findViewById(R.id.profile_lastName);

        changeHeading();

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

    private void changeHeading() {
        database.getReference().child("users").child(mAuth.getUid())
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    String fname = String.valueOf(snapshot.child("fName").getValue());
                    String lname = String.valueOf(snapshot.child("lName").getValue());
                    tvFname.setText(fname);
                    tvLname.setText(lname);
                }
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
        getFeaturedCount();
    }

    private void getFeaturedCount() {
        database.getReference().child("users").child(mAuth.getUid()).child("featured")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("FIREBASE", ""+snapshot.getChildrenCount());
                        featuredCount = (int) snapshot.getChildrenCount();
                        getFeatured();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void getFeatured() {
        Log.d("FIREBASE", ""+featuredCount);
        DatabaseReference tempdb = database.getReference().child("users").child(mAuth.getUid()).child("featured");

        for (int i = 0; i < featuredCount; i++){
            tempdb.child(Integer.toString(i))
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String value = snapshot.getValue().toString();
                            allFeatured.add(value);
                            Log.d("ALL FEATURED", ""+allFeatured);
                            if (allFeatured.size() == featuredCount){
                                displayFeatured();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }
    }

    private void displayFeatured() {
        rvFeaturedProfs.setHasFixedSize(true);
        rvFeaturedProfs.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        for (int i = 0; i < featuredCount; i++){
            database.getReference().child("professors").child(allFeatured.get(i))
                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()){
                        DataSnapshot snapshot = task.getResult();

                        String pronoun = String.valueOf(snapshot.child("pronoun").getValue());
                        String lname = String.valueOf(snapshot.child("lName").getValue());
                        String college = String.valueOf(snapshot.child("college").getValue());

                        dataProfs.add(new MyCardHelperClass(pronoun + " " + lname, college, R.drawable.prof_sample));

                        adapter = new MyProfsAdapter(dataProfs);
                        rvFeaturedProfs.setAdapter(adapter);
                    }
                }
            });
        }
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