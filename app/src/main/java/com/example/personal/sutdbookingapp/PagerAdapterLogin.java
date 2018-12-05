package com.example.personal.sutdbookingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapterLogin extends FragmentStatePagerAdapter {
    int n;
    public PagerAdapterLogin(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.n = numberOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch(i) {
            case 0:
                Student student= new Student();
                return student;
            case 1:
                Staff staff = new Staff();
                return staff;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return n;
    }
}
