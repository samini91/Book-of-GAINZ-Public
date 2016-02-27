package com.example.sadiq.test.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.SyncStateContract;
import android.support.v4.util.Pair;


import com.example.sadiq.test.CustomDataTypes.BodyPartHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Sadiq on 2/10/2016.
 */
//singleton class for the database
public class Database {

    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase SQLDatabase;
    private static Database databaseInstance;

    private final static String PrimaryKeyId="_Id";


    public final static String EMP_TABLE="Exersices"; // name of table

    public final static String ExersiceTable="Exersices"; // name of table
    public final static String Exersicetable_name="name";

    public final static String PrimaryMoverTable="PrimaryMover";
    public final static String PrimaryMoverTable_ExersicesId="ExersicesId";
    public final static String PrimaryMoverTable_bodyPart="bodyPart";

    public final static String SecondaryMoverTable="SecondaryMover";
    public final static String SecondaryMoverTable_ExersicesId="ExersicesId";
    public final static String SecondaryMoverTable_bodyPart="bodyPart";


    public final static String WorkOutTable="WorkOut";
    public final static String WorkOutTable_name= "WorkOutName";


    public final static String WorkOutExersicesTable="WorkOutExersices";
    public final static String WorkOutExersicesTable_WorkOutId="WorkOutId";
    public final static String WorkOutExersicesTable_ExersicesId="ExersicesId";
    public final static String WorkOutExersciesTable_ExersicesOrder="ExersicesOrder";


    public final static String DateWorkOutTable="DateWorkOut";
    public final static String DateWorkOutTable_DateinMilli="DateinMilli";
    public final static String DateWorkOutTable_WorkOutId="WorkOutId";

    public final static String WeeklyScheduleTable="WeeklySchedule";
    public final static String WeeklyScheduleTable_DayofTheWeek="DayOfTheWeekId";
    public final static String WeeklyScheduleTable_WorkOutId="WorkOutId";



    public final static String EMP_ID="_Id"; // id value for employee
    public final static String EMP_NAME="name";  // name of employee
    /**
     *
     * @param context
     */
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

    public Cursor getPrimaryMoverForAllExersices(){
        String[] cols = new String[] {PrimaryMoverTable_bodyPart, PrimaryMoverTable_ExersicesId,PrimaryKeyId};
        //Cursor mCursor = SQLDatabase.query(true, PrimaryMoverTable,cols,null, null, null, null, null, null);

        Cursor mCursor = SQLDatabase.rawQuery("select "+ExersiceTable+"."+PrimaryKeyId +" , " +ExersiceTable +"."+Exersicetable_name +" , "+PrimaryMoverTable+"."+PrimaryMoverTable_bodyPart +
                " from "+PrimaryMoverTable+ "," +ExersiceTable +
                " where "+ PrimaryMoverTable +"."+PrimaryMoverTable_ExersicesId
                +"=" + ExersiceTable+"."+PrimaryKeyId
                ,null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }

    public Cursor getPrimaryMoverForExersice(int ExersiceId){

        Cursor mCursor = SQLDatabase.rawQuery("select "+ExersiceTable+"."+PrimaryKeyId +" , " +ExersiceTable +"."+Exersicetable_name +" , "+PrimaryMoverTable+"."+PrimaryMoverTable_bodyPart +
                " from "+PrimaryMoverTable+ "," +ExersiceTable +
                " where "+ PrimaryMoverTable +"."+PrimaryMoverTable_ExersicesId
                +"=" + ExersiceTable+"."+PrimaryKeyId+" and "+ ExersiceTable+"."+PrimaryKeyId+"="+ExersiceId
                ,null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }

    public Cursor getSecondaryMoverForExersice(int ExersiceId){

        Cursor mCursor = SQLDatabase.rawQuery("select "+ExersiceTable+"."+PrimaryKeyId +" , " +ExersiceTable +"."+Exersicetable_name +" , "+SecondaryMoverTable+"."+SecondaryMoverTable_bodyPart +
                " from "+SecondaryMoverTable+ "," +ExersiceTable +
                " where "+ SecondaryMoverTable +"."+SecondaryMoverTable_ExersicesId
                +"=" + ExersiceTable+"."+PrimaryKeyId+" and "+ ExersiceTable+"."+PrimaryKeyId+"="+ExersiceId
                ,null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.
    }





    public Cursor selectRecords() {
        //String[] cols = new String[] {EMP_ID, EMP_NAME};
        String[] cols = new String[] {EMP_NAME, EMP_ID};

        Cursor mCursor = SQLDatabase.query(true, EMP_TABLE, cols, null
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

    public void addExersice(String exersiceName,BodyPartHolder[] primaryBodyPartHolders,BodyPartHolder[] secondaryBodyPartHolders){
        ContentValues values = new ContentValues();
        values.put("name", exersiceName);
        SQLDatabase.insert(ExersiceTable, null, values);
        int Id=this.lastInsertRow();
        values.clear();
        //for(BodyPartHolder i: primaryBodyPartHolders){
        for (int i = 0;i<primaryBodyPartHolders.length;i++){
            if(primaryBodyPartHolders[i].activate==true){
                values.put(PrimaryMoverTable_ExersicesId,Id);
                values.put(PrimaryMoverTable_bodyPart,primaryBodyPartHolders[i].name);
                SQLDatabase.insert(PrimaryMoverTable,null,values);
                values.clear();
            }
            if(secondaryBodyPartHolders[i].activate==true){
                values.put(SecondaryMoverTable_ExersicesId,Id);
                values.put(SecondaryMoverTable_bodyPart,secondaryBodyPartHolders[i].name);
                SQLDatabase.insert(SecondaryMoverTable,null,values);
                values.clear();
            }
        }
    }

    public void addWorkout(String workOutName, ArrayList<Pair<Long,String>> exersices){
        ContentValues values = new ContentValues();
        values.put(WorkOutTable_name, workOutName);
        SQLDatabase.insert(WorkOutTable, null, values);
        int Id = this.lastInsertRow();
        values.clear();
        for (int i = 0 ;i < exersices.size();i++){
            values.put(WorkOutExersicesTable_ExersicesId, exersices.get(i).first.intValue());
            values.put(WorkOutExersicesTable_WorkOutId, Id);
            values.put(WorkOutExersciesTable_ExersicesOrder,i+1);
            SQLDatabase.insert(WorkOutExersicesTable,null,values);
            values.clear();
        }

    }


    /**
     *  Table Contents:
     *  name varchar
     **/

    public Cursor getAllExersice(){
        String[] cols = new String[] {PrimaryKeyId, Exersicetable_name};
        Cursor mCursor = SQLDatabase.query(true, ExersiceTable, cols, null
                , null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor; // iterate to get each value.

        //HashSet
    }

    public Cursor getAllWorkouts(){
        String[] cols = new String[] {PrimaryKeyId,WorkOutTable_name};
        Cursor mCursor = SQLDatabase.query(true,WorkOutTable,cols,null,null,null,null,null,null);
        if(mCursor!=null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor getExersicesforWorkout(int Id) {

        Cursor cursor = SQLDatabase.rawQuery(
                " select * " +
                        "from " + WorkOutTable + "," + WorkOutExersicesTable + " " +
                        " where " + WorkOutTable + "." + PrimaryKeyId + "=" + WorkOutExersicesTable + "." + WorkOutExersicesTable_WorkOutId
                        + " and " + WorkOutTable + "." + PrimaryKeyId + "=" + Id, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }


    public void addDateforWorkout(long WorkOutId, Date date){
        ContentValues values = new ContentValues();
        values.put(DateWorkOutTable_WorkOutId,WorkOutId);
        values.put(DateWorkOutTable_DateinMilli, date.getTime());
        SQLDatabase.insert(DateWorkOutTable, null, values);
    }

    public Cursor getDateforWorkout(Date date){
        String[] cols = new String[] {PrimaryKeyId,DateWorkOutTable_WorkOutId,DateWorkOutTable_DateinMilli};
        String[] where = new String[]{date.getTime()+"="+DateWorkOutTable_DateinMilli};
        Cursor mCursor = SQLDatabase.query(true,DateWorkOutTable,cols,date.getTime()+"="+DateWorkOutTable_DateinMilli,null,null,null,null,null);

        if(mCursor!=null){
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public void addWeeklyExersice(long dayOfTheWeek, long WorkOutId){
        ContentValues values = new ContentValues();
        SQLDatabase.delete(WeeklyScheduleTable, WeeklyScheduleTable_DayofTheWeek+"="+dayOfTheWeek,null);
        values.put(WeeklyScheduleTable_DayofTheWeek, (int)dayOfTheWeek);
        values.put(WeeklyScheduleTable_WorkOutId, (int) WorkOutId);
        SQLDatabase.insert(WeeklyScheduleTable, null, values);
    }
    //needtochange
    public Cursor getWeeklyExersice(long dayOfTheWeek){
        //String[] cols = new String[]{WeeklyScheduleTable_DayofTheWeek,WeeklyScheduleTable_WorkOutId};
        //Cursor mCursor = SQLDatabase.query(true,WeeklyScheduleTable,cols,WeeklyScheduleTable_DayofTheWeek+"="+dayOfTheWeek,null,null,null,null,null);



        Cursor mCursor = SQLDatabase.rawQuery("Select * " +
                "from " + WeeklyScheduleTable + "," + WorkOutExersicesTable +","+ExersiceTable+ " " +
                "where " + WeeklyScheduleTable + "." + WeeklyScheduleTable_DayofTheWeek + "=" + Long.toString(dayOfTheWeek) +
                " and " + WeeklyScheduleTable + "." + WeeklyScheduleTable_WorkOutId + "=" + WorkOutExersicesTable + "." + WorkOutExersicesTable_WorkOutId+
                " and " + WorkOutExersicesTable+"."+WorkOutExersicesTable_WorkOutId +"="+ExersiceTable+"."+PrimaryKeyId


                , null);

        if(mCursor!=null){
            mCursor.moveToFirst();
        }

        return mCursor;
    }

}
