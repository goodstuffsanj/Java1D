package com.example.personal.sutdbookingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class ProfModeRequestsPage extends AppCompatActivity {

    private static final String TAG = "ProfModeRequestsPage";

    //vars
    private ArrayList<RequestsData> RequestsData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_mode_requests_page);
        Log.i(TAG, "onCreate() inside ProfModeRequestsPage has been called");
        initRequests();
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

    private void initRequests() {
        RequestsData request = new RequestsData("Sanjay Shankar",
                "Friday, 14 Dec, 14:00 - 15:00",
                "Meet to submit the claim forms for java 1D project and also discuss future improvements for the 1D java project"
                );
        request.setSenderName("Sanjay Shankar");
        request.setDate("Friday, 14 Dec, 14:00 - 15:00");
        request.setReason("Meet to submit the claim forms for java 1D project and also discuss future improvements for the 1D java project");

        for (int i = 0; i < 50; i++) {
            Bookable facility = new Bookable();
            request.setSenderName("Student " + i);
            request.setDate("Friday, 21 Dec, 10:00 - 11:00");
            request.setReason("Meet for discussion of potential UROP in the field of either Machine Learning or Artificial Intelligence");
            RequestsData.add(request);
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView RecyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(ProfModeRequestsPage.this,
                LinearLayoutManager.VERTICAL, false);
        RequestsAdapter adapter = new RequestsAdapter(ProfModeRequestsPage.this, RequestsData);
        RecyclerView.setAdapter(adapter);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.addItemDecoration(new DividerItemDecoration(ProfModeRequestsPage.this,
                                                                  DividerItemDecoration.VERTICAL));
    }
}
