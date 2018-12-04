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

    public Bookable setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Bookable setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Bookable setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Bookable setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getContact() {
        return contact;
    }

    public Bookable setContact(String contact) {
        this.contact = contact;
        return this;
    }

    public String getImage() {
        return image;
    }

    public Bookable setImage(String image) {
        this.image = image;
        return this;
    }

    public List<String> getBlockedTimings() {
        return blockedTimings;
    }

    public Bookable setBlockedTimings(List<String> blockedTimings) {
        this.blockedTimings = blockedTimings;
        return this;
    }

    public String getCalendar() {
        return calendar;
    }

    public Bookable setCalendar(String calendar) {
        this.calendar = calendar;
        return this;
    }
}


