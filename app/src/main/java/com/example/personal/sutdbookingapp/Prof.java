package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import de.hdodenhof.circleimageview.CircleImageView;

public class Prof extends AppCompatActivity {
    public final static String PROF_ID = "PROF_ID";
    public final static String IMAGE = "IMAGE";
    private final static String DESCRIPTION = "DESCRIPTION";
    CircleImageView imageProf;
    TextView textDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof);
        Intent intent = getIntent();
        String name = intent.getStringExtra(PROF_ID);
        String image = intent.getStringExtra(IMAGE);
        String desc = intent.getStringExtra(DESCRIPTION);
        setTitle(name);

        imageProf = findViewById(R.id.imageProf);
        textDesc = (TextView) findViewById(R.id.textDesc);


        Glide.with(this).load(image).into(imageProf);
        textDesc.setText(desc);

    }
}
