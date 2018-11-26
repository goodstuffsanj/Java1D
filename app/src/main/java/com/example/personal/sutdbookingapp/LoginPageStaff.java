package com.example.personal.sutdbookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LoginPageStaff extends AppCompatActivity {
    Button imageButtonLoginStaff;
    Button buttonStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_staff);

        imageButtonLoginStaff = findViewById(R.id.imageButtonLoginStaff);
        buttonStudent = findViewById(R.id.student);

        imageButtonLoginStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPageStaff.this,ProfModeHomePage.class);
                startActivity(intent);
            }
        });

        buttonStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPageStaff.this,LoginPage.class);
                startActivity(intent);
            }
        });
    }
}