package com.example.personal.sutdbookingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Calendar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_calendar_confirm);

        Button add_appointment;


        add_appointment = findViewById ( R.id.add_appointment );



        add_appointment.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

            }
        } );
    }



}
