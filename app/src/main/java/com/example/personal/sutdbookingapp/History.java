package com.example.personal.sutdbookingapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class History extends AppCompatActivity{

    private ArrayList<HistoryData> history = new ArrayList<>();
    private com.example.personal.sutdbookingapp.HistoryAdapter historyAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_history_page);
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
        for (int i=0; i<300; i++) {
            HistoryData historyItem = new HistoryData();
            historyItem.setBookingHistory("2333");
            historyItem.setTime("2018-12-15T09:00:00.000");
            historyItem.setStatus("Completed");
            history.add(historyItem);
        }
        initRecyclerView();
    }
    private void initRecyclerView() {
        RecyclerView recyclerView= findViewById(R.id.historyRecycleView);
        historyAdapter = new HistoryAdapter(this, history);
        recyclerView.setAdapter(historyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
