package com.example.personal.sutdbookingapp;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;

public class Database {
    static DynamoDBMapper dynamoDBMapper;

    public static class Student {
        public void createNews(String name, String id) {
            final NewsDO newsItem = new NewsDO();

            newsItem.setUserId(id);

            newsItem.setArticleId("Article1");
            newsItem.setContent("This is the article content");
            newsItem.setAuthor("James");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    dynamoDBMapper.save(newsItem);
                    // Item saved
                }
            }).start();
        }
    }

    public static class Facility {

    }

    public static class BookingInstance {

    }

}
