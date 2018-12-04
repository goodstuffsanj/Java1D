package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookingInstanceAdapter extends RecyclerView.Adapter<BookingInstanceAdapter.ViewHolder> {
    private ArrayList<BookingInstance> bookingInstances = new ArrayList<>();
    private Context context;

    public BookingInstanceAdapter(Context context, ArrayList<BookingInstance> bookings) {
        this.context = context;
        this.bookingInstances = bookings;
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
        viewHolder.bookingDate.setText(bookingInstance.getDate());
        viewHolder.bookingTime.setText(bookingInstance.getEndTime()+"-"+bookingInstance.getEndTime());
        viewHolder.bookingId.setText(bookingInstance.getBookingId());
        String status = bookingInstance.getStatus();
        switch (status) {
            case "upcoming":
                viewHolder.status.setText(status);
                viewHolder.status.setTextColor(Color.parseColor("#2ca411"));
                break;
            case "waiting":
                viewHolder.status.setText(status);
                viewHolder.status.setTextColor(Color.parseColor("#ffbc3f"));
                break;
            case "completed":
                viewHolder.status.setText(status);
                viewHolder.status.setTextColor(Color.parseColor("#e81e1e"));
                break;
                //viewHolder.status.setTextColor(Color.parseColor("#ffbc3f"));
        }
        Glide.with(context).load(bookingInstance.getImgUrl()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return bookingInstances.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookingId;
        TextView bookingDate;
        TextView bookingTime;
        TextView name;
        ImageView image;
        TextView status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookingId = itemView.findViewById(R.id.bookingId);
            bookingDate = itemView.findViewById(R.id.bookingDate);
            bookingTime = itemView.findViewById(R.id.bookingTime);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
            status = itemView.findViewById(R.id.status);
        }
    }
}
