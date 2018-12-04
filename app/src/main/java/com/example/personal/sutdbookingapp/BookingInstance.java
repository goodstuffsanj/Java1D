package com.example.personal.sutdbookingapp;

public class BookingInstance {
    private String bookingId;
    private String name;
    private String date;
    private String startTime;
    private String endTime;
    private String location;
    private String imgUrl;

    public BookingInstance(String bookingId, String name, String date, String startTime, String endTime, String location, String imgUrl) {
        this.bookingId = bookingId;
        this.name = name;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.imgUrl = imgUrl;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
