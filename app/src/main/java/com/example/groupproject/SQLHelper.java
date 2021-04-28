package com.example.groupproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// TODO: create an "Availability" object where each tutor can have a certain availability stored
//  as an object and and insert into DB, include methods to "get & set" availability

public class SQLHelper extends SQLiteOpenHelper {

    public static final String SQL_LOG = "SQLiteLog";
    public static final String DATABASE_NAME = "user_profiles.db";
    public static final int DATABASE_VERSION = 1;
    public static final String ID = "ID";
    public static final String TABLE_NAME = "USERS";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String FIRST_NAME = "FIRSTNAME";
    public static final String LAST_NAME = "LASTNAME";
    public static final String COLLEGE = "COLLEGE";
    public static final String CLASS_YEAR = "CLASSYEAR";
    public static final String PHONE = "PHONE";
    public static final String MAJOR = "MAJOR";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EMAIL + " TEXT, " + PASSWORD
            + " TEXT, " + FIRST_NAME + " TEXT, " + LAST_NAME + " TEXT, " + COLLEGE + " TEXT, " +  CLASS_YEAR + " TEXT, "
            + PHONE + " TEXT, " + MAJOR + " TEXT)";

    private ContentValues values;
    private ArrayList<Person> personList;
    private Cursor cursor;

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(SQL_LOG, "table created");
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion >= newVersion)
            return;

        Log.d(SQL_LOG, "New version ready: " + newVersion);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void addPerson(Person item) {
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(EMAIL, item.getEmail());
        values.put(PASSWORD, item.getPassword());
        values.put(FIRST_NAME, item.getFirstName());
        values.put(LAST_NAME, item.getLastName());
        values.put(COLLEGE, item.getCollege());
        values.put(CLASS_YEAR, item.getClassYear());
        values.put(PHONE, item.getPhone());
        values.put(MAJOR, item.getMajor());
        db.insert(TABLE_NAME, null, values);
        Log.d(SQL_LOG, "User " + item.getFirstName() + item.getLastName() + " added to DB");
        db.close();
    }

/*    // Update person's email
    public void updatePerson(Person item, Person newItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        values = new ContentValues();
        values.put(EMAIL, newItem.getEmail());
        db.update(TABLE_NAME, values, EMAIL + "=?", new String[] {item.getEmail()});
        Log.d(SQL_LOG, item.getEmail() + " updated");
        db.close();
    }

    public void deletePerson(Person item){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, EMAIL + "=?", new String[] {item.getEmail()});
        Log.d(SQL_LOG, item.getEmail() + " deleted");
        db.close();
    }*/

    // Query database and return ArrayList of everyone
    public ArrayList<Person> getPersonList() {

        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.query(TABLE_NAME,
                new String[]{EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, PHONE, COLLEGE, CLASS_YEAR, MAJOR},
                null, null, null, null, EMAIL);

        //write contents of Cursor to list
        personList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String email = cursor.getString(cursor.getColumnIndex(EMAIL));
            String password = cursor.getString(cursor.getColumnIndex(PASSWORD));
            String fname = cursor.getString(cursor.getColumnIndex(FIRST_NAME));
            String lname = cursor.getString(cursor.getColumnIndex(LAST_NAME));
            String college = cursor.getString(cursor.getColumnIndex(COLLEGE));
            String phone = cursor.getString(cursor.getColumnIndex(PHONE));
            String classYear = cursor.getString(cursor.getColumnIndex(CLASS_YEAR));
            String major = cursor.getString(cursor.getColumnIndex(MAJOR));
            personList.add(new Person(email, password, fname, lname, college, phone, classYear, major, 0));
        }

        db.close();
        return personList;


    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + EMAIL + " = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public boolean checkUserPassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " + EMAIL + " = ? and " + PASSWORD + " = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }

    /*public void createUserProfile() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where " )
    }*/


}
