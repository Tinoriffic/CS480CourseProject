package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {

    EditText email1;
    EditText password1;
    Button buttonSignin;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        email1 = (EditText) findViewById(R.id.email1);
        password1 = (EditText) findViewById(R.id.password1);
        buttonSignin = (Button) findViewById(R.id.signin1);
        db = new DB(this);

        //button listener
        buttonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = email1.getText().toString();
                String pass = password1.getText().toString();

            if(user.equals("") || pass.equals(""))
                Toast.makeText(SigninActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

            else{
                Boolean checkuserpass = db.checkuserpassword(user, pass);
                if(checkuserpass==true){
                    Toast.makeText(SigninActivity.this, "Sign in Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SigninActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }


            }
        });

    }
}