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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static java.util.Calendar.DATE;
// This class is created to create a new intent to book professors
public class Prof extends AppCompatActivity {
    public final static String NAME = "NAME";
    public final static String BLOCKED_TIMINGS = "BLOCKED_TIMINGS";
    public final static String DATE_PICKED = "DATE_PICKED";
    public final static String PROF = "PROF";
    public final static String TIME = "TIME";
    public final static String USERNAME = "USERNAME";
    private String name;
    private String image;
    private String location;
    private String email;
    private String contact;
    private ArrayList<String> blockedTimings;
    private String desc;
    private String username;
    CircleImageView imageProf;
    TextView textViewProfName;
    TextView textViewLocation;
    TextView textViewEmail;
    TextView textViewPhone;
    TextView textViewDescription;
    CalendarView calendarView;
    Button book;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        Intent intent = getIntent();
        name = intent.getStringExtra(ListAdapter.NAME);
        image = intent.getStringExtra(ListAdapter.IMAGE);
        location = intent.getStringExtra(ListAdapter.LOCATION);
        email = intent.getStringExtra(ListAdapter.EMAIL);
        contact = intent.getStringExtra(ListAdapter.CONTACT);
        desc = intent.getStringExtra(ListAdapter.DESCRIPTION);
        blockedTimings = intent.getStringArrayListExtra(ListAdapter.BLOCKED_TIMINGS);
        username = intent.getStringExtra(ListAdapter.USERNAME);


        setTitle("Book Consultation");

        imageProf = findViewById(R.id.imageProf);
        textViewProfName = findViewById(R.id.textViewProfName);
        textViewLocation = findViewById(R.id.description_office);
        textViewEmail = findViewById(R.id.description_email);
        textViewPhone = findViewById(R.id.description_contact);
        textViewDescription = findViewById(R.id.description_about);
        book = findViewById(R.id.bookConsult);

        textViewProfName.setText(name);
        Glide.with(this).load(image).into(imageProf);
        textViewLocation.setText(location);
        textViewEmail.setText(email);
        textViewPhone.setText(contact);
        textViewDescription.setText(desc);

        Calendar yesterday = Calendar.getInstance();
        yesterday.add(DATE,-1);
        Calendar nextWeek = Calendar.getInstance();
        nextWeek.add(DATE,6);

        //Booking date limited to 1 week
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder builder = new DatePickerBuilder(Prof.this, listener)
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
                    Toast.makeText(book.getContext(), calendars.get(0).getTime().toString(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(book.getContext(), BookTimings.class);
                    intent.putExtra(DATE_PICKED, calendars.get(0).getTime ());
//                    intent.putExtra ( TIME, calendars.get ( 0 ).getTimeInMillis () );
                    intent.putExtra(PROF, true);
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
