package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;

//Recycler view for timings_list
public class TimingsAdapter extends RecyclerView.Adapter<TimingsAdapter.TimingsViewHolder> {

    private static final String TAG = "TimingsAdapter";

    private ArrayList<TimingsData> timeDataList;
    private Context context;

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
                String info = timingsData.getDate()+ " "  + timingsData.getName() + " " + timingsData.getTime();
                Toast.makeText(context, "pending request for: " + info, Toast.LENGTH_LONG).show();
            }
        });

    }

    //how many results are displayed
    @Override
    public int getItemCount() {
        return timeDataList.size();
    }



}
