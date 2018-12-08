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

@DynamoDBTable(tableName = "sutdbookingapp-mobilehub-1464484747-ProfTable")

public class ProfTableDO {
    private String _profName;
    private List<String> _profBlockedTimings;
    private String _profCalendar;
    private String _profContact;
    private String _profDescription;
    private String _profEmail;
    private String _profId;
    private String _profImage;
    private String _profOffice;
    private String _profPassword;
    private String _profPillar;

    @DynamoDBHashKey(attributeName = "ProfName")
    @DynamoDBAttribute(attributeName = "ProfName")
    public String getProfName() {
        return _profName;
    }

    public void setProfName(final String _profName) {
        this._profName = _profName;
    }
    @DynamoDBAttribute(attributeName = "ProfBlockedTimings")
    public List<String> getProfBlockedTimings() {
        return _profBlockedTimings;
    }

    public void setProfBlockedTimings(final List<String> _profBlockedTimings) {
        this._profBlockedTimings = _profBlockedTimings;
    }
    @DynamoDBAttribute(attributeName = "ProfCalendar")
    public String getProfCalendar() {
        return _profCalendar;
    }

    public void setProfCalendar(final String _profCalendar) {
        this._profCalendar = _profCalendar;
    }
    @DynamoDBAttribute(attributeName = "ProfContact")
    public String getProfContact() {
        return _profContact;
    }

    public void setProfContact(final String _profContact) {
        this._profContact = _profContact;
    }
    @DynamoDBAttribute(attributeName = "ProfDescription")
    public String getProfDescription() {
        return _profDescription;
    }

    public void setProfDescription(final String _profDescription) {
        this._profDescription = _profDescription;
    }
    @DynamoDBAttribute(attributeName = "ProfEmail")
    public String getProfEmail() {
        return _profEmail;
    }

    public void setProfEmail(final String _profEmail) {
        this._profEmail = _profEmail;
    }
    @DynamoDBAttribute(attributeName = "ProfId")
    public String getProfId() {
        return _profId;
    }

    public void setProfId(final String _profId) {
        this._profId = _profId;
    }
    @DynamoDBAttribute(attributeName = "ProfImage")
    public String getProfImage() {
        return _profImage;
    }

    public void setProfImage(final String _profImage) {
        this._profImage = _profImage;
    }
    @DynamoDBAttribute(attributeName = "ProfOffice")
    public String getProfOffice() {
        return _profOffice;
    }

    public void setProfOffice(final String _profOffice) {
        this._profOffice = _profOffice;
    }
    @DynamoDBAttribute(attributeName = "ProfPassword")
    public String getProfPassword() {
        return _profPassword;
    }

    public void setProfPassword(final String _profPassword) {
        this._profPassword = _profPassword;
    }
    @DynamoDBIndexHashKey(attributeName = "ProfPillar", globalSecondaryIndexName = "ProfPillar")
    public String getProfPillar() {
        return _profPillar;
    }

    public void setProfPillar(final String _profPillar) {
        this._profPillar = _profPillar;
    }

}
