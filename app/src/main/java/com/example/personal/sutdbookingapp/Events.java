package com.example.personal.sutdbookingapp;

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

public class Events extends AppCompatActivity {
    private ArrayList<BookingInstance> events = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        //View rootView = inflater.inflate(R.layout.fragment_upcoming, container, false);
//        for (int i=0; i<20; i++) {
//            BookingInstance bookingInstance = new BookingInstance("120839201","Professor "+Integer.toString(i+1),"12/11/2018","08:30","09:30","Building 1 lvl 5","https://www.biography.com/.image/t_share/MTE5NDg0MDU0OTU2OTAxOTAz/albert-einstein-9285408-1-402.jpg","upcoming");
//            upcomings.add(bookingInstance);
//        }

        //get from database
        Database b = new Database(this);
        b.getDataHandlerAll(new Database.DataHandlerAll() {
            @Override
            <T> void postQueryAll(PaginatedList<T> result) {
                for (int i = 0; i < result.size(); i ++) {
                    BookingInstanceTableDO bookingInstance = (BookingInstanceTableDO) result.get(i);
                    if (bookingInstance != null) {
                        Log.i("bookingInstance:", bookingInstance.getName()+bookingInstance.getStudentName());
                        Log.i("username", username);
                        if (bookingInstance.getName().equals(username)|| bookingInstance.getStudentName().equals(username)) {
                            LocalDateTime timing = new LocalDateTime(bookingInstance.getTiming());
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
                                completeds.add(booking);
                            }
                        }
                    }

                }

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
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        EventsAdapter adapter = new EventsAdapter(this, upcomings, username);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
