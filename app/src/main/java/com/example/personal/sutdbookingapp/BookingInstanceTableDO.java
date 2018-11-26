package com.example.personal.sutdbookingapp;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "sutdbookingapp-mobilehub-1464484747-BookingInstanceTable")

public class BookingInstanceTableDO {
    private String _bookingID;
    private String _date;
    private String _profID;
    private String _status;
    private String _studentID;
    private String _timing;

    @DynamoDBHashKey(attributeName = "BookingID")
    @DynamoDBAttribute(attributeName = "BookingID")
    public String getBookingID() {
        return _bookingID;
    }

    public void setBookingID(final String _bookingID) {
        this._bookingID = _bookingID;
    }
    @DynamoDBAttribute(attributeName = "Date")
    public String getDate() {
        return _date;
    }

    public void setDate(final String _date) {
        this._date = _date;
    }
    @DynamoDBAttribute(attributeName = "ProfID")
    public String getProfID() {
        return _profID;
    }

    public void setProfID(final String _profID) {
        this._profID = _profID;
    }
    @DynamoDBAttribute(attributeName = "Status")
    public String getStatus() {
        return _status;
    }

    public void setStatus(final String _status) {
        this._status = _status;
    }
    @DynamoDBAttribute(attributeName = "studentID")
    public String getStudentID() {
        return _studentID;
    }

    public void setStudentID(final String _studentID) {
        this._studentID = _studentID;
    }
    @DynamoDBAttribute(attributeName = "timing")
    public String getTiming() {
        return _timing;
    }

    public void setTiming(final String _timing) {
        this._timing = _timing;
    }

}
