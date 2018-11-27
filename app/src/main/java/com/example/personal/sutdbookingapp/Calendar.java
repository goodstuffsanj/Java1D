package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Calendar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_calendar_confirm);

        Button add_appointment;


        Intent booking_intent = getIntent ();
        String booking_details = booking_intent.getStringExtra (Prof.DATE_PICKED);


        add_appointment = findViewById ( R.id.add_appointment );


        add_appointment.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Toast.makeText ( Calendar.this, booking_details,Toast.LENGTH_SHORT ).show ();
            }
        } );
    }



}
