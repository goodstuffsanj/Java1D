package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ScrollView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.CalendarView;
import java.util.Calendar;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.gson.Gson;
import com.applandeo.materialcalendarview.adapters.CalendarPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends AppCompatActivity {
    private CardView book_facilities;
    private CardView book_prof;
    private CalendarView calendarView;
    private ScrollView scrollHome;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    public String TAG = "DB";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        scrollHome = findViewById(R.id.scrollHome);
        scrollHome.smoothScrollTo(0,0);
        List<EventDay> events = new ArrayList<>();

        drawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
// ************ database query ************ //
        Database test = new Database(HomePage.this);
        StudentTableDO studentTableDO = new StudentTableDO();

        studentTableDO.setUserId("222");
        studentTableDO.setName("best student");
        studentTableDO.setPassword("2333666");

        // Create Data
        Log.i(TAG,"item is creating");
        test.create(studentTableDO);
        Log.i(TAG,"item is creating!!! yay!!!!!!!!!");

        // Update Data
        test.update(studentTableDO);

        /*james.setName("James Andrew Pohadi");
        james.setUserId("1002899");
        james.setPassword("secret");
        b.update(james); */

        // Delete Data

        studentTableDO.setUserId("1002899");
        studentTableDO.setPassword("secret");
        test.delete(studentTableDO);

        // Query Data -> check Logcat Yesss
        // index

//        test.getQueryHandler(new Database.QueryHandler(){
//            @Override
//            <T> void postQuery(PaginatedList<T> result) {
//                Log.d("Yesss",result.toString());
//            }
//        }).getQuery(ProfTableDO.class,"ProfID",profTableDO);
//
//        // Get Data -> check Logcat dataReceived
//        // hash key
//        test.getDataHandler(new Database.DataHandler() {
//            @Override
//            <T> void postReceivedData(T result) {
//                Log.d("dataReceived",result.toString());
//            }
//        }).getData(ProfTableDO.class,"222","2333666");

        Calendar calendar = Calendar.getInstance();
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
                Toast.makeText(HomePage.this,clickedDayCalendar.toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomePage.this,Bookings.class);
                startActivity(intent);
            }
        });

        book_facilities = (CardView) findViewById(R.id.book_facilities);
        book_prof = (CardView) findViewById(R.id.book_prof);


        book_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, BookProf.class);
                startActivity(intent);
            }
        });

        book_facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, BookFacilities.class);
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
