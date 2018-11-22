package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import de.hdodenhof.circleimageview.CircleImageView;

public class Facility extends AppCompatActivity {
    public String facilityId;
    public final static String FACIL_ID = "FACIL_ID";
    public final static String IMAGE = "IMAGE";
    private final static String DESCRIPTION = "DESCRIPTION";
    Button buttonBookFacility;
    TextView textViewFacilityName;
    CircleImageView imageFacility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility);
        Intent intent = getIntent();
        String name = intent.getStringExtra(FACIL_ID);
        String image = intent.getStringExtra(IMAGE);
        String desc = intent.getStringExtra(DESCRIPTION);
        buttonBookFacility = (Button) findViewById(R.id.buttonBookFacility);
        textViewFacilityName = findViewById(R.id.textViewFacilityName);
        imageFacility = findViewById(R.id.imageFacility);
        Glide.with(this).load(image).into(imageFacility);
        textViewFacilityName.setText(name);
        buttonBookFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StyleableToast.makeText(Facility.this,"hello world",R.style.mytoast).show();
            }
        });
        setTitle("Book Facility");
    }
}
