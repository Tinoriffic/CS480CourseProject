package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class WelcomeActivity extends AppCompatActivity {

    private Button Register;
    private Button signin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button Register = (Button) findViewById(R.id.Registerbuttonlink);
        Button signin = (Button) findViewById(R.id.Signinpagebuttonlink);

        Register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            }
        });

        signin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(WelcomeActivity.this, SigninActivity.class));
            }
        });
    }

}