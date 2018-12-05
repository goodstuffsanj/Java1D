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

@DynamoDBTable(tableName = "sutdbookingapp-mobilehub-1464484747-News")

public class NewsDO {
    private String _userId;
    private String _articleId;
    private String _author;
    private String _category;
    private String _content;
    private Double _creationDate;
    private Set<String> _keywords;
    private String _title;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBRangeKey(attributeName = "articleId")
    @DynamoDBAttribute(attributeName = "articleId")
    public String getArticleId() {
        return _articleId;
    }

    public void setArticleId(final String _articleId) {
        this._articleId = _articleId;
    }
    @DynamoDBIndexHashKey(attributeName = "author", globalSecondaryIndexName = "Authors")
    public String getAuthor() {
        return _author;
    }

    public void setAuthor(final String _author) {
        this._author = _author;
    }
    @DynamoDBIndexHashKey(attributeName = "category", globalSecondaryIndexName = "Categories")
    public String getCategory() {
        return _category;
    }

    public void setCategory(final String _category) {
        this._category = _category;
    }
    @DynamoDBAttribute(attributeName = "content")
    public String getContent() {
        return _content;
    }

    public void setContent(final String _content) {
        this._content = _content;
    }
    @DynamoDBIndexRangeKey(attributeName = "creationDate", globalSecondaryIndexName = "Categories")
    public Double getCreationDate() {
        return _creationDate;
    }

    public void setCreationDate(final Double _creationDate) {
        this._creationDate = _creationDate;
    }
    @DynamoDBAttribute(attributeName = "keywords")
    public Set<String> getKeywords() {
        return _keywords;
    }

    public void setKeywords(final Set<String> _keywords) {
        this._keywords = _keywords;
    }
    @DynamoDBAttribute(attributeName = "title")
    public String getTitle() {
        return _title;
    }

    public void setTitle(final String _title) {
        this._title = _title;
    }

}
