package com.example.personal.sutdbookingapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class History extends AppCompatActivity{
    private static final String TAG = "history is created";
    private ArrayList<HistoryData> historylist = new ArrayList<>();
    private HistoryAdapter historyAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_history_page);
        Log.i(TAG, "history is created");
        initContent();
    }
    public void onBackPressed() {
        super.onBackPressed();

    }
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
    private void initContent() {
        for (int i=0; i<20; i++) {
            HistoryData historyItem = new HistoryData();
            historyItem.setBookingHistory("2333");
            historyItem.setTime("10:20");
            historyItem.setStatus("completed");
            historylist.add(historyItem);
        }
        initRecyclerView();
    }
    private void initRecyclerView() {
        RecyclerView recyclerView= findViewById(R.id.historyRecycleView);
        historyAdapter = new HistoryAdapter(this, historylist);
        recyclerView.setAdapter(historyAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.i(TAG, "history recycleView is created");
    }
}
