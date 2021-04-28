package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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

    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder = null;
    private int NOTIFY_ID = 25;
    private String CHANNEL_ID = "11";


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

        // Building the Notification
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        createNotificationChannel();

        Intent notifyIntent = new Intent(this, UserProfile.class);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 9, notifyIntent,0 );

        builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ninja_2)
                .setContentTitle("UNIDOM")
                .setContentText("You have scheduled a new appointment!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // "Book now" buttons sends notification
        setAppointment1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //notify() in response to button click.
                notificationManager.notify(NOTIFY_ID, builder.build());
            }
        });

        setAppointment2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //notify() in response to button click.
                notificationManager.notify(NOTIFY_ID, builder.build());
            }
        });

        setAppointment3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //notify() in response to button click.
                notificationManager.notify(NOTIFY_ID, builder.build());
            }
        });

        // Opens the datePicker fragment
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

    // Returns the data that the user chose from datePicker
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        dateChosen = Calendar.getInstance();
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

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}