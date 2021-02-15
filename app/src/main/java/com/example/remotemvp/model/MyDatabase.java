package com.example.remotemvp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabase extends SQLiteOpenHelper {

    private final static String DB_NAME = "database3";
    private final static int DB_VERSION = 1;

    public static final String tableEmployees = "employees";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHONE = "phone";
    public static final String ADDRESS = "address";
    public static final String PASSWORD = "password";
    public static final String TIME = "time";


    public static final String tablelastConnection = "lastconnections";
    public static final String tableAccessList = "accesslist";
    public static final String tableNotAccessList = "notaccesslist";
    public static final String IDIMEIconnected = "idIMEIconnected";
    public static final String IMEII = "imeii";

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {

        Log.d("aaa","____   oncreat database   ____");

        mydb.execSQL("CREATE TABLE IF NOT EXISTS "+ tableEmployees + " (" +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT, " +
                PHONE + " TEXT, " +
                ADDRESS + " TEXT, " +
                PASSWORD + " TEXT, " +
                TIME + " TEXT);");

        mydb.execSQL("CREATE TABLE IF NOT EXISTS "+ tablelastConnection + " (" +
                IDIMEIconnected + " INTEGER PRIMARY KEY, " +
                IMEII + " TEXT);");

        mydb.execSQL("CREATE TABLE IF NOT EXISTS "+ tableAccessList + " (" +
                IDIMEIconnected + " INTEGER PRIMARY KEY, " +
                IMEII + " TEXT);");

        mydb.execSQL("CREATE TABLE IF NOT EXISTS "+ tableNotAccessList + " (" +
                IDIMEIconnected + " INTEGER PRIMARY KEY, " +
                IMEII + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase mydb,
                          int oldVersion, int newVersion) {

    }
}
