package com.example.groupproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;


public class AdminActivity extends Activity {

    private Button btn_database;
    private Button btn_tutor_form;
    private Button btn_college_info;
    private Button btn_user_profile;
    private Button btn_book_activity;
    private Button btn_to_do_list;
    private Button btn_exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_ui);

        btn_database = (Button) findViewById(R.id.database);
        btn_tutor_form = (Button) findViewById(R.id.tutor_form);
        btn_college_info = (Button) findViewById(R.id.college_info);
        btn_user_profile = (Button) findViewById(R.id.user_profile);
        btn_book_activity = (Button) findViewById(R.id.book_activity);
        btn_to_do_list = (Button) findViewById(R.id.to_do_list);
        btn_exit = (Button) findViewById(R.id.exit);

        btn_database.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserDatabase.class);
                startActivity(intent);
            }
        });

        btn_tutor_form.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TutorForm.class);
                startActivity(intent);
            }
        });

        btn_college_info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CollegeInfo.class);
                startActivity(intent);
            }
        });

        btn_user_profile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                startActivity(intent);
            }
        });

        btn_book_activity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookActivity.class);
                startActivity(intent);
            }
        });

        btn_to_do_list.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ToDoList.class);
                startActivity(intent);
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(intent);
            }
        });

    }
}
