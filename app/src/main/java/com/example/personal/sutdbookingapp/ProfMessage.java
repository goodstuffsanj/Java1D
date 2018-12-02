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

import java.util.ArrayList;
import java.util.List;

public class ProfMessage extends AppCompatActivity {
    private final static String TAG = "ProfMessage";
    private Boolean isProf;
    private String name;
    private LocalDateTime time;
    private List<String> blockedTimings;
    private List<String> pendingTimings;
    private List<String> pendingMessages;
    private TextView typeMessage;
    private TextView textViewName;
    private TextView textViewDate;
    private TextView textViewTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

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
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProfMessage.this, R.style.Theme_AppCompat_Light_Dialog);

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
                                    Database b = new Database(ProfMessage.this);

                                    b.getDataHandler(new Database.DataHandler() {
                                        @Override
                                        <T> void postReceivedData(T result) {
                                            ProfTableDO prof = new ProfTableDO();
                                            ProfTableDO profDatabase = (ProfTableDO) result;
                                            prof.setProfName(name);
                                            prof.setProfPassword(profDatabase.getProfPassword());
                                            prof.setProfContact(profDatabase.getProfContact());
                                            prof.setProfEmail(profDatabase.getProfEmail());
                                            prof.setProfImage(profDatabase.getProfImage());
                                            prof.setProfOffice(profDatabase.getProfOffice());
                                            prof.setProfPillar(profDatabase.getProfPillar());
                                            prof.setProfDescription(profDatabase.getProfDescription());
                                            prof.setProfCalendar(profDatabase.getProfCalendar());
                                            blockedTimings = profDatabase.getProfBlockedTimings();
                                            blockedTimings.add(time.toString());
                                            pendingTimings = profDatabase.getProfPending();
                                            pendingTimings.add(time.toString());
                                            pendingMessages = profDatabase.getProfMessage();
                                            pendingMessages.add(typeMessage.getText().toString());
                                            prof.setProfBlockedTimings(blockedTimings);
                                            prof.setProfPending(pendingTimings);
                                            prof.setProfMessage(pendingMessages);
                                            Log.i("ProfMessage", "postReceivedData: done");
                                            b.update(prof);
                                        }
                                    }).getData(ProfTableDO.class, name);
                                    Toast.makeText(ProfMessage.this, "Confirmed booking", Toast.LENGTH_SHORT).show();

                                }
                            })
                            .create()
                            .show();

                }

            }
        });
    }
}
