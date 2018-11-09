package com.example.personal.sutdbookingapp;

import java.util.Date;

public class MyBookInstance {
    public String type;
    public Bookable instance;
    public Date start;
    public Date end;

    public MyBookInstance(String type, Bookable instance, Date start, Date end) {
        this.type = type;
        this.instance = instance;
        this.start = start;
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public Bookable getInstance() {
        return instance;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }
}
