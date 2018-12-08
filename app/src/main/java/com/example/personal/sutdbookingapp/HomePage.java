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
    private SharedPreferences mPreferences;
    public final static String USERNAME = "USERNAME";
    private String username;
    public String TAG = "DB";
    HashMap<Calendar, Integer> calendars = new HashMap<Calendar, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        scrollHome = findViewById(R.id.scrollHome);
        scrollHome.smoothScrollTo(0,0);
        List<EventDay> events = new ArrayList<>();

        mPreferences = getSharedPreferences("sharedPrefFileStudent", MODE_PRIVATE);
        username = mPreferences.getString(USERNAME, "");

        Intent intent = getIntent();
        String username_temp = intent.getStringExtra(LoginPageNew.USERNAME);
        if (!(username_temp == null)) {
            //i.e. intent.getStringExtra(LoginPageNew.USERNAME, username); (default value)
            username = username_temp;
        }

        drawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigation = findViewById(R.id.navigation);

//        View header = navigation.getHeaderView(0);
//        TextView text = header.findViewById(R.id.name);
//        text.setText(username);
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
                    Intent intent = new Intent(HomePage.this, LoginPageNew.class);
                    startActivity(intent);
                }

                // close drawer when item is tapped
                drawerLayout.closeDrawers();
                return true;
            }
        });


        // ***************************************************** database query
        Database test = new Database(HomePage.this);
        StudentTableDO studentTableDO = new StudentTableDO();
        StudentTableDO studentTableDO1 = new StudentTableDO();

        studentTableDO.setStudentID("222");
        studentTableDO.setStudentName("best student");
        studentTableDO.setStudentPassword("2333666");
        // ***************************************************** Create Data
        Log.i(TAG,"item is creating");
        test.create(studentTableDO);
        Log.i(TAG,"item is creating!!! yay!!!!!!!!!");


        // ***************************************************** Update Data
//        test.update(studentTableDO);


        //creating database for prof
//        ArrayList<String> blockedTimings = new ArrayList<>();
//        blockedTimings.add(new LocalDateTime(2018, 12, 4, 17, 0 ).toString());
//        blockedTimings.add(new LocalDateTime(2018, 12, 5, 16, 0 ).toString());
//
//        for (int i = 0; i < 300; i ++) {
//            ProfTableDO prof = new ProfTableDO();
//            prof.setProfName("Prof " + i);
//            prof.setProfPassword("Sutd1234");
//            prof.setProfImage("https://www.biography.com/.image/t_share/MTE5NDg0MDU0OTU2OTAxOTAz/albert-einstein-9285408-1-402.jpg");
//            prof.setProfOffice("Building 1 Level 5 1.502-8");
//            prof.setProfBlockedTimings(blockedTimings);
//            prof.setProfCalendar("infosysapi883@gmail.com");
//            prof.setProfEmail("abc@mymail.sutd.edu.sg");
//            prof.setProfContact("+65 69876543");
//            prof.setProfDescription("I am a passionate professor. I studied x in university XYZ and I have been doing research about Y for K years.");
//            prof.setProfPillar("ISTD");
//
//            b.create(prof);
//        }



        // Update Data

        /*james.setName("James Andrew Pohadi");
        james.setUserId("1002899");
        james.setPassword("secret");
        b.update(james); */

        // ***************************************************** Delete Data

//        studentTableDO1.setUserId("ss");
//        studentTableDO1.setPassword("ss");
//        test.delete(studentTableDO1);

         //Query Data -> check Logcat Yesss
        // index
//        test.getQueryHandler(new Database.QueryHandler(){
//            @Override
//            <T> void postQuery(PaginatedList<T> result) {
//                Log.i(TAG,"this is the result" + result.toString());
//            }
//        }).getQuery(StudentTableDO.class,"Names",studentTableDO);
////
        // Get Data -> check Logcat dataReceived
        // hash key
        test.getDataHandler(new Database.DataHandler() {
            @Override
            <T> void postReceivedData(T result) {
                StudentTableDO a = (StudentTableDO) result;
//                Log.i("DB_dataReceived ", ((StudentTableDO) result).getStudentPassword());
            }
        }).getData(StudentTableDO.class,"222");
        test.getDataHandler(new Database.DataHandler() {
            @Override
            <T> void postReceivedData(T result) {
                ProfTableDO a = (ProfTableDO) result;
//                Log.i("DB_dataReceived ", (a.getProfPassword()));
            }
        }).getData(ProfTableDO.class,"Prof 0");
/*
                //Log.d("Yesss",);
//                Iterator<T> iter = result.iterator();
//                while (iter.hasNext()) {
//                    Log.i("yesss", "postQuery: " + iter);
//                }

            }
        }).getQuery(NewsDO.class,"Authors", news);

        // Get Data -> check Logcat dataReceived
        // hash key (get specific entry by id)
        b.getDataHandler(new Database.DataHandler() {
            @Override
            <T> void postReceivedData(T result) {
                NewsDO a = (NewsDO) result;
                Log.d("dataReceived",a.getContent());

*/


//////////////////////////////////////////////////////////////////////////////////


        //how to get all table items
//        String username;
//        b.getDataHandlerAll(new Database.DataHandlerAll() {
//            @Override
//            <T> void postQueryAll(PaginatedList<T> result) {
//                for (int i = 0; i < result.size(); i ++) {
//                    ProfTableDO prof = (ProfTableDO) result.get(i);
//                    username = prof.getProfName();
//                }
//            }
//
//            @Override
//            void showOnUI(Handler handler) {
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText(username);
//                    }
//                });
//
//            }
//        }).getAll(ProfTableDO.class);

        Database b = new Database(this);
        b.getDataHandlerAll(new Database.DataHandlerAll() {
            @Override
            <T> void postQueryAll(PaginatedList<T> result) {
                for (int i = 0; i < result.size(); i ++) {
                    BookingInstanceTableDO bookingInstance = (BookingInstanceTableDO) result.get(i);
                    LocalDateTime localDateTime = LocalDateTime.parse(bookingInstance.getTiming());
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(localDateTime.getYear(),localDateTime.getMonthOfYear()-1,localDateTime.getDayOfMonth());
                    if (calendars.containsKey(calendar)) {
                        calendars.put(calendar,calendars.get(calendar)+1);
                    } else {
                        calendars.put(calendar,1);
                    }
                }

                for (Calendar c:calendars.keySet()) {
                    Drawable a = CalendarUtils.getDrawableText(HomePage.this,"+"+ calendars.get(c).toString(), null, R.color.green, 10);
                    events.add(new EventDay(c, a));
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
        calendarView.clearFocus();
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDayCalendar = eventDay.getCalendar();
                Toast.makeText(HomePage.this,clickedDayCalendar.toString(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomePage.this,Bookings.class);
                intent.putExtra(USERNAME, username);
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