package com.example.personal.sutdbookingapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;

public class BookFacilities extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ArrayList<ListAdapter.ListItem> listItems = new ArrayList<>();
    private ArrayList<ListAdapter.ListItem> filteredList  = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_facilities);
        initImage();
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);

    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    //use the query to search your data somehow
    private void doSearch(String query) {
        filteredList.clear();
        for (ListAdapter.ListItem item: listItems) {
            if (item.name.toLowerCase().contains(query.toLowerCase())){
                filteredList.add(item);
            }
        }
        initRecyclerView();
    }

    private void initImage() {
        for (int i=0; i<50; i++) {
            listItems.add(new ListAdapter.ListItem("Facility "+i, "http://www.sunandmoonhotel.com/uploads/images/Gallery/Meeting-Room-Board-Room-Gallery/meeting-room-g1.jpg"));
        }
        filteredList = (ArrayList) listItems.clone();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView= findViewById(R.id.recycler_view);
        ListAdapter listAdapter = new ListAdapter(filteredList, this);
        recyclerView.setAdapter(listAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        doSearch(s);
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }
}
