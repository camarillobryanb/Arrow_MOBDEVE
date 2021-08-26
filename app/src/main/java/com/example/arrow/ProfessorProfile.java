package com.example.arrow;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

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

//        String rating = "";
//        if(intent.getStringExtra(CollegeProfAdapter.KEY_RATING) != "")
//            rating = intent.getStringExtra(CollegeProfAdapter.KEY_RATING);
//        else if(intent.getStringExtra(AllCardsAdapter.KEY_RATING) != "")
//            rating = intent.getStringExtra(AllCardsAdapter.KEY_RATING);
//        else if(intent.getStringExtra(RecommendedAdapter.KEY_RATING) != "")
//            rating = intent.getStringExtra(RecommendedAdapter.KEY_RATING);
//
//        this.rbRating.setRating(Float.parseFloat(rating));


//        int profimg = 0;
//        if(intent.getIntExtra(CollegeProfAdapter.KEY_IMG, 0) != profimg)
//            profimg = intent.getIntExtra(CollegeProfAdapter.KEY_IMG, 0);
//        else if(intent.getIntExtra(AllCardsAdapter.KEY_IMG, 0) != profimg)
//            profimg = intent.getIntExtra(AllCardsAdapter.KEY_IMG, 0);
//        else if(intent.getIntExtra(RecommendedAdapter.KEY_IMG, 0) != profimg)
//            profimg = intent.getIntExtra(RecommendedAdapter.KEY_IMG, 0);
//
//        this.img.setImageResource(profimg);



        commentsReview();


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