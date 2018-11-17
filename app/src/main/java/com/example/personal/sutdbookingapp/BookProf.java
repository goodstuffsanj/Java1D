package com.example.personal.sutdbookingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import java.util.ArrayList;

public class BookProf extends AppCompatActivity {

    private static final String TAG = "BookProf";

    private ArrayList<ListAdapter.ListItem> listItems = new ArrayList<>();
    private ArrayList<ListAdapter.ListItem> filteredList = new ArrayList<>();
    private SearchView searchView;

    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_prof);
        initImage();
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

    private void initImage() {
        listItems.add(new ListAdapter.ListItem("Test", "http://www.sunandmoonhotel.com/uploads/images/Gallery/Meeting-Room-Board-Room-Gallery/meeting-room-g1.jpg"));
        listItems.add(new ListAdapter.ListItem("Abcd", "http://www.sunandmoonhotel.com/uploads/images/Gallery/Meeting-Room-Board-Room-Gallery/meeting-room-g1.jpg"));
        listItems.add(new ListAdapter.ListItem("Abc", "http://www.sunandmoonhotel.com/uploads/images/Gallery/Meeting-Room-Board-Room-Gallery/meeting-room-g1.jpg"));
        listItems.add(new ListAdapter.ListItem("Abd", "http://www.sunandmoonhotel.com/uploads/images/Gallery/Meeting-Room-Board-Room-Gallery/meeting-room-g1.jpg"));

        for (int i=0; i<100; i++) {
            listItems.add(new ListAdapter.ListItem("Professor "+i, "https://www.biography.com/.image/t_share/MTE5NDg0MDU0OTU2OTAxOTAz/albert-einstein-9285408-1-402.jpg"));
        }

        initRecyclerView();
    }

    private void initRecyclerView() {
        if (searchView != null) {
            for (ListAdapter.ListItem item: listItems) {
                if (item.name.toLowerCase().startsWith(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        else {
            filteredList = listItems;
        }
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        ListAdapter listAdapter = new ListAdapter(filteredList, this);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }
}
