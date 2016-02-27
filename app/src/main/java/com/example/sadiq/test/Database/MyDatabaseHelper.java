package com.example.sadiq.test.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sadiq on 2/17/2016.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="GAINZDB";
    private static final int VERSION= 2;
    private static String CreateTables="";

    private static final String CreateExersiceTable= "create table Exersices (_Id integer primary key autoincrement, name varchar(39));";

    private static final String CreatePrimaryMuscleTable="create table PrimaryMover (_Id integer primary key autoincrement, ExersicesId integer, bodyPart varchar(20), " +
            "Foreign key(ExersicesId) references Exersices(_Id));";

    private static final String CreateSecondaryMuscleTable="create table SecondaryMover (_Id integer primary key autoincrement, ExersicesId integer, bodyPart varchar(20), " +
            "Foreign key(ExersicesId) references Exersices(_Id));";


    private static final String CreateWorkoutTable="create table WorkOut (_Id integer primary key autoincrement, WorkOutName varchar(20))";


    private static final String CreateWorkoutExersicesTable="create table WorkOutExersices (_Id integer primary key autoincrement, WorkOutId integer ,ExersicesId integer, ExersicesOrder integer, " +
            "Foreign key (WorkOutId) references WorkOut(_Id))";


    private static final String CreateDateWorkoutTable="create table DateWorkOut (_Id integer primary key autoincrement, DateinMilli Integer, WorkOutId Integer," +
            "Foreign key (WorkOutId) references WorkOut(_Id))";

    //_Id will be weekday ie. sunday = 0, monday =1 etc. so the table will only have 7 days so maybe like 50 rows at max no need to normalize that. May change this for additional functionality
    private  static final String CreateWeeklyScheduleTable="create table WeeklySchedule (_Id integer primary key autoincrement, DayOfTheWeekId Integer, WorkOutId Integer," +
            "Foreign Key (WorkOutId) references WorkOut(_Id))";


    private static MyDatabaseHelper databaseHelper;


    public static synchronized MyDatabaseHelper getDatabaseHelper(Context context){

        if(databaseHelper==null){
            databaseHelper=new MyDatabaseHelper(context);
        }
        return databaseHelper;

    }


    private MyDatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CreateExersiceTable);

        db.execSQL(CreatePrimaryMuscleTable);

        db.execSQL(CreateSecondaryMuscleTable);

        db.execSQL(CreateWorkoutTable);

        db.execSQL(CreateWorkoutExersicesTable);

        db.execSQL(CreateDateWorkoutTable);

        db.execSQL(CreateWeeklyScheduleTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
