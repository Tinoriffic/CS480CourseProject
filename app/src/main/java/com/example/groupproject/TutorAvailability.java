package com.example.groupproject;

import java.util.Calendar;

public class TutorAvailability {

    private Calendar calendar;
    private int year;
    private int month;
    private int dayOfMonth;

    public TutorAvailability(int year, int month, int dayOfMonth) {
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    public Calendar calendar() {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return calendar;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }


    // Use the helper class to identify tutors
    // Create "availability" for each of the tutors
    // Refer to this class's methods to get their availability and compare


}
