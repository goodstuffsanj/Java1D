package com.example.personal.sutdbookingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginPageNew extends AppCompatActivity implements Student.OnFragmentInteractionListener, Staff.OnFragmentInteractionListener {
    private Button loginButton;
    private SignInButton googleSignInButton;
    private String username;
    private String password;
    private String name;
    final static int RC_SIGN_IN = 100;
    private static final String TAG = "LoginPageNew";
    private GoogleSignInClient mGoogleSignInClient;
    private final static String NAME = "USERNAME";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TabLayout tabLayout = findViewById(R.id.tabLayoutLogin);
        tabLayout.addTab(tabLayout.newTab().setText("Student"));
        tabLayout.addTab(tabLayout.newTab().setText("Staff"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapterLogin adapter = new PagerAdapterLogin(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //make database
        Database b = new Database(LoginPageNew.this);

        //set login button action
        loginButton = findViewById(R.id.imageButtonLogin);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //adapter.getItem(tabLayout.getSelectedTabPosition()).getClass())
                if (tabLayout.getSelectedTabPosition() == 0) {
                    Student student = (Student) adapter.getItem(tabLayout.getSelectedTabPosition());
                    username = student.getUsername();
                    password = student.getPassword();
                    if (username.isEmpty()) {
                        Toast.makeText(LoginPageNew.this, "Please type in your username", Toast.LENGTH_LONG).show();
                    }
                    else {
                        b.getDataHandler(new Database.DataHandler() {
                            Boolean usernameFail;
                            Boolean passwordFail;
                            @Override
                            <T> void postReceivedData(T result) {
                                StudentTableDO studentDatabase = (StudentTableDO) result;
                                //nothing from database i.e. wrong username
                                //nothing from database i.e. wrong username
                                if (studentDatabase == null) {
                                    usernameFail = true;
                                    passwordFail = false;

                                }
                                else if (password.equals(studentDatabase.getStudentPassword())) {
                                    name = studentDatabase.getStudentName();
                                    usernameFail = false;
                                    passwordFail = false;

                                    Intent intent = new Intent(LoginPageNew.this,HomePage.class);
                                    intent.putExtra(NAME, username);
                                    startActivity(intent);
                                }
                                else {
                                    usernameFail = false;
                                    passwordFail = true;
                                    }
                            }

                            @Override
                            public void showOnUI(Handler handler) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        makeToast(usernameFail, passwordFail);
                                    }
                                });
                            }
                        }).getData(StudentTableDO.class, username);
                    }




                }
                else if (tabLayout.getSelectedTabPosition() == 1){
                    Staff staff = (Staff) adapter.getItem(tabLayout.getSelectedTabPosition());
                    username = staff.getUsername();
                    password = staff.getPassword();
                    if (username.isEmpty()) {
                        Toast.makeText(LoginPageNew.this, "Please type in your username", Toast.LENGTH_LONG).show();
                    }
                    else {
                        //checking with database
                        b.getDataHandler(new Database.DataHandler() {
                            Boolean usernameFail;
                            Boolean passwordFail;
                            @Override
                            <T> void postReceivedData(T result) {
                                ProfTableDO profDatabase= (ProfTableDO) result;

                                //nothing from database i.e. wrong username
                                if (profDatabase == null) {
                                    usernameFail = true;
                                    passwordFail = false;
                                }
                                else if (password.equals(profDatabase.getProfPassword())) {
                                    name = profDatabase.getProfName();
                                    usernameFail = false;
                                    passwordFail = false;

                                    Intent intent = new Intent(LoginPageNew.this, ProfModeHomePage.class);
                                    intent.putExtra(NAME, username);
                                    startActivity(intent);
                                }
                                else {
                                    usernameFail = false;
                                    passwordFail = true;
                                }
                            }

                            @Override
                            public void showOnUI(Handler handler) {
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        makeToast(usernameFail, passwordFail);
                                    }
                                });

                            }
                        }).getData(ProfTableDO.class, username);
                    }


                }
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        googleSignInButton = findViewById(R.id.sign_in_button_google);
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

    }

    private void updateUI (GoogleSignInAccount user) {
        if (user != null) {
            Log.i ( TAG, "not null" );
        } else {
            Log.i ( TAG, "null" );
        }
    }

    private void handleSignInResult ( Task<GoogleSignInAccount> completedTask ) {
        try {
            GoogleSignInAccount account = completedTask.getResult ( ApiException.class );

            // Signed in successfully, show authenticated UI.
            updateUI ( account );
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w (TAG, "signInResult:failed code=" + e.getStatusCode ( ) );
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
            handleSignInResult (task);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {Log.i("account",account.toString());}
    }



    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void makeToast(Boolean usernameFalse, Boolean passwordFail) {
        if (usernameFalse) {
            Toast.makeText(LoginPageNew.this, "Incorrect username", Toast.LENGTH_LONG).show();
        }
        if (passwordFail) {
            Toast.makeText(LoginPageNew.this, "Incorrect password", Toast.LENGTH_LONG).show();

        }
    }
}
