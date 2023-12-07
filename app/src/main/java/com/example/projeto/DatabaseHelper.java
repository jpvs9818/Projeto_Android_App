package com.example.projeto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
// Add other user-related columns as needed

    private static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_USERNAME + " TEXT" +
                    // Add other user-related columns as needed
                    ");";
//

    public static final String TABLE_YEARS = "years";
    public static final String COLUMN_YEAR_ID = "_id";
    public static final String COLUMN_USER_ID_FK = "user_id";
    public static final String COLUMN_YEAR = "year";
// Add other year-related columns as needed

    private static final String CREATE_TABLE_YEARS =
            "CREATE TABLE " + TABLE_YEARS + " (" +
                    COLUMN_YEAR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_USER_ID_FK + " INTEGER," +
                    COLUMN_YEAR + " INTEGER" +
                    // Add other year-related columns as needed
                    ");";
//

    public static final String TABLE_MONTHS = "months";
    public static final String COLUMN_MONTH_ID = "_id";
    public static final String COLUMN_YEAR_ID_FK = "year_id";
    public static final String COLUMN_MONTH = "month";
// Add other month-related columns as needed

    private static final String CREATE_TABLE_MONTHS =
            "CREATE TABLE " + TABLE_MONTHS + " (" +
                    COLUMN_MONTH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_YEAR_ID_FK + " INTEGER," +
                    COLUMN_MONTH + " INTEGER" +
                    // Add other month-related columns as needed
                    ");";
//
public static final String TABLE_DAYS = "days";
    public static final String COLUMN_DAY_ID = "_id";
    public static final String COLUMN_MONTH_ID_FK = "month_id";
    public static final String COLUMN_DAY = "day";
// Add other day-related columns as needed

    private static final String CREATE_TABLE_DAYS =
            "CREATE TABLE " + TABLE_DAYS + " (" +
                    COLUMN_DAY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_MONTH_ID_FK + " INTEGER," +
                    COLUMN_DAY + " INTEGER" +
                    // Add other day-related columns as needed
                    ");";



    public DatabaseHelper( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
