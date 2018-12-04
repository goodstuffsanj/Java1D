package com.example.personal.sutdbookingapp;

public class HistoryData {
    private String bookingHistory;
    private String time;
    private String bookingStatus;


    public String getBookingHistory() {
        return bookingHistory;
    }

    public void setBookingHistory(String bookingHistory) {
        this.bookingHistory = bookingHistory;
    }
    public String getStatus(){
        return bookingStatus;
    }
    public void setStatus(String bookingStatus){
        this.bookingStatus = bookingStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
