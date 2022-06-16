package com.example.realtorandviewer;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "VIEWERS";
    private SQLiteDatabase database;

    // Table columns
    public static final String _ID = "_id";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String LAST_NAME = "LAST_NAME";
    public static final String GENDER = "GENDER";
    public static final String DATE_OF_BIRTH = "DATE_OF_BIRTH";

    // Database Information
    static final String DB_NAME = "R&V.DB";

    // database version
    static final int DB_VERSION = 2;

    // Creating table query
//    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "("
//            + EMAIL + " TEXT primary key, " + PASSWORD + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table VIEWERS(FIRST_NAME TEXT, LAST_NAME TEXT, GENDER TEXT, DATE_OF_BIRTH TEXT," +
                " EMAIL TEXT primary key, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertData(String FIRST_NAME, String LAST_NAME, String GENDER, String DATE_OF_BIRTH, String EMAIL, String PASSWORD) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.FIRST_NAME, FIRST_NAME);
        contentValue.put(DatabaseHelper.LAST_NAME, LAST_NAME);
        contentValue.put(DatabaseHelper.GENDER, GENDER);
        contentValue.put(DatabaseHelper.DATE_OF_BIRTH, DATE_OF_BIRTH);
        contentValue.put(DatabaseHelper.EMAIL, EMAIL);
        contentValue.put(DatabaseHelper.PASSWORD, PASSWORD);
        long result = database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //to check if the user is registered already
    public Boolean checkEmail(String email) {
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from viewers where email = ?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //for login
    public Boolean checkEmailPassword(String email, String password) {
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from viewers where email = ? and password = ?",
                new String[]{email, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public String getViewerName(String email, String password) {
        database = this.getReadableDatabase();
        String fullName;
        String firstName = null;
        String lastName = null;

        Cursor cursor = database.rawQuery("Select FIRST_NAME, LAST_NAME from viewers where email = ? and password = ?",
                new String[]{email, password});
        if (cursor.moveToFirst()) {
            do {
                firstName = cursor.getString(0);
                lastName = cursor.getString(1);
            } while (cursor.moveToNext());
        }
        fullName = firstName + " " + lastName;
        return fullName;
    }


}
