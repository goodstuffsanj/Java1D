package com.example.personal.sutdbookingapp;

import java.util.ArrayList;
import java.util.List;

public class Bookable {
    private String name;
    private String location;
    private String description;
    private String email;
    private String contact;
    private String image;
    private List<String> blockedTimings;
    private String calendar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getBlockedTimings() {
        return blockedTimings;
    }

    public void setBlockedTimings(List<String> blockedTimings) {
        this.blockedTimings = blockedTimings;
    }

    public String getCalendar() {
        return calendar;
    }

    public void setCalendar(String calendar) {
        this.calendar = calendar;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}


