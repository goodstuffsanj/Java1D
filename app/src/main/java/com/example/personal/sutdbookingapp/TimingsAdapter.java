package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//Recycler view for timings_list
public class TimingsAdapter extends RecyclerView.Adapter<TimingsAdapter.TimingsViewHolder> {

    private static final String TAG = "TimingsAdapter";

    private ArrayList<TimingsData> timeDataList;
    private Context context;
    private List<String> blockedTimings;
    public final static String NAME = "NAME";
    public final static String TIME = "TIME";
    public final static String IS_PROF = "IS_PROF";

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
        LocalDateTime time1 = new LocalDateTime(timingsData.getTime().getYear(), timingsData.getTime().getMonthOfYear(), timingsData.getTime().getDayOfMonth(), timingsData.getTime().getHourOfDay(), timingsData.getTime().getMinuteOfHour());
        String text = time.toString("HH:mm - ") + time1.toString("HH:mm");
        timeSlot.setText(text);
    }

    //setup button
    private void setButton(TimingsViewHolder viewHolder, TimingsData timingsData) {
        Button bookButton = viewHolder.bookButton;
        Boolean enabled = timingsData.getAvailability();
        //if not available for booking
        if (!enabled) {
            Log.i(TAG, "setButton: " + timingsData.getTime());
            bookButton.setBackgroundColor(bookButton.getContext().getResources().getColor(R.color.colorPrimary));
            bookButton.setEnabled(enabled);
        }
        else {
            bookButton.setBackgroundColor(bookButton.getContext().getResources().getColor(R.color.colorPrimaryDark));
        }
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookButton.getContext(), ConfirmBooking.class);
                intent.putExtra(NAME, timingsData.getName());
                intent.putExtra(TIME, timingsData.getTime().toString());
                Log.i(TAG, "onClick: " + timingsData.getTime().toString());
                intent.putExtra(IS_PROF, true);
                context.startActivity(intent);
            }
        });

    }

    //how many results are displayed
    @Override
    public int getItemCount() {
        return timeDataList.size();
    }



}
