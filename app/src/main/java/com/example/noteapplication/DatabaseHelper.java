package com.example.noteapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //TABLE NAME
    public static final String TABLE_NAME = "NotePad";

    //COLUMN NAME
    public static final String _ID = "_id";
    public static final String _TITLE = "_title";
    public static final String _DESC = "_desc";

    //DATABASE INFORMATION
    static final String DB_NAME = "NoteApplication.DB";

    //DATABASE VERSION
    static final int DB_VERSION = 1;

    //CREATING TABLE QUERY
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + _TITLE + " TEXT NOT NULL, "
            + _DESC + " TEXT);";

    //CONSTRUCTOR
    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate(db);
    }
}

