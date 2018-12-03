package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.util.Log;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class Database {
    static DynamoDBMapper dynamoDBMapper;
    Object result = null;

    public Database(Context context) {
        AWSMobileClient.getInstance().initialize(context).execute();
        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
        AWSConfiguration configuration = AWSMobileClient.getInstance().getConfiguration();
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(configuration)
                .build();
    }

    public <T extends Object> void create(T o) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                dynamoDBMapper.save(o);
            }
        }).start();
    }

    public <T extends Object> void update(T o) {
        Log.i("DB", "in update");
        new Thread(new Runnable() {

            @Override
            public void run() {
                Log.i("DB", "update is calling");
                dynamoDBMapper.save(o);
            }
        }).start();
    }

    public <T extends Object> void delete(T o){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("****************************************************");
                Log.i("DB", "delete is calling");
                dynamoDBMapper.delete(o);
            }
        }).start();
    }

    public static QueryHandler getQueryHandler(QueryHandler queryHandler) {
        return queryHandler;
    }

    public static DataHandler getDataHandler(DataHandler dataHandler) {
        return dataHandler;
    }

    public static abstract class QueryHandler {
        abstract <T extends Object> void postQuery(PaginatedList<T> result);

        public <T extends Object> void getQuery(Class<T> dbClass, String index,T o) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.i("DB", "query is calling");
                    DynamoDBQueryExpression<T> queryExpression = new DynamoDBQueryExpression<>();
                    queryExpression.setHashKeyValues(o);
                    queryExpression.setIndexName(index);
                    queryExpression.setConsistentRead(false);
                    PaginatedList<T> result = dynamoDBMapper.query(dbClass, queryExpression);
                    postQuery(result);
                }
            }).start();
        }
    }

    public static abstract class DataHandler {
        abstract <T extends Object> void postReceivedData(T result);

        public <T extends Object> void getData(Class<T> dbClass, Object hashKey, Object rangeKey) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    T result = dynamoDBMapper.load(dbClass, hashKey, rangeKey);
                    postReceivedData(result);
                }
            }).start();
        }
    }

}
