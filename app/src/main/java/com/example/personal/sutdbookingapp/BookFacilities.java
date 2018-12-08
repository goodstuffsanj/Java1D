package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;

import java.util.ArrayList;
import java.util.List;

public class BookFacilities extends AppCompatActivity {

    private ArrayList<Bookable> facilities = new ArrayList<>();
    private String name;
    private String image;
    private String location;
    private List<String> blockedTimings;
    private String desc;
<<<<<<< HEAD
    private ProgressBar spinner;
=======
    private String username;
>>>>>>> 6a81ab52c8acfbd288581d29c03127aa3454c8a4

    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_facilities);
<<<<<<< HEAD
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
=======

        Intent intent = getIntent();
        username = intent.getStringExtra(HomePage.USERNAME);
        Log.i("DATABASEXXX_BookFacil", "onCreate: " + username);
>>>>>>> 6a81ab52c8acfbd288581d29c03127aa3454c8a4
        initImage();
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void initImage() {
        Database b = new Database(BookFacilities.this);

        //getting list of facilities from database
        b.getDataHandlerAll(new Database.DataHandlerAll() {
            @Override
            <T> void postQueryAll(PaginatedList<T> result) {
                for (int i = 0; i < result.size(); i++) {
                    FacilityTableDO facility = (FacilityTableDO) result.get(i);
                    name = facility.getFacilityName();
                    image = facility.getFacilityImage();
                    location = facility.getFacilityLocation();
                    blockedTimings = facility.getFacilityBlockedTimings();
                    desc = facility.getFacilityDescription();

                    Bookable facilInfo = new Bookable()
                            .setName(name)
                            .setImage(image)
                            .setLocation(location)
                            .setDescription(desc)
                            .setBlockedTimings(blockedTimings);
                    facilities.add(facilInfo);
                }
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        // Stuff that updates the UI
                        spinner.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            void showOnUI(Handler handler) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        initRecyclerView(username);
                    }
                });
            }
        }).getAll(FacilityTableDO.class);
//        for (int i=0; i<50; i++) {
//            Bookable facility = new Bookable();
//            facility.setName("Facility "+i);
//            facility.setImage("https://c1.staticflickr.com/2/1859/44728469401_62141e6b0b_k.jpg");
//            facility.setDescription("XXX, useful for A, use me carefully. I am very dangerous.");
//            facilities.add(facility);
//        }
//        initRecyclerView();
    }

    private void initRecyclerView(String username) {
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        listAdapter = new ListAdapter(facilities, this, username);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                listAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }
}