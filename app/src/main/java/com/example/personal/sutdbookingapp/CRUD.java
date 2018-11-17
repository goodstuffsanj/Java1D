package com.example.personal.sutdbookingapp;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;

public class CRUD {
    private String student_id;
    private String password;
    DynamoDBMapper dynamoDBMapper;

    public void createNews() {
        student_id = "1009";
        password = "1234";
        final DBstudent_table.AWS newsItem = new DBstudent_table.AWS();

        newsItem.setStudent_id(student_id);
        newsItem.setPassword(password);
        System.out.println("student_id and password are set");

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newsItem);
                System.out.println("new item is adding");
                // Item saved
            }
        }).start();
    }



    public static void main(String args[]) {
        DBstudent_table.AWS aws = new DBstudent_table.AWS();
        CRUD crud = new CRUD();
        aws.getStudent_id();
        aws.getPassword();
        crud.createNews();
    }
}