package com.example.personal.sutdbookingapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.ArrayList;

public class History extends AppCompatActivity{

    private ArrayList<historylist> history = new ArrayList<>();
    private historyAdapter HistoryAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_prof);
    }
    public void onBackPressed() {
        super.onBackPressed();

    }
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
    private void initRecyclerView() {
        RecyclerView recyclerView= findViewById(R.id.historyRecycleView);
        HistoryAdapter = new historyAdapter(this, history);
        recyclerView.setAdapter(HistoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void initContent() {
        for (int i=0; i<300; i++) {
            historylist historyItem = new historylist();
            historyItem.setbookingHistory("2333");
            historyItem.setTime("10:20");
            historyItem.setStatus("completed");
            history.add(historyItem);

        }
        initRecyclerView();
    }
}
