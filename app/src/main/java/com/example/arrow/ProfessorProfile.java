package com.example.arrow;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class ProfessorProfile extends AppCompatActivity {

    RecyclerView commentsRecycler;
    TextView name;
    ImageView img;

    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.professor_profile);

        //Hooks
        commentsRecycler = findViewById(R.id.comments_Recycler);
        name = findViewById(R.id.name);

        Intent intent = getIntent();

        String profname = "";
        if(intent.getStringExtra(CollegeProfAdapter.KEY_NAME) != "")
            profname = intent.getStringExtra(CollegeProfAdapter.KEY_NAME);
        else if(intent.getStringExtra(AllCardsAdapter.KEY_NAME) != "")
            profname = intent.getStringExtra(AllCardsAdapter.KEY_NAME);
        else if(intent.getStringExtra(RecommendedAdapter.KEY_NAME) != "")
            profname = intent.getStringExtra(RecommendedAdapter.KEY_NAME);

        this.name.setText(profname);

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