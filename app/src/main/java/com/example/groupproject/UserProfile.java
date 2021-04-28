package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

// TODO: Add a picture of the user's school in their user profile (beneath basic info)
// TODO: Make the UI Pretty
// TODO: Test the application

public class UserProfile extends AppCompatActivity {

        private TextView tv_basic_info;
        private TextView tv_name;
        private TextView tv_admin_login;
        private Button btn_call;
        private Button btn_msg;
        private Button btn_list;
        private Button btn_book;
        private Button btn_admin;
        private String phone_num;
        private SQLHelper helper;
        private SQLiteDatabase db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_profile);

            tv_basic_info = (TextView) findViewById(R.id.basic_info);
            tv_name = (TextView) findViewById(R.id.userprofile);
            tv_admin_login = (TextView) findViewById(R.id.tv_adminLogin);
            btn_call = (Button) findViewById(R.id.call);
            btn_msg = (Button) findViewById(R.id.message);
            btn_list = (Button) findViewById(R.id.list);
            btn_book = (Button) findViewById(R.id.book);
            btn_admin = (Button) findViewById(R.id.admin);

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

            // Iterate through person list to find the current user's information
            for (Person person : personList) {
                email = person.getEmail();
                if (SigninActivity2.login_email.equals(email)) {
                    String basic_information = person.getMajor() + "  " + person.getClassYear() + "  " + person.getCollege();
                    phone_num = person.getPhone();
                    tv_basic_info.setText(basic_information);

                    String name = person.getFirstName() + " " + person.getLastName();
                    tv_name.setText(name);
                }
            }
    }

    // Button interface methods
    public void bookAppointment(View view) {
        Intent intent = new Intent(getApplicationContext(), BookActivity.class);
        startActivity(intent);
    }

    public void toDoList(View view) {
        Intent intent = new Intent(getApplicationContext(), ToDoList.class);
        startActivity(intent);
    }

    public void adminInterface(View view) {
        if (tv_admin_login.getText().toString().equals("Harry Bentley")) {
            Toast.makeText(this, "Password Accepted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
            startActivity(intent);
        }
        else
            Toast.makeText(this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
    }

    public void dialer(View view) {
            Uri uri = Uri.parse("tel:17818912000");
            Intent intent = new Intent(Intent.ACTION_DIAL, uri);
            startActivity(intent);
    }

    public void message(View view){
        Uri uri = Uri.parse("smsto:");
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        //intent.putExtra("sms_body", smsText);
        startActivity(intent);
    }

    public void college_info(View view) {
        Intent intent = new Intent(getApplicationContext(), CollegeInfo.class);
        startActivity(intent);
    }

    public void logout(View view) {
        Intent intent = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(intent);
    }
}
