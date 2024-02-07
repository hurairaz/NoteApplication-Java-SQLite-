package com.example.noteapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    //CONSTUCTOR
    public  DBManager(Context c){
        context = c;
    }

    public DBManager open() throws SecurityException{
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert(String title, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper._TITLE,title);
        contentValues.put(DatabaseHelper._DESC,desc);
        database.insert(DatabaseHelper.TABLE_NAME,null,contentValues);
    }

    public Cursor fetch(){
        String[] columns = new String[] {DatabaseHelper._ID,DatabaseHelper._TITLE,DatabaseHelper._DESC};
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,columns,null,
                null, null, null, null);

        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long id, String title, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper._TITLE, title);
        contentValues.put(DatabaseHelper._DESC, desc);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + id, null);
        return i;
    }

    public void delete(long id){
        database.delete(DatabaseHelper.TABLE_NAME,DatabaseHelper._ID + " = " + id, null);
    }


}
