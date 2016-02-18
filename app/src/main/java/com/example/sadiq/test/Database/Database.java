package com.example.sadiq.test.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;

/**
 * Created by Sadiq on 2/10/2016.
 */
//singleton class for the database
public class Database {

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase SQLDatabase;
    private static Database databaseInstance;


    public final static String EMP_TABLE="Exersices"; // name of table

    public final static String EMP_ID="_id"; // id value for employee
    public final static String EMP_NAME="name";  // name of employee
    /**
     *
     * @param context
     */
    //make it a singleton

    public static synchronized Database getDatabaseInstance(Context context){
        if(databaseInstance==null){
            databaseInstance=new Database(context);
        }
        return databaseInstance;

    }




    private Database(Context context){
        dbHelper = MyDatabaseHelper.getDatabaseHelper(context);
        SQLDatabase = dbHelper.getWritableDatabase();
    }


    public long createRecords(String id, String name){
        ContentValues values = new ContentValues();
        //values.put(EMP_ID, id);
        values.put(EMP_NAME, name);
        return SQLDatabase.insert(EMP_TABLE, null, values);

    }

    public Cursor selectRecords() {
        //String[] cols = new String[] {EMP_ID, EMP_NAME};
        String[] cols = new String[] {EMP_NAME, EMP_ID};

        Cursor mCursor = SQLDatabase.query(true, EMP_TABLE,cols,null
                , null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }

    public void deleteDatabase(Context context){
        context.deleteDatabase("GAINZDB");
        context.deleteDatabase("GAINZDB.db");
        context.deleteDatabase("GAINZDB.DB");


        //SQLDatabase.execSQL("drop database if exists GAINZDB;");

    }

    public int lastInsertRow(){
        final String MY_QUERY = "SELECT last_insert_rowid()";
        Cursor cursor = SQLDatabase.rawQuery(MY_QUERY, null);
        cursor.moveToFirst();
        int Id = cursor.getInt(0);
        cursor.close();
        return Id;
    }


}
