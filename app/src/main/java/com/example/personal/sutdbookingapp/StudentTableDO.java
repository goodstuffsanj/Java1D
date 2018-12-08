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

@DynamoDBTable(tableName = "sutdbookingapp-mobilehub-1464484747-StudentTable")

public class StudentTableDO {
    private String _studentID;
    private String _studentImage;
    private String _studentName;
    private String _studentPassword;

    @DynamoDBHashKey(attributeName = "StudentID")
    @DynamoDBAttribute(attributeName = "StudentID")
    public String getStudentID() {
        return _studentID;
    }

    public void setStudentID(final String _studentID) {
        this._studentID = _studentID;
    }
    @DynamoDBAttribute(attributeName = "StudentImage")
    public String getStudentImage() {
        return _studentImage;
    }

    public void setStudentImage(final String _studentImage) {
        this._studentImage = _studentImage;
    }
    @DynamoDBAttribute(attributeName = "StudentName")
    public String getStudentName() {
        return _studentName;
    }

    public void setStudentName(final String _studentName) {
        this._studentName = _studentName;
    }
    @DynamoDBAttribute(attributeName = "StudentPassword")
    public String getStudentPassword() {
        return _studentPassword;
    }

    public void setStudentPassword(final String _studentPassword) {
        this._studentPassword = _studentPassword;
    }

}
