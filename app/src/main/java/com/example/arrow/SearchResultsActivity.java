package com.example.arrow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class SearchResultsActivity extends AppCompatActivity {

    RecyclerView searchResultsRecycler;
    TextView tvSubmitRequest;
    TextView tvSearchedInfo;
    ImageView iv_home;
    ImageView iv_profile;

    // Search
    String searchItem;

    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.search_results);

        Intent i = getIntent();
        tvSearchedInfo = findViewById(R.id.searched_info);
        searchItem = i.getStringExtra("SEARCH");
        tvSearchedInfo.setText(searchItem);

        //Hooks
        searchResultsRecycler = findViewById(R.id.searchResults_Recycler);

        displaySearchResults();
        submitRequest();

        this.viewHome();
        this.viewProfile();
    }

    private void viewHome() {
        this.iv_home= findViewById(R.id.iv_home);
        this.iv_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchResultsActivity.this, userDashboard.class);
                startActivity(i);
            }
        });
    }

    private void viewProfile() {
        this.iv_profile= findViewById(R.id.iv_profile);
        this.iv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchResultsActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
    }

    private void submitRequest() {
        this.tvSubmitRequest = findViewById(R.id.submit_request);
        this.tvSubmitRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchResultsActivity.this, SubmitRequestActivity.class);
                startActivity(i);
            }
        });
    }

    private void displaySearchResults() {
        searchResultsRecycler.setHasFixedSize(true);
        searchResultsRecycler.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));

        ArrayList<RecommendedHelperClass> resultItem = new ArrayList<>();

        resultItem.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));
        resultItem.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "Team Sports Professor"));
        resultItem.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "History Professor"));
        resultItem.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));
        resultItem.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "Team Sports Professor"));
        resultItem.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "History Professor"));
        resultItem.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));
        resultItem.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Santos", "Team Sports Professor"));
        resultItem.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mr. Perez", "History Professor"));
        resultItem.add(new RecommendedHelperClass(R.drawable.prof_sample, "Mrs. Cruz", "IT Professor"));

        adapter = new AddFeaturedProfAdapter(resultItem);
        searchResultsRecycler.setAdapter(adapter);
    }
}