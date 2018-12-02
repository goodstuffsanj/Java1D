package com.example.personal.sutdbookingapp;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import static android.provider.CalendarContract.*;
import static android.provider.CalendarContract.CALLER_IS_SYNCADAPTER;

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


//    public void addEvent( String title, String description, String location, long start_time,long end_time, int id, String time_zone)
//    {
//        ContentValues cv = new ContentValues (  );
//        ContentResolver cr = context.getContentResolver ();
//        cv.put ( Events.TITLE, title );
//        cv.put ( Events.DESCRIPTION, description );
//        cv.put ( Events.EVENT_LOCATION, location );
//        cv.put ( Events.DTSTART, start_time );
//        cv.put ( Events.DTEND, end_time );
//        cv.put ( Events.CALENDAR_ID, id );
//        cv.put ( Events.EVENT_TIMEZONE, time_zone);
//
//        if (ActivityCompat.checkSelfPermission ( this.context, Manifest.permission.WRITE_CALENDAR ) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//
////        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
////            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, 0);
////        }
////
////        Uri uri = CalendarContract.Calendars.CONTENT_URI;
////        uri = uri.buildUpon().appendQueryParameter(CALLER_IS_SYNCADAPTER,"true")
////                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, "christianivan86@gmail.com")
////                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, "christianivan86@gmail.com").build();
//        cr.insert(Events.CONTENT_URI, cv);
//        Toast.makeText ( this.context,"EVENT ADDED", Toast.LENGTH_SHORT ).show ();
//
//    }


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
            bookButton.setBackgroundColor(Color.GRAY);
            bookButton.setEnabled(enabled);

        }


        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String info = timingsData.getDate()+ " "  + timingsData.getProf_facil() + " " + timingsData.getTime();
                //addEvent ("Booking with Professor","For Information System","SUTD", Calendar.getInstance ().getTimeInMillis (), Calendar.getInstance ().getTimeInMillis ()*60*60*1000,1, "Singapore" );
                //Toast.makeText(context, "pending request for: " + info, Toast.LENGTH_LONG).show();
                ContentResolver cr = context.getContentResolver ( );
                ContentValues cv = new ContentValues ( );
                cv.put ( Events.TITLE, "Booking with Professor" );
                cv.put ( Events.DESCRIPTION, "Test Example" );
                cv.put ( Events.EVENT_LOCATION, "Test Example" );
                cv.put ( Events.DTSTART,Calendar.getInstance ( ).getTimeInMillis ( ) );
                cv.put ( Events.DTEND, Calendar.getInstance ( ).getTimeInMillis ( ) );
                cv.put ( Events.CALENDAR_ID, 1 );
                cv.put ( Events.EVENT_TIMEZONE, "Test Example" );
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


                String x = String.valueOf (Calendar.getInstance ( ).getTimeInMillis ( ) );
                Uri uri = cr.insert ( Events.CONTENT_URI, cv );
                Toast.makeText ( context, x, Toast.LENGTH_SHORT ).show ( );
            }
        });

    }

    //how many results are displayed
    @Override
    public int getItemCount() {
        return timeDataList.size();
    }



}
