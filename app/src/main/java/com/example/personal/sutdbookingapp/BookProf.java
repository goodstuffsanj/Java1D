package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class BookProf extends AppCompatActivity {

    private static final String TAG = "BookProf";
    private ArrayList<Bookable> profs = new ArrayList<>();
    private ListAdapter listAdapter;
    private String name;
    private String image;
    private String location;
    private String email;
    private String contact;
    private List<String> blockedTimings;
    private String desc;
    private String calendar;
    private ProgressBar spinner;
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_prof);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        getData();

        Intent intent = getIntent();
        username = intent.getStringExtra(HomePage.USERNAME);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }




    //get list of profs to show
    private void getData() {
        Database b = new Database(BookProf.this);
        b.getDataHandlerAll(new Database.DataHandlerAll() {
            @Override
            <T> void postQueryAll(PaginatedList<T> result) {
                for (int i = 0; i < result.size(); i ++) {
                    ProfTableDO prof = (ProfTableDO) result.get(i);
                    name = prof.getProfName();
                    image = prof.getProfImage();
                    location = prof.getProfOffice();
                    email = prof.getProfEmail();
                    contact = prof.getProfContact();
                    desc = prof.getProfDescription();
                    blockedTimings = prof.getProfBlockedTimings();

                    Bookable profInfo = new Bookable()
                            .setName(name)
                            .setImage(image)
                            .setLocation(location)
                            .setEmail(email)
                            .setContact(contact)
                            .setDescription(desc)
                            .setBlockedTimings(blockedTimings);
                    profs.add(profInfo);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {

                            // Stuff that updates the UI
                            spinner.setVisibility(View.GONE);
                        }
                    });
                    Log.i(TAG, "postQueryAll: done");
                    Log.i(TAG, "postQueryAll: " + profs.size());
                }

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
        }).getAll(ProfTableDO.class);
    }

    public void initRecyclerView(String username) {
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        Collections.sort(profs, new Comparator<Bookable>() {
            @Override
            public int compare(Bookable o1, Bookable o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        listAdapter = new ListAdapter(profs, this, username);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.i(TAG, "initRecyclerView: " + profs.size());
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