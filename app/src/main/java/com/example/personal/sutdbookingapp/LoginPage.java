package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

//import com.amazonaws.mobile.auth.core.signin.ui.buttons.SignInButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginPage extends AppCompatActivity {
    Button imageButtonLoginStudent;
    Button buttonStaff;
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
