package com.example.personal.sutdbookingapp;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import org.joda.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

public class ConfirmBooking extends AppCompatActivity {
    private final static String TAG = "ConfirmBooking";
    private Boolean isProf;
    private String name;
    private LocalDateTime time;
    private String username;
    private List<String> blockedTimings;
    private TextView typeMessage;
    private TextView textViewName;
    private TextView textViewDate;
    private TextView textViewTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Intent intent = getIntent();
        name = intent.getStringExtra(TimingsAdapter.NAME);
        time = new LocalDateTime(intent.getStringExtra(TimingsAdapter.TIME));
        isProf = intent.getExtras().getBoolean(TimingsAdapter.IS_PROF);
        username = intent.getStringExtra(TimingsAdapter.USERNAME);


        Button okButton = findViewById(R.id.okButton);
        typeMessage = findViewById(R.id.sendMessage);
        textViewName = findViewById(R.id.name);
        textViewDate = findViewById(R.id.date);
        textViewTime = findViewById(R.id.timing);
        textViewName.setText(name);
        Log.i(TAG, "onCreate: " + time);
        textViewDate.setText(time.toString("E d MMM yyyy"));
        String timeSlot = time.toString("h:mm a - ") + time.plusMinutes(30).toString("h:mm a");
        textViewTime.setText(timeSlot);


        if (!isProf) {
            typeMessage.setVisibility(View.GONE);
        }

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isProf) {
                    //create dialog to confirm on show
                    AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmBooking.this, R.style.Theme_AppCompat_Light_Dialog);

                    String text = "Confirm booking at "
                            + time.toString("d MMM yyyy '('E')'")
                            + " at "
                            + time.toString("h:mm a")
                            + " with "
                            + name
                            + "?";
                    builder.setTitle("Confirm")
                            .setMessage(text)
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //update database
                                    Database b = new Database(ConfirmBooking.this);

                                    //update profTableDO database
                                    b.getDataHandler(new Database.DataHandler() {
                                        @Override
                                        <T> void postReceivedData(T result) {
                                            ProfTableDO prof = (ProfTableDO) result;
                                            blockedTimings = prof.getProfBlockedTimings();
                                            blockedTimings.add(time.toString());
                                            prof.setProfBlockedTimings(blockedTimings);
                                            Log.i("Database", "Update ProfTableDO: done");
                                            b.update(prof);
                                        }
                                    }).getData(ProfTableDO.class, name);

                                    //add to bookingInstanceDO database
                                    BookingInstanceTableDO bookingInstance = new BookingInstanceTableDO();
                                    bookingInstance.setBookingID(UUID.randomUUID().toString());
                                    bookingInstance.setName(name);
                                    bookingInstance.setTiming(time.toString());
                                    bookingInstance.setMessage(typeMessage.getText().toString());
                                    bookingInstance.setStudentName(username);
                                    Log.i(TAG, "onClick: " + username);
                                    bookingInstance.setStatus("Pending");
                                    Log.i("Database", "Add to BookingInstanceDO: done");
                                    b.create(bookingInstance);

                                    Toast.makeText(ConfirmBooking.this, "Confirmed booking", Toast.LENGTH_LONG).show();

                                    //add to calendar
                                    long calID = 3;
/*<<<<<<< HEAD
                                    long startMillis = 0;
                                    long endMillis = 0;
                                    Calendar beginTime = Calendar.getInstance();
                                    beginTime.set(2018, 12, 20, 7, 30);
                                    startMillis = beginTime.getTimeInMillis();
                                    Calendar endTime = Calendar.getInstance();
                                    endTime.set(2018, 12, 20, 8, 45);
                                    endMillis = endTime.getTimeInMillis();
=======
//                                    long startMillis = 0;
//                                    long endMillis = 0;
//                                    Calendar beginTime = Calendar.getInstance();
//                                    beginTime.set(2012, 9, 14, 7, 30);
//                                    startMillis = beginTime.getTimeInMillis();
//                                    Calendar endTime = Calendar.getInstance();
//                                    endTime.set(2012, 9, 14, 8, 45);
//                                    endMillis = endTime.getTimeInMillis();
>>>>>>> c944143a588276e6570a7da2bafc3e9d0dd8037e*/


// Refer to the Java quickstart on how to setup the environment:
// https://developers.google.com/calendar/quickstart/java
// Change the scope to CalendarScopes.CALENDAR and delete any stored

                                    ContentResolver cr = getContentResolver();
                                    ContentValues values = new ContentValues();
                                    values.put(Events.DTSTART, getLongAsDate(time));
                                    values.put(Events.DTEND, getLongAsDate(time.plusMinutes(30)));
                                    values.put(Events.TITLE, "Booking With "  + name);
                                    values.put(Events.DESCRIPTION, "Group workout");
                                    values.put(Events.CALENDAR_ID, calID);
                                    values.put(Events.EVENT_TIMEZONE, "Singapore");
                                    if (ActivityCompat.checkSelfPermission (ConfirmBooking.this, Manifest.permission.WRITE_CALENDAR ) != PackageManager.PERMISSION_GRANTED) {
                                        // TODO: Consider calling
                                        //    ActivityCompat#requestPermissions
                                        // here to request the missing permissions, and then overriding
                                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                        //                                          int[] grantResults)
                                        // to handle the case where the user grants the permission. See the documentation
                                        // for ActivityCompat#requestPermissions for more details.
                                        Intent intent = new Intent(ConfirmBooking.this, HomePage.class);
                                        startActivity(intent);
                                        return;
                                    }
                                    Uri uri = cr.insert(Events.CONTENT_URI, values);

                                    // get the event ID that is the last element in the Uri
                                    long eventID = Long.parseLong(uri.getLastPathSegment());

                                    Intent intent = new Intent(ConfirmBooking.this, HomePage.class);
                                    startActivity(intent);

                                }
                            })
                            .create()
                            .show();

                }

                else {
                    //create dialog to confirm on show
                    AlertDialog.Builder builder = new AlertDialog.Builder(ConfirmBooking.this, R.style.Theme_AppCompat_Light_Dialog);

                    String text = "Confirm booking at "
                            + time.toString("d MMM yyyy '('E')'")
                            + " at "
                            + time.toString("h:mm a")
                            + " for "
                            + name
                            + "?";
                    builder.setTitle("Confirm")
                            .setMessage(text)
                            .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //
                                    dialog.cancel();
                                }
                            })
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //update database
                                    Database b = new Database(ConfirmBooking.this);

                                    //update profTableDO database
                                    b.getDataHandler(new Database.DataHandler() {
                                        @Override
                                        <T> void postReceivedData(T result) {
                                            FacilityTableDO facility = (FacilityTableDO) result;
                                            blockedTimings = facility.getFacilityBlockedTimings();
                                            blockedTimings.add(time.toString());
                                            facility.setFacilityBlockedTimings(blockedTimings);
                                            Log.i("Database", "Update FacilityTableDO: done");
                                            b.update(facility);
                                        }
                                    }).getData(FacilityTableDO.class, name);

                                    //add to bookingInstanceDO database
                                    BookingInstanceTableDO bookingInstance = new BookingInstanceTableDO();
                                    bookingInstance.setBookingID(UUID.randomUUID().toString());
                                    bookingInstance.setName(name);
                                    bookingInstance.setTiming(time.toString());
                                    bookingInstance.setStudentName(username);
                                    Log.i(TAG, "onClick: " + username);
                                    bookingInstance.setStatus("Accepted");
                                    Log.i("Database", "Add to BookingInstanceDO: done");
                                    b.create(bookingInstance);

                                    Toast.makeText(ConfirmBooking.this, "Confirmed booking", Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(ConfirmBooking.this, HomePage.class);
                                    startActivity(intent);

                                }
                            })
                            .create()
                            .show();

                }



            }
        });
    }

    private long getLongAsDate(LocalDateTime date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(),date.getMonthOfYear(), date.getDayOfMonth(), date.getHourOfDay(), date.getMinuteOfHour());
        return calendar.getTimeInMillis();
    }
}