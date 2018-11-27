package com.example.personal.sutdbookingapp;

import android.content.Context;
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

        holder.time.setText(timingsData.getTime());
        setButton(holder, timingsData);


    }


    //setup button
    private void setButton(TimingsViewHolder viewHolder, TimingsData timingsData) {
        Button bookButton = viewHolder.bookButton;
        Boolean enabled = timingsData.getAvailability();
        //if not available for booking
        if (!enabled) {
            bookButton.setBackgroundColor(0xFFE68F8C);
            //bookButton.setTextColor(R.color.colorPrimary);
            bookButton.setEnabled(enabled);

        }
        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String info = timingsData.getDate()+ " "  + timingsData.getProf_facil() + " " + timingsData.getTime();
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
