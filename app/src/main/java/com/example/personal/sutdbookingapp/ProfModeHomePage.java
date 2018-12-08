package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProfModeHomePage extends AppCompatActivity {
    private CardView book_facilities;
    private CardView meeting_requests;
    private CalendarView calendarView;
    private ScrollView scrollHome;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    public final static String USERNAME = "USERNAME";
    private String username;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_mode_home_page);
        setTitle("My Activity");

        mPreferences = getSharedPreferences("sharedPrefFileStudent", MODE_PRIVATE);
        username = mPreferences.getString(USERNAME, "");

        Intent intent = getIntent();
        String username_temp = intent.getStringExtra(LoginPageNew.USERNAME);
        if (!(username_temp == null)) {
            //i.e. intent.getStringExtra(LoginPageNew.USERNAME, username); (default value)
            username = username_temp;
        }

        Log.i("DATABASEXXX", "onCreate: " + username);


        scrollHome = findViewById(R.id.scrollHome);
        scrollHome.smoothScrollTo(0,0);
        List<EventDay> events = new ArrayList<>();

        drawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigation = findViewById(R.id.navigation);

        //set username on navigation panel
//        View header = navigation.getHeaderView(0);
//        TextView navUsername = header.findViewById(R.id.name);
//        navUsername.setText(username);

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                // set item as selected to persist highlight

                menuItem.setChecked(true);
                int id = menuItem.getItemId();
                Log.i("Homepage", "onNavigationItemSelected: inside");

                if (id == R.id.nav_bookingHistory) {
                    Intent intent = new Intent(ProfModeHomePage.this, Bookings.class);
                    intent.putExtra(USERNAME, username);
                    startActivity(intent);

                }
                else if (id == R.id.nav_logout) {
                    Intent intent = new Intent(ProfModeHomePage.this, LoginPageNew.class);
                    startActivity(intent);
                }

                // close drawer when item is tapped
                drawerLayout.closeDrawers();
                return true;
            }
        });


        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.set(2018,10,28);
        Drawable a = CalendarUtils.getDrawableText(this, "+32", null, R.color.green, 10);
        events.add(new EventDay(calendar, a));

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        calendarView.clearFocus();
        calendarView.setEvents(events);
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                Toast.makeText(ProfModeHomePage.this,clickedDayCalendar.toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfModeHomePage.this,Bookings.class);
                intent.putExtra(USERNAME, username);
                startActivity(intent);
            }
        });


        book_facilities = (CardView) findViewById(R.id.book_facilities);
        meeting_requests = (CardView) findViewById(R.id.meeting_requests);

        meeting_requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfModeHomePage.this, ProfModeRequestsPage.class);
                intent.putExtra(USERNAME, username);
                startActivity(intent);
            }
        });

        book_facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfModeHomePage.this, BookFacilities.class);
                intent.putExtra(USERNAME, username);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor preferenceEditor = mPreferences.edit();
        preferenceEditor.putString(USERNAME, username);
        preferenceEditor.apply();
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
