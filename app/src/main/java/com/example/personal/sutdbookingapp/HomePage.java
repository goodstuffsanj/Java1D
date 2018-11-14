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
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.google.gson.Gson;

public class HomePage extends AppCompatActivity {
    private CardView book_facilities;
    private CardView book_prof;
    DynamoDBMapper dynamoDBMapper;
    private String ida;

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

        ida = "kk";

        createNews();
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
        final NewsDO newsItem = new NewsDO();

        newsItem.setUserId(ida);

        newsItem.setArticleId("Article1");
        newsItem.setContent("This is the article content");

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newsItem);
                // Item saved
            }
        }).start();
    }

    public void readNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                NewsDO newsItem = dynamoDBMapper.load(
                        NewsDO.class,
                        ida,
                        "Article1");

                // Item read
                //Log.d("News Item:", newsItem.toString());
            }
        }).start();
    }

    public void updateNews() {
        final NewsDO newsItem = new NewsDO();

        newsItem.setUserId(ida);

        newsItem.setArticleId("Article1");
        newsItem.setContent("This is the updated content.");

        new Thread(new Runnable() {
            @Override
            public void run() {

                dynamoDBMapper.save(newsItem);

                // Item updated
            }
        }).start();
    }

    public void deleteNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                NewsDO newsItem = new NewsDO();

                newsItem.setUserId(ida);    //partition key

                newsItem.setArticleId("Article1");  //range (sort) key

                dynamoDBMapper.delete(newsItem);

                // Item deleted
            }
        }).start();
    }

    /*public void queryNews() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                com.amazonaws.models.nosql.NewsDO news = new com.amazonaws.models.nosql.NewsDO();
                news.setUserId(id);
                news.setArticleId("Article1");

                Condition rangeKeyCondition = new Condition()
                        .withComparisonOperator(ComparisonOperator.BEGINS_WITH)
                        .withAttributeValueList(new AttributeValue().withS("Trial"));

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(note)
                        .withRangeKeyCondition("articleId", rangeKeyCondition)
                        .withConsistentRead(false);

                PaginatedList<com.amazonaws.models.nosql.NewsDO> result = dynamoDBMapper.query(com.amazonaws.models.nosql.NewsDO.class, queryExpression);

                Gson gson = new Gson();
                StringBuilder stringBuilder = new StringBuilder();

                // Loop through query results
                for (int i = 0; i < result.size(); i++) {
                    String jsonFormOfItem = gson.toJson(result.get(i));
                    stringBuilder.append(jsonFormOfItem + "\n\n");
                }

                // Add your code here to deal with the data result
                Log.d("Query result: ", stringBuilder.toString());

                if (result.isEmpty()) {
                    // There were no items matching your query.
                }
            }
        }).start();
    }*/
}
