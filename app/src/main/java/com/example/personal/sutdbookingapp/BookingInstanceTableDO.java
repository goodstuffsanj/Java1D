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
    private String _message;
    private String _name;
    private String _status;
    private String _studentName;
    private String _timing;

    @DynamoDBHashKey(attributeName = "BookingID")
    @DynamoDBAttribute(attributeName = "BookingID")
    public String getBookingID() {
        return _bookingID;
    }

    public void setBookingID(final String _bookingID) {
        this._bookingID = _bookingID;
    }
    @DynamoDBAttribute(attributeName = "Message")
    public String getMessage() {
        return _message;
    }

    public void setMessage(final String _message) {
        this._message = _message;
    }
    @DynamoDBAttribute(attributeName = "Name")
    public String getName() {
        return _name;
    }

    public void setName(final String _name) {
        this._name = _name;
    }
    @DynamoDBAttribute(attributeName = "Status")
    public String getStatus() {
        return _status;
    }

    public void setStatus(final String _status) {
        this._status = _status;
    }
    @DynamoDBAttribute(attributeName = "StudentName")
    public String getStudentName() {
        return _studentName;
    }

    public void setStudentName(final String _studentName) {
        this._studentName = _studentName;
    }
    @DynamoDBAttribute(attributeName = "Timing")
    public String getTiming() {
        return _timing;
    }

    public void setTiming(final String _timing) {
        this._timing = _timing;
    }

}
