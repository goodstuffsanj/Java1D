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

@DynamoDBTable(tableName = "sutdbookingapp-mobilehub-1464484747-FacilityTable")

public class FacilityTableDO {
    private String _facilityName;
    private List<String> _facilityBlockedTimings;
    private String _facilityDescription;
    private String _facilityImage;
    private String _facilityLocation;

    @DynamoDBHashKey(attributeName = "FacilityName")
    @DynamoDBAttribute(attributeName = "FacilityName")
    public String getFacilityName() {
        return _facilityName;
    }

    public void setFacilityName(final String _facilityName) {
        this._facilityName = _facilityName;
    }
    @DynamoDBAttribute(attributeName = "FacilityBlockedTimings")
    public List<String> getFacilityBlockedTimings() {
        return _facilityBlockedTimings;
    }

    public void setFacilityBlockedTimings(final List<String> _facilityBlockedTimings) {
        this._facilityBlockedTimings = _facilityBlockedTimings;
    }
    @DynamoDBAttribute(attributeName = "FacilityDescription")
    public String getFacilityDescription() {
        return _facilityDescription;
    }

    public void setFacilityDescription(final String _facilityDescription) {
        this._facilityDescription = _facilityDescription;
    }
    @DynamoDBAttribute(attributeName = "FacilityImage")
    public String getFacilityImage() {
        return _facilityImage;
    }

    public void setFacilityImage(final String _facilityImage) {
        this._facilityImage = _facilityImage;
    }
    @DynamoDBAttribute(attributeName = "FacilityLocation")
    public String getFacilityLocation() {
        return _facilityLocation;
    }

    public void setFacilityLocation(final String _facilityLocation) {
        this._facilityLocation = _facilityLocation;
    }

}
