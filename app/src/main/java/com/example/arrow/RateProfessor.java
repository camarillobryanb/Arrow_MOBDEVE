package com.example.arrow;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RateProfessor extends AppCompatActivity {

    private Spinner sp_editCollege;
    ImageView iv_home;
    ImageView iv_profile;

    RadioGroup rgSync;
    RadioButton rbSync;

    RadioGroup rgAttendance;
    RadioButton rbAttendance;

    RadioGroup rgGrading;
    RadioButton rbGrading;

    RatingBar rateOverall;

    EditText etReview;

    Button btnSubmit;

    // Firebase
    FirebaseAuth mAuth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.rate_professor);

        this.viewHome();
        this.viewProfile();

        initFirebase();
        initComponents();
    }

    private void initFirebase() {
        this.mAuth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance("https://arrow-848c3-default-rtdb.asia-southeast1.firebasedatabase.app/");
    }

    private void initComponents() {
        this.rgSync = findViewById(R.id.rg_sync);
        this.rgAttendance = findViewById(R.id.rg_attendance);
        this.rgGrading = findViewById(R.id.rg_gradingcriteria);
        this.rateOverall = findViewById(R.id.ratingBarOverall);
        this.etReview = findViewById(R.id.et_reviewdesc);
        this.btnSubmit = findViewById(R.id.btn_submitreview);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                int selectedSync = rgSync.getCheckedRadioButtonId();
                rbSync = (RadioButton) findViewById(selectedSync);
                int sync = convertSyncToInt(rbSync.getText().toString());

                int selectedAttendance = rgAttendance.getCheckedRadioButtonId();
                rbAttendance = (RadioButton) findViewById(selectedAttendance);
                int attendance = convertAttendanceToInt(rbAttendance.getText().toString());

                int selectedGrading = rgGrading.getCheckedRadioButtonId();
                rbGrading = (RadioButton) findViewById(selectedGrading);
                int grading = convertGradingToInt(rbGrading.getText().toString());

                float rating = (float) rateOverall.getRating();

                String comment = etReview.getText().toString().trim();

                Review review = new Review(sync, attendance, grading, rating, comment);

                database.getReference().child("professors").child("0000001").child("reviews").child("0")
                        .setValue(review).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            successReview();
                        }
                    }
                });
            }
        });
    }

    private int convertSyncToInt(String text){
        if (text.equals("None")){
            return 1;
        } else if (text.equals("Few")){
            return 2;
        } else if (text.equals("Moderate")){
            return 3;
        } else if (text.equals("More Sync")){
            return 4;
        } else {
            return 5;
        }
    }

    private int convertAttendanceToInt(String text){
        if (text.equals("None")){
            return 1;
        } else {
            return 2;
        }
    }

    private int convertGradingToInt(String text){
        if (text.equals("Half Output/Exams")){
            return 1;
        } else if (text.equals("Pure Output-based")){
            return 2;
        } else if (text.equals("More Output-based")){
            return 3;
        } else if (text.equals("More Exam based")){
            return 4;
        } else {
            return 5;
        }
    }

    private void successReview() {
        Toast.makeText(this, "Review Added.", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(RateProfessor.this, userDashboard.class);
        startActivity(i);
    }

    private void viewHome() {
        this.iv_home= findViewById(R.id.iv_home);
        this.iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RateProfessor.this, userDashboard.class);
                startActivity(i);
            }
        });
    }

    private void viewProfile() {
        this.iv_profile= findViewById(R.id.iv_profile);
        this.iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RateProfessor.this, ProfileActivity.class);
                startActivity(i);
            }
        });
    }

}
