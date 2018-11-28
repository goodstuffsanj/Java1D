package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


//get available timings_list for booking
public class TimingsData {
    private String time;
    private String prof_facil;
    private Boolean availability;

//    public TimingsData(String time, Boolean available) {
//        this.time = time;
//        this.availability = available;
//    }

    private String date;

    public String getProf_facil() {
        return prof_facil;
    }

    public String getDate() {
        return date;
    }

   public String getTime() {
       return time;
   }

   public boolean getAvailability() {
        return availability;
   }

   public void setTime(String time) {
        this.time = time;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }


}
