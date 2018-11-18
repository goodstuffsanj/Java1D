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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setTitle("My Activity");

        AWSMobileClient.getInstance().initialize(this).execute();
        AWSCredentialsProvider credentialsProvider = AWSMobileClient.getInstance().getCredentialsProvider();
        AWSConfiguration configuration = AWSMobileClient.getInstance().getConfiguration();


        // Add code to instantiate a AmazonDynamoDBClient
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(credentialsProvider);

        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(configuration)
                .build();

//        ida = new NewsDO().getUserId();

        createNews();
//        queryNews();
        //readNews();
        //updateNews();
        //readNews();

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

    public void createNews() {
        final StudentTableDO newsItem = new StudentTableDO();

        newsItem.setUserId("bugaosuni");

        newsItem.setPassword("testest");
//        newsItem.setContent("10");

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newsItem);
                // Item saved
            }
        }).start();
    }
//
//    public void readNews() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                NewsDO newsItem = dynamoDBMapper.load(
//                        NewsDO.class,
//                        ida,
//                        "Article1");
//
//                // Item read
//                //Log.d("News Item:", newsItem.toString());
//            }
//        }).start();
//    }
//
//    public void updateNews() {
//        final NewsDO newsItem = new NewsDO();
//
//        newsItem.setUserId(ida);
//
//        newsItem.setArticleId("Article1");
//        newsItem.setContent("This is the updated content.");
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                dynamoDBMapper.save(newsItem);
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
//                NewsDO newsItem = new NewsDO();
//
//                newsItem.setUserId(ida);    //partition key
//
//                newsItem.setArticleId("Article1");  //range (sort) key
//
//                dynamoDBMapper.delete(newsItem);
//
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
//                NewsDO news = new NewsDO();
//                news.setAuthor("James");
//
//                Condition rangeKeyCondition = new Condition()
//                        .withComparisonOperator(ComparisonOperator.BEGINS_WITH)
//                        .withAttributeValueList(new AttributeValue().withS(""));
//
//                DynamoDBQueryExpression<NewsDO> queryExpression = new DynamoDBQueryExpression<>();
//                queryExpression.setHashKeyValues(news);
//                queryExpression.setIndexName("Authors");
//                queryExpression.setConsistentRead(false);
//
//                PaginatedList<NewsDO> result = dynamoDBMapper.query(NewsDO.class, queryExpression);
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
//                Log.d("Query result: ", stringBuilder.toString());
//
//                NewsDO news2 = new NewsDO();
//                news2.setUserId("123");
//                //news2.setArticleId("Article1");
//
//                DynamoDBQueryExpression<NewsDO> queryExpression2 = new DynamoDBQueryExpression<NewsDO>()
//                        .withHashKeyValues(news2)
//                        .withConsistentRead(false);
//
//                PaginatedList<NewsDO> result2 = dynamoDBMapper.query(NewsDO.class, queryExpression2);
//
//                Gson gson2 = new Gson();
//                StringBuilder stringBuilder2 = new StringBuilder();
//
//                // Loop through query results
//                for (int i = 0; i < result2.size(); i++) {
//                    String jsonFormOfItem = gson2.toJson(result2.get(i));
//                    stringBuilder2.append(jsonFormOfItem + "\n\n");
//                }
//
//                // Add your code here to deal with the data result
//                Log.d("Query result: ", stringBuilder2.toString());
//
//                if (result.isEmpty()) {
//                    // There were no items matching your query.
//                }
//            }
//        }).start();
//    }
}
