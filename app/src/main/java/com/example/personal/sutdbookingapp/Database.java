package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;


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

    public static DataHandlerAll getDataHandlerAll (DataHandlerAll dataHandlerAll) {return dataHandlerAll;}

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

        //for no sort key
        public <T extends Object> void getData(Class<T> dbClass, Object hashKey) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    T result = dynamoDBMapper.load(dbClass, hashKey);
                    postReceivedData(result);
                }
            }).start();
        }
    }

    //class that handles getting all table items
    public static abstract class DataHandlerAll {
        abstract <T extends Object> void postQueryAll(PaginatedList<T> result);

        //how to present results e.g. on TextView
        abstract void showOnUI(Handler handler);

        //gets all table items
        public <T extends Object> void getAll(Class<T> dbClass) {
            new Thread(new Runnable() {

                //to update UI
                Handler mHandler = new Handler(Looper.getMainLooper());

                @Override
                public void run() {
                    PaginatedScanList<T> result = dynamoDBMapper.scan(dbClass, new DynamoDBScanExpression());
                    postQueryAll(result);
                    showOnUI(mHandler);
                }
            }).start();
        }
    }


}



