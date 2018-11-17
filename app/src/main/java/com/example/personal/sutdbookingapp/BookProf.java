package com.example.personal.sutdbookingapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class BookProf extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private static final String TAG = "BookProf";

    private ArrayList<ListAdapter.ListItem> listItems = new ArrayList<>();
    private ArrayList<ListAdapter.ListItem> filteredList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_prof);
        initImage();
        handleIntent(getIntent());
        Log.i(TAG, "onCreate: " + filteredList.size());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
        Log.i(TAG, "onCreate2: " + filteredList.size());
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
            doSearch(query);


        }
    }

    //use the query to search your data somehow
    private void doSearch(String query) {
        filteredList.clear();
        for (ListAdapter.ListItem item: listItems) {
            if (item.name.toLowerCase().contains(query.toLowerCase())){
                Log.i(TAG, "handleIntent: " + item.name);
                filteredList.add(item);
            }
        }
        initRecyclerView();
    }


    private void initImage() {
        listItems.add(new ListAdapter.ListItem("Test", null));
        listItems.add(new ListAdapter.ListItem("Abcd", null));
        listItems.add(new ListAdapter.ListItem("Abc", null));
        listItems.add(new ListAdapter.ListItem("Abd", null));

        for (int i=0; i<100; i++) {
            listItems.add(new ListAdapter.ListItem("Professor "+i, "https://www.biography.com/.image/t_share/MTE5NDg0MDU0OTU2OTAxOTAz/albert-einstein-9285408-1-402.jpg"));
        }
        Log.i(TAG, "initImage: " + listItems.get(0).name);
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

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        return true;
    }

    public boolean onQueryTextChange(String newText) {
        doSearch(newText);
        return false;
    }

    public boolean onQueryTextSubmit(String query) {
        return false;
    }
}
