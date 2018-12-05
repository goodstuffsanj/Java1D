package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.util.Log;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;

import com.amazonaws.mobile.auth.core.signin.ui.buttons.SignInButton;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMappingException;
//import com.amazonaws.mobile.auth.core.signin.ui.buttons.SignInButton;
import com.applandeo.materialcalendarview.EventDay;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends AppCompatActivity {
    Button imageButtonLoginStudent;
    Button buttonStaff;
    EditText username;
    EditText password;
    private ScrollView scrollLogin;
    final static int RC_SIGN_IN = 100;
    private static final String TAG = "Login Page";
    private GoogleSignInClient mGoogleSignInClient;

    private void updateUI ( GoogleSignInAccount user ) {
        if (user != null) {
            Log.i ( TAG, "not null" );
        } else {
            Log.i ( TAG, "null" );
        }
    }

    private void handleSignInResult ( Task <GoogleSignInAccount> completedTask ) {
        try {
            GoogleSignInAccount account = completedTask.getResult ( ApiException.class );

            // Signed in successfully, show authenticated UI.
            updateUI ( account );
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w ( TAG, "signInResult:failed code=" + e.getStatusCode ( ) );
            updateUI ( null );
        }
    }
    @Override
    public void onActivityResult ( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult ( requestCode, resultCode, data );

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent ( data );
            handleSignInResult ( task );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context context = this;

        SharedPreferences sharedPref = context.getSharedPreferences("name", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_login_page);
        imageButtonLoginStudent = findViewById(R.id.imageButtonLoginStudent);
        buttonStaff = findViewById(R.id.buttonStaff);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        scrollLogin = findViewById(R.id.scrollLogin);
        scrollLogin.smoothScrollTo(0,0);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
        imageButtonLoginStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database test = new Database(LoginPage.this);
//
//                test.getDataHandler(new Database.DataHandler() {
//                    @Override
//                    <T> void postReceivedData(T result) {
//                        StudentTableDO a = (StudentTableDO) result;
//                        Log.i("DB_data from Loginpage", (a.getStudentPassword()));
//                    }}).getData(StudentTableDO.class, usernameInput);


                try {
                    Log.i("DB_try", "in try");
                    test.getDataHandler(new Database.DataHandler() {
                        @Override
                        <T> void postReceivedData(T result) {
                            StudentTableDO a = (StudentTableDO) result;
                            Log.i("DB_data from Loginpage", (a.getStudentPassword()));
                        }
                    }).getData(StudentTableDO.class, username);
                    throw new Exception();
                }
                catch(Exception ex){
                    Log.i("DB_extype:", ex.getClass().getCanonicalName());
                    Log.i("DB_ex from loginpage", "invalid username");
                }
                catch(Error error) {
                    Log.i("DB_error from loginpage", "invalid username");
                }

                Intent intent = new Intent(LoginPage.this,HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        buttonStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this,LoginPageStaff.class);
                startActivity(intent);
            }
        });



    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {Log.i("account",account.toString());}
    }
}
