package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.joda.time.LocalDateTime;


//get available timings_list for booking
public class TimingsData {
    private LocalDateTime time;
    private String name;
    private Boolean availability;

//    public TimingsData(String time, Boolean available) {
//        this.time = time;
//        this.availability = available;
//    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
       return time;
    }

    public boolean getAvailability() {
        return availability;
   }

    public TimingsData setName(String name) {
        this.name = name;
        return this;
    }

    public TimingsData setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public TimingsData setAvailability(Boolean availability) {
        this.availability = availability;
        return this;
    }


}
