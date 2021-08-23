package com.example.arrow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private ViewPager screenPager;
//    OnboardViewPagerAdapter onboardViewPagerAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_onboard);
//
//        List<OnboardItem> mList = new ArrayList<>();
//        mList.add(new OnboardItem("Find the right Professors for You!",
//                "Find the right professors to take in your class!",
//                R.drawable.main1));
//        mList.add(new OnboardItem("Find Your Classes",
//                "Find the right professors to take in your class!",
//                R.drawable.main2));
//        mList.add(new OnboardItem("Leave Reviews",
//                "Help other students with their choices and leave reviews about your past classes!",
//                R.drawable.main3));
//
//        screenPager = findViewById(R.id.screen_viewpager);
//        onboardViewPagerAdapter = new OnboardViewPagerAdapter(this, mList);
//        screenPager.setAdapter(onboardViewPagerAdapter);
//    }

    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initComponents();
    }

    private void initComponents() {
        this.btnContinue = findViewById(R.id.btn_continue);
        this.btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}