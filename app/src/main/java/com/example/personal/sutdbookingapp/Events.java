package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;
import java.util.Date;

public class Events extends AppCompatActivity {
    private ArrayList<BookingInstance> events = new ArrayList<>();
    SharedPreferences appData;
    String username;
    private static final String TAG = "Events";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        //View rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);
//        for (int i=0; i<20; i++) {
//            BookingInstance bookingInstance = new BookingInstance("120839201","Professor "+Integer.toString(i+1),"12/11/2018","08:30","09:30","Building 1 lvl 5","https://www.biography.com/.image/t_share/MTE5NDg0MDU0OTU2OTAxOTAz/albert-einstein-9285408-1-402.jpg","upcoming");
//            upcomings.add(bookingInstance);
//        }
        Intent intent = getIntent();
        long millis = intent.getLongExtra("data",-1)/86400000;
        appData = getSharedPreferences("appData",MODE_PRIVATE);
        username = appData.getString("username","");

        //get from database
        Database c = new Database(Events.this);
        c.getDataHandlerAll(new Database.DataHandlerAll() {
            @Override
            <T> void postQueryAll(PaginatedList<T> result) {
                for (int i = 0; i < result.size(); i ++) {
                    BookingInstanceTableDO bookingInstance = (BookingInstanceTableDO) result.get(i);
                    //Log.i(TAG, "postQueryAll: "+bookingInstance.getTiming());
                    LocalDateTime timing = new LocalDateTime(bookingInstance.getTiming());
                    long date= timing.toDate().getTime()/86400000;
                    Log.i(TAG, "postQueryAll: "+date + "n");
                    if (bookingInstance != null && date == millis+1) {

                        if (bookingInstance.getName().equals(username)|| bookingInstance.getStudentName().equals(username)) {
                            //LocalDateTime timing = new LocalDateTime(bookingInstance.getTiming());
                            if (timing.isAfter(new LocalDateTime(DateTimeZone.forID("+08:00"))) && bookingInstance.getStatus().equals("Accepted")) {
                                BookingInstance booking = new BookingInstance(bookingInstance.getBookingID(), bookingInstance.getName(), bookingInstance.getStudentName(), bookingInstance.getTiming(), bookingInstance.getLocation(), "Upcoming");
                                events.add(booking);

                            }
                            else if (timing.isAfter(new LocalDateTime(DateTimeZone.forID("+08:00"))) && bookingInstance.getStatus().equals("Pending")) {
                                BookingInstance booking = new BookingInstance(bookingInstance.getBookingID(), bookingInstance.getName(), bookingInstance.getStudentName(), bookingInstance.getTiming(), bookingInstance.getLocation(), "Waiting");
                                events.add(booking);
                            } else if (timing.isBefore(new LocalDateTime(DateTimeZone.forID("+08:00"))) && bookingInstance.getStatus().equals("Accepted")) {
                                BookingInstance booking = new BookingInstance(bookingInstance.getBookingID(), bookingInstance.getName(), bookingInstance.getStudentName(), bookingInstance.getTiming(), bookingInstance.getLocation(), "Completed");
                                events.add(booking);
                            }
                            else if (timing.isBefore(new LocalDateTime(DateTimeZone.forID("+08:00"))) && bookingInstance.getStatus().equals("Pending")) {
                                BookingInstance booking = new BookingInstance(bookingInstance.getBookingID(), bookingInstance.getName(), bookingInstance.getStudentName(), bookingInstance.getTiming(), bookingInstance.getLocation(), "Overdue");
                                events.add(booking);
                            }
                            else if (bookingInstance.getStatus().equals("Rejected")) {
                                BookingInstance booking = new BookingInstance(bookingInstance.getBookingID(), bookingInstance.getName(), bookingInstance.getStudentName(), bookingInstance.getTiming(), bookingInstance.getLocation(), "Rejected");
                                events.add(booking);
                            }
                            else if (bookingInstance.getStatus().equals("Cancelled")) {
                                BookingInstance booking = new BookingInstance(bookingInstance.getBookingID(), bookingInstance.getName(), bookingInstance.getStudentName(), bookingInstance.getTiming(), bookingInstance.getLocation(), "Cancelled");
                                events.add(booking);
                            }
                        }
                    }

                }
                Collections.sort(events, new BookingInstanceComparator());

            }

            @Override
            void showOnUI(Handler handler) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        initRecycler();
                    }
                });
            }
        }).getAll(BookingInstanceTableDO.class);
    }
    public void initRecycler() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        EventsAdapter adapter = new EventsAdapter(this, events, username);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);

    }
}
