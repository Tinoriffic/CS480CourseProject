package com.example.groupproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class WelcomeActivity extends AppCompatActivity {

    private Button Register;
    private Button Register2;
    private Button signin;
    private Button signIn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button Register = (Button) findViewById(R.id.Registerbuttonlink);
        Button Register2 = (Button) findViewById(R.id.Register_2);
        Button signin = (Button) findViewById(R.id.Signinpagebuttonlink);
        Button signIn2 = (Button) findViewById(R.id.SignIn_2);

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

        Register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, Registration2.class));
            }
        });

        signIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, SigninActivity2.class));
            }
        });
    }

}