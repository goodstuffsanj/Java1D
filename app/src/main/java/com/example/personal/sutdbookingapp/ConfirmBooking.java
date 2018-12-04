package com.example.personal.sutdbookingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.LocalDateTime;

import java.util.List;
import java.util.UUID;

public class ConfirmBooking extends AppCompatActivity {
    private final static String TAG = "ConfirmBooking";
    private Boolean isProf;
    private String name;
    private LocalDateTime time;
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

                    String text = "Confirm booking for "
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
                                    bookingInstance.setStudentName("John Smith");
                                    bookingInstance.setStatus("Pending");
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
}
