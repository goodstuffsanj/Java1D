package com.example.personal.sutdbookingapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static java.util.Calendar.DATE;

public class Prof extends AppCompatActivity {
    public final static String PROF_ID = "PROF_ID";
    public final static String IMAGE = "IMAGE";
    public final static String DESCRIPTION = "DESCRIPTION";
    public final static String DATE_PICKED = "DATE_PICKED";
    CircleImageView imageProf;
    TextView description_about;
    TextView textViewProfName;
    CalendarView calendarView;
    Button book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        Intent intent = getIntent();
        String name = intent.getStringExtra(PROF_ID);
        String image = intent.getStringExtra(IMAGE);
        String desc = intent.getStringExtra(DESCRIPTION);
        setTitle("BookProf");

        imageProf = findViewById(R.id.imageProf);
        description_about = findViewById(R.id.description_about);
        book = findViewById(R.id.bookConsult);
        textViewProfName = findViewById(R.id.textViewProfName);

        textViewProfName.setText(name);
        Glide.with(this).load(image).into(imageProf);
        description_about.setText(desc);
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(DATE,-1);
        Calendar nextWeek = Calendar.getInstance();
        nextWeek.add(DATE,6);



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

                //String print = calendarView.getSelectedDates().get(0).getTime().toString();


                //Log.i("calendar", "onClick: " + print);
                //Toast.makeText(bookButton.getContext(), print, Toast.LENGTH_SHORT).show();
            }

            private OnSelectDateListener listener = new OnSelectDateListener() {
                @Override
                public void onSelect(List<Calendar> calendars) {
                    String date = calendars.get(0).getTime().toString();
                    Toast.makeText(book.getContext(), date, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(book.getContext(), BookTimings.class);
                    intent.putExtra(DATE_PICKED, date);
                    startActivity(intent);
                }
            };


        });


    }
}
