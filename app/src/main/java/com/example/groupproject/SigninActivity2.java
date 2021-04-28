package com.example.groupproject;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SigninActivity2 extends Activity {

    public static String login_email;
    private SQLHelper helper;

    EditText email1;
    EditText password1;
    Button buttonSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_2);

        email1 = (EditText) findViewById(R.id.email1);
        password1 = (EditText) findViewById(R.id.password1);
        buttonSignin = (Button) findViewById(R.id.signin1);

        helper = new SQLHelper(SigninActivity2.this);


        //button listener
        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email1.getText().toString();
                String pass = password1.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(SigninActivity2.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

                else {
                    Boolean checkUserPassword = helper.checkUserPassword(user, pass);
                    if (checkUserPassword) {
                        Toast.makeText(SigninActivity2.this, "Sign in Successful", Toast.LENGTH_SHORT).show();
                        login_email = user;
                        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                        startActivity(intent);
                    }

                    else {
                        Toast.makeText(SigninActivity2.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }
}
