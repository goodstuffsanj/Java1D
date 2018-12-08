package com.example.personal.sutdbookingapp;


import org.joda.time.LocalDateTime;

public class BookingInstance {
    private String bookingId;
    private String name;
    private String bookerName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private String imgUrl;
    private String status;

    public BookingInstance(String bookingId, String name, String bookerName, String startTime, String endTime, String location, String imgUrl, String status) {
        this.bookingId = bookingId;
        this.name = name;
        this.bookerName = bookerName;
        this.startTime = new LocalDateTime(startTime);
        this.endTime = new LocalDateTime(endTime);
        this.location = location;
        this.imgUrl = imgUrl;
        this.status = status;
    }

    public BookingInstance(String bookingId, String name, String bookerName, String time, String location, String status) {
        this.bookingId = bookingId;
        this.name = name;
        this.bookerName = bookerName;
        this.startTime = new LocalDateTime(time);
        this.endTime = this.startTime.plusMinutes(30);
        this.location = location;
        this.status = status;

    }

    public String getBookingId() {
        return bookingId;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getStatus() {
        return status;
    }


    public String getBookerName() {
        return bookerName;
    }
}
