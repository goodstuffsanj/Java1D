package com.example.personal.sutdbookingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int n;
    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.n = numberOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i) {
            case 0:
                Upcoming upcoming= new Upcoming();
                return upcoming;
            case 1:
                Waiting waiting = new Waiting();
                return waiting;
            case 2:
                Completed completed = new Completed();
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
