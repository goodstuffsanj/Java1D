package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

public class BookingInstanceAdapter extends RecyclerView.Adapter<BookingInstanceAdapter.ViewHolder> {
    private ArrayList<BookingInstance> bookingInstances;
    private ArrayList<BookingInstance> updatedResult;
    private Context context;
    private String username;

    public BookingInstanceAdapter(Context context, ArrayList<BookingInstance> bookings, String username) {
        this.context = context;
        this.bookingInstances = bookings;
        this.username = username;
        this.updatedResult = new ArrayList<>(bookingInstances);
    }

    @NonNull
    @Override
    public BookingInstanceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.booking_instance, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookingInstanceAdapter.ViewHolder viewHolder, int i) {
        BookingInstance bookingInstance = bookingInstances.get(i);
        viewHolder.name.setText(bookingInstance.getName());
        String timeSlot = bookingInstance.getStartTime().toString("E, d MMM yyyy, h:mm a - ") + bookingInstance.getEndTime().toString("h:mm a");
        viewHolder.bookingTime.setText(timeSlot);
        String bookingIDText = "Booking ID: " + bookingInstance.getBookingId();
        viewHolder.bookingId.setText(bookingIDText);
        viewHolder.bookerName.setText(bookingInstance.getBookerName());
        String status = bookingInstance.getStatus();
        switch (status) {
            case "Upcoming":
                viewHolder.status.setText(status);
                viewHolder.status.setTextColor(Color.parseColor("#2ca411"));
                break;
            case "Waiting":
                viewHolder.status.setText(status);
                viewHolder.status.setTextColor(Color.parseColor("#ffbc3f"));
                break;
            case "Completed":
                viewHolder.status.setText(status);
                viewHolder.status.setTextColor(Color.parseColor("#e81e1e"));
                viewHolder.buttonCancel.setVisibility(View.GONE);
                break;
            case "Rejected":
                viewHolder.status.setText(status);
                viewHolder.status.setTextColor(Color.parseColor("#e81e1e"));
                viewHolder.buttonCancel.setVisibility(View.GONE);
                break;
            case "Cancelled":
                viewHolder.status.setText(status);
                viewHolder.status.setTextColor(Color.parseColor("#e81e1e"));
                viewHolder.buttonCancel.setVisibility(View.GONE);
                break;
                //viewHolder.status.setTextColor(Color.parseColor("#ffbc3f"));
        }
//        Glide.with(context).load(bookingInstance.getImgUrl()).into(viewHolder.image);

        //set button
        viewHolder.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database b = new Database(context);
                //update blocked timings
                b.getDataHandler(new Database.DataHandler() {
                    @Override
                    <T> void postReceivedData(T result) {
                        FacilityTableDO facility = (FacilityTableDO) result;
                        if (result != null) {
                            List<String> blockedTimings = facility.getFacilityBlockedTimings();
                            blockedTimings.remove(bookingInstance.getStartTime().toString());
                            facility.setFacilityBlockedTimings(blockedTimings);
                            b.update(facility);
                        }
                    }
                }).getData(FacilityTableDO.class, bookingInstance.getName());
                b.getDataHandler(new Database.DataHandler() {
                    @Override
                    <T> void postReceivedData(T result) {
                        ProfTableDO prof = (ProfTableDO) result;
                        if (result != null) {
                            List<String> blockedTimings = prof.getProfBlockedTimings();
                            blockedTimings.remove(bookingInstance.getStartTime().toString());
                            prof.setProfBlockedTimings(blockedTimings);
                            b.update(prof);
                        }
                    }
                }).getData(ProfTableDO.class, bookingInstance.getName());
                //update booking instance
                b.getDataHandler(new Database.DataHandler() {
                    @Override
                    <T> void postReceivedData(T result) {
                        BookingInstanceTableDO bookingInstance = (BookingInstanceTableDO) result;
                        bookingInstance.setStatus("Cancelled");
                        b.update(bookingInstance);
                    }

                    @Override
                    public void showOnUI(Handler handler) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                updatedResult.remove(bookingInstance);
                                bookingInstances.clear();
                                bookingInstances.addAll(updatedResult);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Booking has been cancelled", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).getData(BookingInstanceTableDO.class, bookingInstance.getBookingId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingInstances.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookingId;
        TextView bookingTime;
        TextView name;
        TextView bookerName;
        ImageView image;
        TextView status;
        Button buttonCancel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookingId = itemView.findViewById(R.id.bookingId);
            bookingTime = itemView.findViewById(R.id.bookingTime);
            name = itemView.findViewById(R.id.name);
            bookerName = itemView.findViewById(R.id.bookerName);
            image = itemView.findViewById(R.id.image);
            status = itemView.findViewById(R.id.status);
            buttonCancel = itemView.findViewById(R.id.buttonCancel);
        }
    }
}
