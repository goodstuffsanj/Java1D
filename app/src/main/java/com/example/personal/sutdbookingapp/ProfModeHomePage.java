package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class ProfModeHomePage extends AppCompatActivity {

    private CardView book_facilities;
    private CardView meeting_requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_mode_home_page);

        book_facilities = (CardView) findViewById(R.id.book_facilities);
        meeting_requests = (CardView) findViewById(R.id.meeting_requests);


        meeting_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfModeHomePage.this, ProfModeRequestsPage.class);
                startActivity(intent);
            }
        });

        book_facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfModeHomePage.this, BookFacilities.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

}
