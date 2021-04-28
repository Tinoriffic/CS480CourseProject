package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

// Future class, not currently in use for calculating what tutee's need assistance with
public class TutorForm extends AppCompatActivity {

    CheckBox englishcheckbox;
    CheckBox mathcheckbox;
    CheckBox sciencecheckbox;
    CheckBox languagecheckbox;
    CheckBox businesscheckbox;
    Button submit;
    int checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_form);

        //initialization
        englishcheckbox = (CheckBox) findViewById(R.id.English);
        mathcheckbox = (CheckBox) findViewById(R.id.Mathematics);
        sciencecheckbox = (CheckBox) findViewById(R.id.Science);
        languagecheckbox = (CheckBox) findViewById(R.id.Language);
        businesscheckbox = (CheckBox) findViewById(R.id.Business);
        submit = (Button) findViewById(R.id.submit);

        englishcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checked = 1;
                }
                else {
                    checked = 0;
                }
            }
        });

        mathcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checked = 1;
                }
                else {
                    checked = 0;
                }
            }
        });

        sciencecheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    checked = 1;
                }
                else {
                    checked = 0;
                }

            }
        });

        sciencecheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checked = 1;
                }
                else {
                    checked = 0;
                }

            }
        });


    }

}