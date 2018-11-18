package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Facility extends AppCompatActivity {
    public String facilityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility);

        Intent intent = getIntent();
        facilityId = intent.getStringExtra(ListAdapter.FACIL_ID);
    }
}
