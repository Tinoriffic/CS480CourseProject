package com.example.groupproject;

import android.app.Activity;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class UserDatabase extends Activity {

    private SQLHelper helper;
    private SQLiteDatabase db;
    private TextView text;
    private TextView counter;

    private static final String LOG = "SQLiteLog";

    // TODO: create a test DB layout file where we can test creating, adding to and querying the DB
    // TODO: Create a second sign in activity, copy the contents of the original but use the helper DB instead!
    // Set the content view to the test DB layout
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database_testing);

        text = (TextView) findViewById(R.id.Data);
        text.setMovementMethod(new ScrollingMovementMethod());
        helper = new SQLHelper(UserDatabase.this);

        // Create database
        try {
            db = helper.getWritableDatabase();
        }
        catch (SQLException e) {
            Log.d("SQLiteLog", "Creating database failed");
        }


        if (!helper.checkEmail("jdoe@bentley.edu")) {

            // Insert placeholder tutors
            helper.addPerson(new Person("jdoe@bentley.edu", "jdoe123", "John",
                    "Doe", "Bentley University", "617-281-6370",
                    "2022", "Computer Information Systems", 1));

            // Log.d(LOG, "New person added: " + )

            helper.addPerson(new Person("jjohnson@bentley.edu", "joe321",
                    "Joe", "Johnson", "Bentley University",
                    "781-207-3223", "2021", "Accounting", 1));

            helper.addPerson(new Person("jsmith@brandeis.edu", "jsmith345",
                    "John", "Smith", "Brandeis University",
                    "978-460-1291", "2022", "Computer Science", 1));
        }


        // Query database
        ArrayList<Person> personList = helper.getPersonList();
        int count = 0;

        for (Person person : personList) {
            count++;
            text.append(person.getFirstName() + " " + person.getLastName() + " " + person.getEmail() + " " + person.getPhone());
            text.append("\n");
        }

        try {
            counter.setText(count);
        }
        catch (Exception e) {
            Toast.makeText(this, "Counter is null", Toast.LENGTH_SHORT).show();
        }

    }

    // Close database
    @Override
    protected void onPause() {
        super.onPause();
        if(db != null)
            db.close();
    }
}
