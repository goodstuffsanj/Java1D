package com.example.personal.sutdbookingapp;

import java.util.Date;

/**
 * Created by sanjayshankar on 24/11/18.
 */

public class RequestsData {

    private String senderName;
    private String date;
    private String reason;

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }

    RequestsData(String senderName, String date, String reason) {
        this.senderName = senderName;
        this.date = date;
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