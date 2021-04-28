package com.example.groupproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";


    public DB(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users( id INTEGER PRIMARY KEY AUTOINCREMENT, firstName TEXT, lastName TEXT, college TEXT, email TEXT, password TEXT, classyear TEXT, mayor TEXT, phone TEXT, radiovalue TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int x) {
        db.execSQL("drop Table if exists users");
    }

    //insertion of data
    public Boolean insertData(String firstName, String lastName, String college, String email, String password, String classyear, String mayor, String phoneNum, String radiovalue) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("firstName", firstName);
        values.put("lastName", lastName);
        values.put("college", college);
        values.put("email", email);
        values.put("password", password);
        values.put("classyear", classyear);
        values.put("mayor", mayor);
        values.put("phone", phoneNum);
        values.put("radiovalue", radiovalue);
        long result = db.insert("users", null, values);

        return result != -1;
    }

    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email = ?", new String[]{email});
        return cursor.getCount() > 0;
    }

    public boolean checkuserpassword(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});
        return cursor.getCount() > 0;
    }


    public void dummyTutors() {
        ContentValues values = new ContentValues();
        // values.put(Constants., "John Doe");
    }
}
