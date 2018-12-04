package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class historyAdapter extends RecyclerView.Adapter<historyAdapter.HistoryViewHolder>{


//Recycler view for timings_list

    private static final String TAG = "historyAdapter";

    private ArrayList<historylist> history_list;
    private Context context;

    public historyAdapter (Context context, ArrayList<historylist> history_list) {
        this.history_list = history_list;
        this.context = context;
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        LinearLayout list;
        TextView bookingHistory;
        TextView bookingTime;
        TextView bookingStatus;

        public HistoryViewHolder(View itemView) {
            super(itemView);

            bookingTime = itemView.findViewById(R.id.bookingTime);
            bookingHistory = itemView.findViewById(R.id.bookingHistory);
            bookingStatus = itemView.findViewById(R.id.status);
            list = itemView.findViewById(R.id.history);
        }
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_list,
                viewGroup, false);
        HistoryViewHolder holder = new HistoryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(historyAdapter.HistoryViewHolder holder, int position) {
        historylist historyData = history_list.get(position);

        holder.bookingTime.setText(historyData.getTime());
        holder.bookingStatus.setText(historyData.getStatus());
        holder.bookingHistory.setText(historyData.getbookingHistory());




    }

    //how many results are displayed
    @Override
    public int getItemCount() {
        return history_list.size();
    }



}