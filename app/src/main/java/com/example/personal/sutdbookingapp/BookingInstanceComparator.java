package com.example.personal.sutdbookingapp;

import java.util.Comparator;

public class BookingInstanceComparator implements Comparator<BookingInstance> {
    @Override
    public int compare(BookingInstance o1, BookingInstance o2) {
        return o2.getStartTime().compareTo(o1.getStartTime());
    }
}
