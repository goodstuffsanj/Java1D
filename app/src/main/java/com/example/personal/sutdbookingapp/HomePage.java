package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.google.gson.Gson;

public class HomePage extends AppCompatActivity {
    private CardView book_facilities;
    private CardView book_prof;
    DynamoDBMapper dynamoDBMapper;
    private String tag = "DB";
    private String ida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        AWSMobileClient.getInstance().initialize(this).execute();
        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
        AWSConfiguration configuration = AWSMobileClient.getInstance().getConfiguration();


        // Add code to instantiate a AmazonDynamoDBClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);

        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(configuration)
                .build();

        ida = new StudentTableDO().getUserId();

//        createNews();
//        queryNews();
//        readNews();
//        updateNews();
//        deleteNews();

        book_facilities = (CardView) findViewById(R.id.book_facilities);
        book_prof = (CardView) findViewById(R.id.book_prof);


        book_prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, BookProf.class);
                startActivity(intent);
            }
        });

        book_facilities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, BookFacilities.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }



//    public void createNews() {
//        final StudentTableDO newsItem = new StudentTableDO();
//
//        newsItem.setUserId("2333");
//        newsItem.setName("six god");
//        newsItem.setPassword("123");
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                dynamoDBMapper.save(newsItem);
//                Log.i("DB", "new Item is created");
//                // Item saved
//            }
//        }).start();
//    }
//
//    public void readNews() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    StudentTableDO newsItem1 = dynamoDBMapper.load(
//                            StudentTableDO.class,
//                            "2333",
//                            "123");
//
//                    StudentTableDO newsItem2 = dynamoDBMapper.load(
//                            StudentTableDO.class,
//                            "where is the name",
//                            "testtest");
//                    Log.i(tag, "read item1: " + String.valueOf(newsItem1.toString()));
//                    Log.i(tag, "read item2: " + newsItem2.toString());
//                } catch (NullPointerException nullpointer_ex) {
//                    Log.i(tag, "cannot can the item" );
//                }
//            }
//
//        }).start();
//    }
//
//    private String userId = "bugaosuni";
//    private String password = "update and delete";
//
//    public void updateNews() {
//        final StudentTableDO newsItem = new StudentTableDO();
//
//        newsItem.setUserId(userId);
//        newsItem.setPassword(password);
//        newsItem.setName("call me minion");
//        deleteNews();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                dynamoDBMapper.save(newsItem);
//                Log.i(tag, "item is updated yaho~~");
//
//                // Item updated
//            }
//        }).start();
//    }
//
//    public void deleteNews() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                StudentTableDO newsItem = new StudentTableDO();
//
//                newsItem.setUserId(userId);    //partition key
//
//                newsItem.setPassword("testest");  //range (sort) key
//
//                dynamoDBMapper.delete(newsItem);
//                Log.i(tag, "deleted");
//                // Item deleted
//            }
//        }).start();
//    }
//
//    public void queryNews() {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.i(tag, "start to query******");
//                StudentTableDO news = new StudentTableDO();
//                news.setName("call me minion");
//
//                Condition rangeKeyCondition = new Condition()
//                        .withComparisonOperator(ComparisonOperator.BEGINS_WITH)
//                        .withAttributeValueList(new AttributeValue().withS(""));
//
////                Condition rangeKeyCondition = new Condition()
////                        .withComparisonOperator(ComparisonOperator.BEGINS_WITH)
////                        .withAttributeValueList(new AttributeValue().withS("Trial"));
////
////                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
////                        .withHashKeyValues(news)
////                        .withRangeKeyCondition("password", rangeKeyCondition)
////                        .withConsistentRead(false);
//
//                DynamoDBQueryExpression<StudentTableDO> queryExpression = new DynamoDBQueryExpression<>();
//                queryExpression.setHashKeyValues(news);
//                queryExpression.setIndexName("Names");
//                queryExpression.setConsistentRead(false);
//
//                PaginatedList<StudentTableDO> result = dynamoDBMapper.query(StudentTableDO.class, queryExpression);
//
//                Gson gson = new Gson();
//                StringBuilder stringBuilder = new StringBuilder();
//
//                // Loop through query results
//                for (int i = 0; i < result.size(); i++) {
//                    String jsonFormOfItem = gson.toJson(result.get(i));
//                    stringBuilder.append(jsonFormOfItem + "\n\n");
//                }
//
//                // Add your code here to deal with the data result
//                Log.i(tag, stringBuilder.toString());
//
////                NewsDO news2 = new NewsDO();
////                news2.setUserId("123");
////                //news2.setArticleId("Article1");
////
////                DynamoDBQueryExpression<NewsDO> queryExpression2 = new DynamoDBQueryExpression<NewsDO>()
////                        .withHashKeyValues(news2)
////                        .withConsistentRead(false);
////
////                PaginatedList<NewsDO> result2 = dynamoDBMapper.query(NewsDO.class, queryExpression2);
////
////                Gson gson2 = new Gson();
////                StringBuilder stringBuilder2 = new StringBuilder();
////
////                // Loop through query results
////                for (int i = 0; i < result2.size(); i++) {
////                    String jsonFormOfItem = gson2.toJson(result2.get(i));
////                    stringBuilder2.append(jsonFormOfItem + "\n\n");
////                }
////
////                // Add your code here to deal with the data result
////                Log.d("Query result: ", stringBuilder2.toString());
//
//                if (result.isEmpty()) {
//                    // There were no items matching your query.
//                }
//            }
//        }).start();
//    }
}
