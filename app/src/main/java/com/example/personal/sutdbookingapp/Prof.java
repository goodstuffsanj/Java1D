package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Prof extends AppCompatActivity {
    public final static String PROF_ID = "PROF_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        Intent intent = getIntent();
        String name = intent.getStringExtra(PROF_ID);
        setTitle(name);

    }
}
