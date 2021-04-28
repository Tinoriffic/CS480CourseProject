package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class UserProfile extends AppCompatActivity {

        private TextView tv_email;
        private TextView tv_phone;
        private TextView tv_college;
        private TextView tv_name;
        private SQLHelper helper;
        private SQLiteDatabase db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_profile);

            tv_email = (TextView) findViewById(R.id.email_address);
            tv_phone = (TextView) findViewById(R.id.phone_num);
            tv_college = (TextView) findViewById(R.id.college_prof);
            tv_name = (TextView) findViewById(R.id.full_name);

            helper = new SQLHelper(UserProfile.this);

            // Create database
            try {
                db = helper.getWritableDatabase();
            }
            catch (SQLException e) {
                Log.d("SQLiteLog", "Creating database failed");
            }

            ArrayList<Person> personList = helper.getPersonList();
            String email = "";

            for (Person person : personList) {
                email = person.getEmail();
                if (SigninActivity2.login_email.equals(email)) {
                    tv_email.setText(email);
                    tv_phone.setText(person.getPhone());
                    tv_college.setText(person.getCollege());
                    String name = person.getFirstName() + " " + person.getLastName();
                    tv_name.setText(name);
                }

            /*Cursor cursor = db.UserProfile();
            if (cursor.moveToFirst()) {
                do {
                    String string_email = cursor.getString(cursor.getColumnIndex("email"));
                    email.setText(string_email);

                    String string_phone = cursor.getString(cursor.getColumnIndex("phone"));
                    phone.setText(string_phone);

                    String string_college = cursor.getString(cursor.getColumnIndex("college"));
                    college.setText(string_college);

                    String string_first = cursor.getString(cursor.getColumnIndex("firstName"));
                    String string_last = cursor.getString(cursor.getColumnIndex("lastName"));

                    name.setText(string_first + " " + string_last);

                } while (cursor.moveToNext());

            }
            cursor.close();*/
            }
    }
}
