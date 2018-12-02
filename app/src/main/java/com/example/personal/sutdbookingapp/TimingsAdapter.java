package com.example.personal.sutdbookingapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

import java.util.ArrayList;
import java.util.List;

//Recycler view for timings_list
public class TimingsAdapter extends RecyclerView.Adapter<TimingsAdapter.TimingsViewHolder> {

    private static final String TAG = "TimingsAdapter";

    private ArrayList<TimingsData> timeDataList;
    private Context context;
    private List<String> blockedTimings;

    public TimingsAdapter (Context context, ArrayList<TimingsData> timeDataList) {
        this.timeDataList = timeDataList;
        this.context = context;
    }

    class TimingsViewHolder extends RecyclerView.ViewHolder {

        LinearLayout list;
        TextView time;
        Button bookButton;

        public TimingsViewHolder(View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.bookingTime);
            bookButton = itemView.findViewById(R.id.book);
            list = itemView.findViewById(R.id.timing_list);
        }
    }

    @NonNull
    @Override
    public TimingsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.timings_list,
                viewGroup, false);
        TimingsViewHolder holder = new TimingsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TimingsAdapter.TimingsViewHolder holder, int position) {
        TimingsData timingsData = timeDataList.get(position);
        setTiming(holder, timingsData);
        setButton(holder, timingsData);


    }

    private void setTiming(TimingsViewHolder viewHolder, TimingsData timingsData) {
        TextView timeSlot = viewHolder.time;
        LocalDateTime time = timingsData.getTime();
        String text = time.toString("HH:mm") + " - " + time.plusMinutes(30).toString("HH:mm");
//        if (strings[1] == "30") {
//            int hour = Integer.valueOf(strings[0]);
//            String nextHour;
//            if (hour < 10) {
//                nextHour = "0" + hour;
//            }
//            else {
//                nextHour = String.valueOf(hour + 1);
//            }
//            text = " - " + nextHour + ":00";
//        }
//        else {
//            text = " - " + strings[0] + ":30";
//        }
        timeSlot.setText(text);
    }



    //setup button
    private void setButton(TimingsViewHolder viewHolder, TimingsData timingsData) {
        Button bookButton = viewHolder.bookButton;
        Boolean enabled = timingsData.getAvailability();
        //if not available for booking
        if (!enabled) {
            bookButton.setBackgroundColor(Color.GRAY);
            bookButton.setEnabled(enabled);

        }
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create dialog to confirm on show
                AlertDialog.Builder builder = new AlertDialog.Builder(bookButton.getContext(), R.style.Theme_AppCompat_Light_Dialog);

                String text = "Confirm booking for "
                        + timingsData.getTime().toString("dd MMM yyyy '('E')'")
                        + " at "
                        + timingsData.getTime().toString("h:mm a")
                        + " with "
                        + timingsData.getName()
                        + "?";
                builder.setTitle("Confirm")
                        .setMessage(text)
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(bookButton.getContext(), "Confirmed booking", Toast.LENGTH_SHORT).show();

                                //update database
                                Database b = new Database(bookButton.getContext());
                                ProfTableDO prof = new ProfTableDO();
                                prof.setProfName(timingsData.getName());
                                b.getDataHandler(new Database.DataHandler() {
                                    @Override
                                    <T> void postReceivedData(T result) {
                                        ProfTableDO prof = (ProfTableDO) result;
                                        blockedTimings = prof.getProfBlockedTimings();
                                        blockedTimings.add(timingsData.getTime().toString());
                                    }
                                });
                                prof.setProfBlockedTimings(blockedTimings);

                                b.update(prof);
                            }
                        })
                        .create()
                        .show();
                Log.i(TAG, "onClick: " + "Confirm booking for "
                        + timingsData.getTime().toString("dd MMM yyyy '('E')'")
                        + " with "
                        + timingsData.getName()
                        + "?");
            }
        });

    }

    //how many results are displayed
    @Override
    public int getItemCount() {
        return timeDataList.size();
    }



}
