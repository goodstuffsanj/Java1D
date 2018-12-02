package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookTimings extends AppCompatActivity{
    private ArrayList<TimingsData> timings = new ArrayList<>();
    private TextView dateInfo;
    private TimingsAdapter timingsAdapter;
    private static final String TAG = "XBookTimings";
    public static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookable_timings);
        BookTimings.context = getApplicationContext ();
        setTitle(getName());

        initTimings();

        Intent intent = getIntent();
        String datePicked = intent.getStringExtra(Prof.DATE_PICKED);
        String time = intent.getStringExtra ( Prof.TIME_EACH );
        Toast.makeText ( this, time,Toast.LENGTH_SHORT ).show ();
        Button book;

        dateInfo = findViewById(R.id.date);
        dateInfo.setText(datePicked);
//        book = findViewById ( R.id.book );
//
//        book.setOnClickListener ( new View.OnClickListener ( ) {
//            @Override
//            public void onClick ( View v ) {
//                Toast.makeText ( book.getContext (), "Booked", Toast.LENGTH_SHORT ).show ( );
//            }
//        } );

    }

    private void initTimings() {
        for (int i=9; i<19; i++) {
            TimingsData timingsData = new TimingsData();
            TimingsData timingsData1 =new TimingsData();
            if (String.valueOf(i).length() == 1) {
                timingsData.setTime("0" + i + ":00");
                timingsData1.setTime("0" + i + ":30");
                Log.i(TAG, "initTimings: " + timingsData1.getTime());
            }
            else {
                timingsData.setTime(i + ":00");
                timingsData1.setTime(i + ":30");
            }
            timingsData.setAvailability(true);
            timingsData1.setAvailability(false);
            timings.add(timingsData);
            timings.add(timingsData1);
        }
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        timingsAdapter = new TimingsAdapter(this, timings);
        recyclerView.setAdapter(timingsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public static Context  getAppContext()
    {
        return BookTimings.context;
    }
    //get Facility or Prof name to display on action bar
    private String getName() {
        return "Confirming your Booking";
    }
}



