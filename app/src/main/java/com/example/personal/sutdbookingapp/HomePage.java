package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.CalendarView;
import java.util.Calendar;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.gson.Gson;
import com.applandeo.materialcalendarview.adapters.CalendarPageAdapter;

// import org.joda.time.LocalDateTime;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private CardView book_facilities;
    private CardView book_prof;
    private CalendarView calendarView;
    private ScrollView scrollHome;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private SharedPreferences appData;
    private SharedPreferences.Editor appDataEditor;
    public final static String USERNAME = "USERNAME";
    public final static String DATE_PICKED = "DATE_PICKED";
    private String username;
    HashMap<Calendar, Integer> calendars = new HashMap<Calendar, Integer>();
    private static final String TAG = "HomePage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appData = getSharedPreferences("appData",MODE_PRIVATE);
        appDataEditor = appData.edit();
        username = appData.getString("username","no-name");
        Log.i(TAG, "onCreate: " + username);

        setContentView(R.layout.activity_home_page);
        scrollHome = findViewById(R.id.scrollHome);
        scrollHome.smoothScrollTo(0,0);
        List<EventDay> events = new ArrayList<>();

        drawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigation = findViewById(R.id.navigation);

        View header = navigation.getHeaderView(0);
        TextView text = header.findViewById(R.id.name);
        text.setText(username);
//        text.setTextColor(Color.WHITE);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // set item as selected to persist highlight

                menuItem.setChecked(true);
                int id = menuItem.getItemId();
                Log.i("Homepage", "onNavigationItemSelected: inside");

                if (id == R.id.nav_bookingHistory) {
                    Intent intent = new Intent(HomePage.this, Bookings.class);
                    intent.putExtra(USERNAME, username);
                    startActivity(intent);

                }
                else if (id == R.id.nav_logout) {
                    appDataEditor.putString("username","");
                    appDataEditor.putString("user","");
                    appDataEditor.apply();
                    Intent intent = new Intent(HomePage.this, LoginPageNew.class);
                    startActivity(intent);
                    finish();
                }

                // close drawer when item is tapped
                drawerLayout.closeDrawers();
                return true;
            }
        });

        Database b = new Database(this);
        b.getDataHandlerAll(new Database.DataHandlerAll() {
            @Override
            <T> void postQueryAll(PaginatedList<T> result) {
                for (int i = 0; i < result.size(); i ++) {
                    BookingInstanceTableDO bookingInstance = (BookingInstanceTableDO) result.get(i);

                    if (bookingInstance.getStudentName().toLowerCase().equals(username.toLowerCase()) && bookingInstance.getStatus().toLowerCase().equals("accepted")) {
                        Log.i(TAG, bookingInstance.getStudentName() + "  " + username);
                        LocalDateTime localDateTime = LocalDateTime.parse(bookingInstance.getTiming());
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(localDateTime.getYear(),localDateTime.getMonthOfYear()-1,localDateTime.getDayOfMonth());
                        Drawable a = CalendarUtils.getDrawableText(HomePage.this,"*", null, R.color.green, 10);
                        events.add(new EventDay(calendar, a));

                    }

                }
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        // Stuff that updates the UI
                        calendarView.setEvents(events);
                    }
                });

            }

            @Override
            void showOnUI(Handler handler) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                    }
                });
            }
        }).getAll(BookingInstanceTableDO.class);

        /*Calendar calendar = Calendar.getInstance();
        calendar.set(2018,10,28);

        Drawable a = CalendarUtils.getDrawableText(this, "+32", null, R.color.green, 10);
        events.add(new EventDay(calendar, a));*/

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Intent intent = new Intent(HomePage.this,Events.class);
                intent.putExtra("data", eventDay.getCalendar().getTimeInMillis());
                startActivity(intent);
            }
        });

        book_facilities = (CardView) findViewById(R.id.book_facilities);
        book_prof = (CardView) findViewById(R.id.book_prof);


        book_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, BookProf.class);
                intent.putExtra(USERNAME, username);
                startActivity(intent);
            }
        });

        book_facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, BookFacilities.class);
                intent.putExtra(USERNAME, username);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {

            return true;
        }

        return super.onOptionsItemSelected(item);


    }
}