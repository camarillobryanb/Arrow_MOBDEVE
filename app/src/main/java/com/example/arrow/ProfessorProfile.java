package com.example.arrow;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

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


public class ProfessorProfile extends AppCompatActivity {

    RecyclerView commentsRecycler;
    TextView name;
    ImageView img;
    ImageView iv_profile;
    ImageView iv_home;
    ImageView commentButton;
    RatingBar rbRating;

    // Firebase
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;

    ArrayList<CommentHelperClass> commentItem = new ArrayList<>();
    ArrayList<String> lNames = new ArrayList<>();

    int commentCount = 0;
    int collegeProfessorsCount = 0;
    String profId;
    int i;

    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.professor_profile);

        //Hooks
        commentsRecycler = findViewById(R.id.comments_Recycler);
        name = findViewById(R.id.name);
        rbRating = findViewById(R.id.prof_rating);


        Intent intent = getIntent();

        String profname = "";
        if(intent.getStringExtra(CollegeProfAdapter.KEY_NAME) != "")
            profname = intent.getStringExtra(CollegeProfAdapter.KEY_NAME);
        else if(intent.getStringExtra(AllCardsAdapter.KEY_NAME) != "")
            profname = intent.getStringExtra(AllCardsAdapter.KEY_NAME);
        else if(intent.getStringExtra(RecommendedAdapter.KEY_NAME) != "")
            profname = intent.getStringExtra(RecommendedAdapter.KEY_NAME);

        this.name.setText(profname);

        float rating = -1;
        if(intent.getFloatExtra(CollegeProfAdapter.KEY_RATING, 0) != -1)
            rating = intent.getFloatExtra(CollegeProfAdapter.KEY_RATING, 0);
        else if(intent.getFloatExtra(AllCardsAdapter.KEY_RATING, 0) != -1)
            rating = intent.getFloatExtra(AllCardsAdapter.KEY_RATING, 0);
        else if(intent.getFloatExtra(RecommendedAdapter.KEY_RATING, 0) != -1)
            rating = intent.getFloatExtra(RecommendedAdapter.KEY_RATING, 0);

        this.rbRating.setRating(rating);


//        int profimg = -1;
//        if(intent.getIntExtra(CollegeProfAdapter.KEY_IMG, 0) != -1)
//            profimg = intent.getIntExtra(CollegeProfAdapter.KEY_IMG, 0);
//        else if(intent.getIntExtra(AllCardsAdapter.KEY_IMG, 0) != -1)
//            profimg = intent.getIntExtra(AllCardsAdapter.KEY_IMG, 0);
//        else if(intent.getIntExtra(RecommendedAdapter.KEY_IMG, 0) != -1)
//            profimg = intent.getIntExtra(RecommendedAdapter.KEY_IMG, 0);
//
//        this.img.setImageResource(profimg);

        initFirebase();
//        getCollegeProfessorsCount(profname);
//        getDetailsfromID("0000001");
        getDetailsfromID("0000001");

        this.viewHome();
        this.viewProfile();
        this.rateProf();


    }
    private void viewHome() {
        this.iv_home= findViewById(R.id.iv_home);
        this.iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfessorProfile.this, userDashboard.class);
                startActivity(i);
            }
        });
    }

    private void viewProfile() {
        this.iv_profile= findViewById(R.id.iv_profile);
        this.iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfessorProfile.this, ProfileActivity.class);
                startActivity(i);
            }
        });
    }

    private void rateProf() {
        this.commentButton= findViewById(R.id.commentButton);
        this.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfessorProfile.this, RateProfessor.class);
                startActivity(i);
            }
        });
    }

    private void initFirebase() {
        this.mAuth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance("https://arrow-848c3-default-rtdb.asia-southeast1.firebasedatabase.app/");
    }


    private void getDetailsfromID (String ID){
        database.getReference().child("professors").child(ID)
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    DataSnapshot snapshot = task.getResult();

                    String fName = String.valueOf(snapshot.child("fName").getValue());
                    String lName = String.valueOf(snapshot.child("lName").getValue());
                    String college = String.valueOf(snapshot.child("college").getValue());

                    getCommentCount(ID, fName, lName, college);
                }
            }
        });
    }

    private void getCommentCount(String ID, String fName, String lName, String college) {
        database.getReference().child("professors").child(ID).child("reviews")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("FIREBASE", ""+snapshot.getChildrenCount());
                        commentCount = (int) snapshot.getChildrenCount();
                        displayRated(ID, fName, lName, college);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    private void displayRated(String ID, String fName, String lName, String college) {
        commentsRecycler.setHasFixedSize(true);
        commentsRecycler.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));

        for (i = 0; i < commentCount; i++){
            database.getReference().child("professors").child(ID).child("reviews").child(Integer.toString(i))
                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()){
                        DataSnapshot snapshot = task.getResult();


                        float rating = Float.parseFloat(String.valueOf(snapshot.child("overall").getValue()));
                        int learning = Integer.parseInt(String.valueOf(snapshot.child("sync").getValue()));
                        int grading = Integer.parseInt(String.valueOf(snapshot.child("grading").getValue()));
                        int attendance = Integer.parseInt(String.valueOf(snapshot.child("attendance").getValue()));
                        String review = String.valueOf(snapshot.child("comment").getValue());

//                        commentItem.add(new CommentHelperClass(fName, lName, college, learning, attendance, grading, rating, review));

                        commentItem.add(new CommentHelperClass(fName, lName, college, 2, 3, 3, 2, "review"));

                        adapter = new CommentCardAdapter(commentItem);
                        commentsRecycler.setAdapter(adapter);
                    }
                }
            });
        }
    }


    private void commentsReview() {
        commentsRecycler.setHasFixedSize(true);
        commentsRecycler.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));

        ArrayList<CommentHelperClass> commentItem = new ArrayList<>();

        commentItem.add(new CommentHelperClass("Jane", "Dela Cruz", "BSIT", "Pure Synchronous",
                "Imporant Attendance", "Pure Exams", getResources().getString(R.string.rev_comment)));
        commentItem.add(new CommentHelperClass("Juan", "Santos", "BSIT", "Pure Synchronous",
                "Imporant Attendance", "Pure Exams", getResources().getString(R.string.rev_comment)));
        commentItem.add(new CommentHelperClass("John", "Perez", "BSIT", "Pure Synchronous",
                "Imporant Attendance", "Pure Exams", getResources().getString(R.string.rev_comment)));
        commentItem.add(new CommentHelperClass("Peter", "Parker", "BSIT", "Pure Synchronous",
                "Imporant Attendance", "Pure Exams", getResources().getString(R.string.rev_comment)));
        commentItem.add(new CommentHelperClass("Tony", "Stark", "BSIT", "Pure Synchronous",
                "Imporant Attendance", "Pure Exams", getResources().getString(R.string.rev_comment)));


        adapter = new CommentCardAdapter(commentItem);
        commentsRecycler.setAdapter(adapter);
    }
}