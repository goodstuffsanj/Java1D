package com.example.personal.sutdbookingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int n;
    private String username;
    public PagerAdapter(FragmentManager fm, int numberOfTabs, String username) {
        super(fm);
        this.n = numberOfTabs;
        this.username = username;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i) {
            case 0:
                Upcoming upcoming= Upcoming.newInstance(username);
                return upcoming;
            case 1:
                Waiting waiting = Waiting.newInstance(username);
                return waiting;
            case 2:
                Completed completed = Completed.newInstance(username);
                return completed;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return n;
    }
}
