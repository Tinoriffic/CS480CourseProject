package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.groupproject.DatePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class BookActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    // private DatePicker datePicker;
    private TextView tviewDateChosen;
    private Button openCalendar;
    private TextView tviewIsAvailable;
    private Calendar dateChosen;

    private Button setAppointment1;
    private Button setAppointment2;
    private Button setAppointment3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutors);

        tviewDateChosen = (TextView) findViewById(R.id.dateChosen);
        tviewIsAvailable = (TextView) findViewById(R.id.isAvailable);
        // datePicker = (DatePicker) findViewById(R.id.calendar_picker);
        openCalendar = (Button) findViewById(R.id.openCalendar);

        setAppointment1 = (Button) findViewById(R.id.setAppointment1);
        setAppointment2 = (Button) findViewById(R.id.setAppointment2);
        setAppointment3 = (Button) findViewById(R.id.setAppointment3);

        openCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

                /* long currentDate = Calendar.getInstance().getTimeInMillis();
                int selectedDay = datePicker.getDayOfMonth();
                int selectedMonth = datePicker.getMonth();
                int selectedYear = datePicker.getYear();

                GregorianCalendar selectedDate = new GregorianCalendar(selectedYear, selectedMonth, selectedDay);
                long selectedDateMS = selectedDate.getTimeInMillis();

                if (selectedDateMS >= currentDate)
                    isAvailable.setText("Selected Date: " + (datePicker.getMonth() + 1) + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear() + " is available!");
                else
                    isAvailable.setText("Selected Date: " + (datePicker.getMonth() + 1) + "/" + datePicker.getDayOfMonth() + "/" + datePicker.getYear() + " is not available!");*/
            }
        });


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar currentDate = Calendar.getInstance();
        dateChosen.set(Calendar.YEAR, year);
        dateChosen.set(Calendar.MONTH, month);
        dateChosen.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(currentDate.getTime());

        tviewDateChosen.setText(currentDateString);

        if (dateChosen.after(currentDate)) {
            tviewIsAvailable.setVisibility(View.INVISIBLE);
            setAppointment1.setVisibility(View.VISIBLE);
            setAppointment2.setVisibility(View.VISIBLE);
            setAppointment3.setVisibility(View.VISIBLE);
        } else {
            tviewIsAvailable.setText(R.string.unavailable);
            tviewIsAvailable.setVisibility(View.VISIBLE);
            setAppointment1.setVisibility(View.INVISIBLE);
            setAppointment2.setVisibility(View.INVISIBLE);
            setAppointment3.setVisibility(View.INVISIBLE);
        }
    }

    public void testDatabase(View view) {
        Intent intent = new Intent(getApplicationContext(), UserDatabase.class);
        startActivity(intent);
    }
}