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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Registration2 extends Activity {

    //define variables

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private SQLHelper helper;

    EditText firstName;
    EditText lastName;
    EditText college;
    EditText email;
    EditText password;
    EditText rePassword;
    EditText classyear;
    EditText mayor;
    EditText phone;
    RadioGroup question;
    Boolean insert = false;
    RadioButton tutorbutton;
    RadioButton tuteebutton;
    RadioButton selectedRadioButton;
    Button register;
    Button signin;
    SQLiteDatabase db;
    String radiovalue;
    Integer selectedId;


  /* public void onCheckChanged(RadioGroup group, int checkedId ) {
        tutorbutton = (RadioButton)findViewById(checkedId);
        tuteebutton = (RadioButton)findViewById(checkedId);
        if (tutorbutton != null) {
            radiovalue = "Tutor";
        }
        if (tuteebutton != null) {
            radiovalue = "Tutee";
        }
    } */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_2);

        //initialization
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        college = (EditText) findViewById(R.id.college);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        rePassword = (EditText) findViewById(R.id.repassword);
        classyear = (EditText) findViewById(R.id.classyear);
        mayor = (EditText) findViewById(R.id.mayor);
        phone = (EditText) findViewById(R.id.phone);
        question = (RadioGroup) findViewById(R.id.RadioGroup1);
        tutorbutton = (RadioButton) findViewById(R.id.tutor);
        tuteebutton = (RadioButton) findViewById(R.id.tutee);
        register = (Button) findViewById(R.id.signup);
        signin = (Button) findViewById(R.id.signin);

        helper = new SQLHelper(Registration2.this);

        // Create database
        try {
            db = helper.getWritableDatabase();
        }
        catch (SQLException e) {
            Log.d("SQLiteLog", "Creating database failed");
        }


        //listeners for buttons

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = firstName.getText().toString();
                String lname = lastName.getText().toString();
                String coll = college.getText().toString();
                String user = email.getText().toString();
                String pass = password.getText().toString();
                String repass = rePassword.getText().toString();
                String year = classyear.getText().toString();
                String m = mayor.getText().toString();
                String phoneNum = phone.getText().toString();

                //get selected radio button from radioGroup
                selectedId = question.getCheckedRadioButtonId();
                if (selectedId != -1) {
                    selectedRadioButton = (RadioButton) findViewById(selectedId);
                    radiovalue = selectedRadioButton.getText().toString();
                }


                //if any of the editText boxes empty will produce a toast message
                if (fname.equals("") || lname.equals("") || coll.equals("") || user.equals("") || phoneNum.equals("") || pass.equals("") || repass.equals("") || year.equals("") || m.equals(""))
                    Toast.makeText(Registration2.this, "Please enter information in all fields", Toast.LENGTH_SHORT).show();

                else {
                    if (pass.equals(repass)) {
                        Log.d(LOG_TAG, "Password matches");
                        Boolean checkEmail = helper.checkEmail(user);
                        Log.d(LOG_TAG, "CheckEmail: " + checkEmail);

                        //if user does not exist
                        if (!checkEmail) {
                            Log.d(LOG_TAG, "Email doesn't exist, adding to DB");
                            //onCheckChanged(question, 1);
                            Log.d(LOG_TAG, "Radio value: " + radiovalue);

                            try {
                                helper.addPerson(new Person(user, pass, fname, lname, coll, phoneNum, year, m, 0));
                                insert = true;
                                Log.d(LOG_TAG, "Inserted into DB");
                            }
                            catch (Exception e) {
                                Log.d(LOG_TAG, "Insertion failed");
                            }

                            if (insert) {
                                Toast.makeText(Registration2.this, "Registered successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), SigninActivity2.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Registration2.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Registration2.this, "User already exists! Please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Registration2.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SigninActivity2.class);
                startActivity(intent);
            }
        });
    }
}
