package com.example.personal.sutdbookingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class UpcomingAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<Fragment> upcomingList;

    public UpcomingAdapter(FragmentManager fm, ArrayList<Fragment> upcomingList) {
        super(fm);
        this.upcomingList = upcomingList;
    }

    @Override
    public Fragment getItem(int i) {
        return upcomingList.get(i);
    }

    @Override
    public int getCount() {
        return upcomingList.size();
    }



}
