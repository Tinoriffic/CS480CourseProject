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
    public Boolean insertData(String firstName, String lastName, String college, String email, String password, String classyear, String mayor, String phoneNum, String radiovalue){
     SQLiteDatabase db = this.getWritableDatabase();
     ContentValues contentValues = new ContentValues();
     contentValues.put("firstName", firstName);
     contentValues.put("lastName", lastName);
     contentValues.put("college", college);
     contentValues.put("email", email);
     contentValues.put("password", password);
     contentValues.put("classyear", classyear);
     contentValues.put("mayor", mayor);
     contentValues.put("phone", phoneNum);
     contentValues.put("radiovalue", radiovalue);
     long result = db.insert("users", null, contentValues);
     if(result == -1) return false;
     else
       return true;
    }

    public Boolean checkEmail(String email){
     SQLiteDatabase db = this.getWritableDatabase();
     Cursor cursor = db.rawQuery("Select * from users where email = ?", new String[] {email});
     if(cursor.getCount()>0)
      return true;
     else
       return false;
    }

    public boolean checkuserpassword(String email, String password){
     SQLiteDatabase db = this.getWritableDatabase();
     Cursor cursor = db.rawQuery("Select * from users where email = ? and passwod = ?", new String [] {email,password});
     if(cursor.getCount()>0)
      return true;
     else
      return false;
    }
}
