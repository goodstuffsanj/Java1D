package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.bumptech.glide.Glide;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static java.util.Calendar.DATE;

public class Facility extends AppCompatActivity {
    public final static String NAME = "NAME";
    public final static String BLOCKED_TIMINGS = "BLOCKED_TIMINGS";
    public final static String DATE_PICKED = "DATE_PICKED";
    public final static String PROF = "PROF";
    public static final String USERNAME = "USERNAME";

    private String name;
    private String image;
    private String location;
    private ArrayList<String> blockedTimings;
    private String desc;
    private String username;

    Button buttonBookFacility;
    TextView textViewFacilityName;
    TextView textViewLocation;
    TextView textViewDescription;
    CircleImageView imageFacility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility);
        setTitle("Book Facility");

        Intent intent = getIntent();
        name = intent.getStringExtra(ListAdapter.NAME);
        image = intent.getStringExtra(ListAdapter.IMAGE);
        location = intent.getStringExtra(ListAdapter.LOCATION);
        desc = intent.getStringExtra(ListAdapter.DESCRIPTION);
        blockedTimings = intent.getStringArrayListExtra(ListAdapter.BLOCKED_TIMINGS);
        username = intent.getStringExtra(ListAdapter.USERNAME);

        buttonBookFacility = findViewById(R.id.buttonBookFacility);
        textViewFacilityName = findViewById(R.id.textViewFacilityName);
        textViewLocation = findViewById(R.id.facil_location);
        textViewDescription = findViewById(R.id.description_about);
        imageFacility = findViewById(R.id.imageFacility);
        Glide.with(this).load(image).into(imageFacility);

        textViewFacilityName.setText(name);
        textViewLocation.setText(location);
        textViewDescription.setText(desc);
        buttonBookFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                StyleableToast.makeText(Facility.this,"hello world",R.style.mytoast).show();
                //set week range for calendar
                Calendar yesterday = Calendar.getInstance();
                yesterday.add(DATE, -1);
                Calendar nextWeek = Calendar.getInstance();
                nextWeek.add(DATE, 6);

                DatePickerBuilder builder = new DatePickerBuilder(Facility.this, listener)
                        .minimumDate(yesterday) // Minimum available date
                        .maximumDate(nextWeek) // Maximum available date
                        .headerColor(R.color.red) // Color of the dialog header
                        .selectionColor(R.color.red) // Color of the selection circle
                        .todayLabelColor(R.color.red)
                        .pickerType(CalendarView.ONE_DAY_PICKER);


                DatePicker datePicker = builder.build();
                datePicker.show();
            }

            private OnSelectDateListener listener = new OnSelectDateListener() {
                @Override
                public void onSelect(List<Calendar> calendars) {
                    Toast.makeText(buttonBookFacility.getContext(), calendars.get(0).getTime().toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(buttonBookFacility.getContext(), BookTimings.class);
                    intent.putExtra(DATE_PICKED, calendars.get(0).getTime());
                    intent.putExtra(PROF, false);
                    intent.putExtra(NAME, name);
                    intent.putExtra(BLOCKED_TIMINGS, blockedTimings);
                    intent.putExtra(USERNAME, username);

                    startActivity(intent);
                }
            };


        });


    }

    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
