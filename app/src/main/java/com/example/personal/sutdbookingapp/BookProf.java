package com.example.personal.sutdbookingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class BookProf extends AppCompatActivity {

    private static final String TAG = "BookProf";

    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> image_urls = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_prof);
        initImage();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void initImage() {
        for (int i=0; i<300; i++) {
            names.add("Professor "+i);
            image_urls.add("https://www.biography.com/.image/t_share/MTE5NDg0MDU0OTU2OTAxOTAz/albert-einstein-9285408-1-402.jpg");
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        ListAdapter listAdapter = new ListAdapter(image_urls, names, this);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
