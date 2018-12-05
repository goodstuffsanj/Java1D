package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Prof extends AppCompatActivity {
    public final static String PROF_ID = "PROF_ID";
    public final static String IMAGE = "IMAGE";
    private final static String DESCRIPTION = "DESCRIPTION";
    CircleImageView imageProf;
    TextView description_about;
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
        setTitle(name);

        imageProf = findViewById(R.id.imageProf);
        description_about = (TextView) findViewById(R.id.description_about);
        book = (Button) findViewById(R.id.bookConsult);


        Glide.with(this).load(image).into(imageProf);
        description_about.setText(desc);
        calendarView = findViewById(R.id.calendarView);
        Calendar yesterday = Calendar.getInstance();
        yesterday.add(Calendar.DATE,-1);
        Calendar nextWeek = Calendar.getInstance();
        nextWeek.add(Calendar.DATE,6);
        calendarView.setMinimumDate(yesterday);
        calendarView.setMaximumDate(nextWeek);
        OnSelectDateListener listener = new OnSelectDateListener() {
            @Override
            public void onSelect(List<java.util.Calendar> calendars) {

            }
        };
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                java.util.Calendar clickedDayCalendar = eventDay.getCalendar();
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder builder = new DatePickerBuilder(Prof.this, listener)
                        .pickerType(CalendarView.ONE_DAY_PICKER);

                DatePicker datePicker = builder.build();
                datePicker.show();
            }
        });

    }
}
