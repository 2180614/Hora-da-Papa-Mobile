package com.example.horadapapa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    static String name = "database";
    static int version =1;

    String createTableUser = "CREATE TABLE if not exists \"user\" (\n" +
            "\t\"id\"\tINTEGER,\n" +
            "\t\"firstname\"\tTEXT ,\n" +
            "\t\"lastname\"\tTEXT ,\n" +
            "\t\"email\"\tTEXT ,\n" +
            "\t\"phone\"\tINTEGER ,\n" +
            "\t\"password\"\tTEXT ,\n" +
            "\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n" +
            ")";


    public DBHelper(@Nullable Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableUser);
    }

    /*public void insertUser(ContentValues contentValues) {
        getWritableDatabase().insert("user", "", contentValues);
    }*/

    public boolean insertUser(String firstnameValue, String lastnameValue, String emailValue, String phoneValue, String passwordValue) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("firstname", firstnameValue);
        contentValues.put("lastname", lastnameValue);
        contentValues.put("email", emailValue);
        contentValues.put("phone", phoneValue);
        contentValues.put("password", passwordValue);
        long result = database.insert("user", null, contentValues);
        if(result == -1) return false;
        else
            return true;
    }

    public boolean isLoginValid(String email, String password) {
        String sql = "Select count(*) from user where email='" + email +"' and password='" + password + "'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        statement.close();

        if (l == 1){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkemail (String email) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from user where email = ?", new String[] {email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkemailpassword(String email, String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from user where email = ? and password = ?", new String[] {email, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
