package com.example.personal.sutdbookingapp;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by sanjayshankar on 24/11/18.
 */

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.RequestsViewHolder> {

    private static final String TAG = "RequestsAdapter";

    //private List requestsList;
    private ArrayList<RequestsData> requestsList = new ArrayList<>();
    private ArrayList<RequestsData> updatedResult = new ArrayList<>();
    private Context context;
    private String calId;
    private String username;

    public RequestsAdapter(Context context, ArrayList<RequestsData> requestsList, String username) {
        this.requestsList = requestsList;
        this.context = context;
        this.username = username;
        this.updatedResult = new ArrayList<>(requestsList);
    }

    @Override
    public RequestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_requests_item,
                parent, false);
        RequestsViewHolder holder = new RequestsViewHolder(view);
        return holder;
        //return new RequestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RequestsViewHolder holder, int position) {
        Log.i(TAG, "onBindViewHolder() is called");  //for every item that is in the list
        String bookingID = requestsList.get(position).getBookingID();
        String senderName = requestsList.get(position).getSenderName();
        LocalDateTime timing = requestsList.get(position).getTime();
        String timeSlot = timing.toString("E, d MMM yyyy, h:mm a - ") + timing.plusMinutes(30).toString("h:mm a");
        String message = requestsList.get(position).getReason();
        holder.senderName.setText(senderName);
        String bookingIDText = "Booking ID: " + bookingID;
        holder.bookingID.setText(bookingIDText);
        holder.date.setText(timeSlot);
        holder.reason.setText(message);


        Database b = new Database(context);
        b.getDataHandler(new Database.DataHandler() {
            @Override
            <T> void postReceivedData(T result) {
                ProfTableDO prof = (ProfTableDO) result;
                calId = prof.getProfCalendar();
            }
        }).getData(ProfTableDO.class, username);

        holder.tick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.getDataHandler(new Database.DataHandler() {
                    @Override
                    <T> void postReceivedData(T result) {
                        BookingInstanceTableDO booking = (BookingInstanceTableDO) result;
                        booking.setStatus("Accepted");
                        b.update(booking);
                    }

                    @Override
                    public void showOnUI(Handler handler) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                //update UI
                                updatedResult.remove(requestsList.get(position));
                                requestsList.clear();
                                requestsList.addAll(updatedResult);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Request has been accepted", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).getData(BookingInstanceTableDO.class, bookingID);
                ContentResolver cr = context.getContentResolver ( );
                ContentValues cv = new ContentValues ( );
                cv.put ( CalendarContract.Events.TITLE, "Booking With "  + senderName);
                cv.put ( CalendarContract.Events.DESCRIPTION, "getDescription" );
                cv.put ( CalendarContract.Events.EVENT_LOCATION, "getLocation" );
                cv.put ( CalendarContract.Events.DTSTART, getLongAsDate(timing));
                cv.put ( CalendarContract.Events.DTEND,  getLongAsDate(timing.plusMinutes(30)));
                cv.put ( CalendarContract.Events.CALENDAR_ID, 1 );
                cv.put ( CalendarContract.Events.EVENT_TIMEZONE, "Singapore" );
                if (ActivityCompat.checkSelfPermission ( context, Manifest.permission.WRITE_CALENDAR ) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Uri uri = cr.insert ( CalendarContract.Events.CONTENT_URI, cv );
                Toast.makeText (context, timeSlot, Toast.LENGTH_SHORT ).show ();

                Toast.makeText(context, "Request has been added to calendar", Toast.LENGTH_LONG).show();
            }
        });

        holder.cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //RequestsData.remove(position);
                //Toast.makeText(context, "request has been declined", Toast.LENGTH_LONG).show();
                //notifyDataSetChanged();
                b.getDataHandler(new Database.DataHandler() {
                    @Override
                    <T> void postReceivedData(T result) {
                        BookingInstanceTableDO booking = (BookingInstanceTableDO) result;
                        booking.setStatus("Rejected");
                        b.update(booking);
                    }
                }).getData(BookingInstanceTableDO.class, bookingID);
                b.getDataHandler(new Database.DataHandler() {
                    @Override
                    <T> void postReceivedData(T result) {
                        ProfTableDO prof = (ProfTableDO) result;
                        List<String> blockedTimings = ((ProfTableDO) result).getProfBlockedTimings();
                        blockedTimings.remove(timing.toString());
                        prof.setProfBlockedTimings(blockedTimings);
                        b.update(prof);
                    }

                    @Override
                    public void showOnUI(Handler handler) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                updatedResult.remove(requestsList.get(position));
                                requestsList.clear();
                                requestsList.addAll(updatedResult);
                                notifyDataSetChanged();
                                Toast.makeText(context, "Request has been declined", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }).getData(ProfTableDO.class, username);
            }
        });

//        holder.senderName.setText(requestsList.get(position).getSenderName());
//        holder.date.setText(requestsList.get(position).getTime());
//        holder.reason.setText(requestsList.get(position).getReason());
//
//
//
//        holder.tick.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "request has been accepted", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        holder.cross.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "request has been declined", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return requestsList.size();
    }

    class RequestsViewHolder extends RecyclerView.ViewHolder {

        LinearLayout request;

        TextView senderName;
        TextView bookingID;
        ImageButton tick;
        ImageButton cross;
        TextView date;
        TextView reason;

        public RequestsViewHolder(View itemView) {
            super(itemView);

            request = itemView.findViewById(R.id.request);
            bookingID = itemView.findViewById(R.id.bookingId);
            senderName = itemView.findViewById(R.id.requestName);
            tick = itemView.findViewById(R.id.tick);
            cross = itemView.findViewById(R.id.cross);
            date = itemView.findViewById(R.id.requestTime);
            reason = itemView.findViewById(R.id.requestReason);
        }
    }

    private long getLongAsDate(LocalDateTime date) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, date.getDayOfMonth());
        calendar.set(Calendar.MONTH, date.getMonthOfYear());
        calendar.set(Calendar.YEAR, date.getYear());
        return calendar.getTimeInMillis();
    }
}

