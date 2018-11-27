package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


//get available timings for booking
public class Timings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timings);
        Intent intent = getIntent();
        String date = intent.getStringExtra(Prof.DATE_PICKED);
    }


}
