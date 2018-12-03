package com.example.personal.sutdbookingapp;


import org.joda.time.LocalDateTime;

/**
 * Created by sanjayshankar on 24/11/18.
 */

public class RequestsData {

    private String senderName;
    private LocalDateTime time;
    private String reason;
    private String bookingID;

    public String getSenderName() {
        return senderName;
    }

    public RequestsData setSenderName(String senderName) {
        this.senderName = senderName;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public RequestsData setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public RequestsData setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public String getBookingID() {
        return bookingID;
    }

    public RequestsData setBookingID(String bookingID) {
        this.bookingID = bookingID;
        return this;
    }

    RequestsData(String bookingID, String senderName, LocalDateTime time, String reason) {
        this.bookingID = bookingID;
        this.senderName = senderName;
        this.time = time;
        this.reason = reason;
    }

}


//    public RequestsData(String mSender, String mTitle, String mDetails, String mTime) {
//        this.mSender = mSender;
//        this.mTitle = mTitle;
//        this.mDetails = mDetails;
//        this.mTime = mTime;
//    }
//
//    public String getmSender() {
//        return mSender;
//    }
//
//    public String getmTitle() {
//        return mTitle;
//    }
//
//    public String getmDetails() {
//        return mDetails;
//    }
//
//    public String getmTime() {
//        return mTime;
//    }