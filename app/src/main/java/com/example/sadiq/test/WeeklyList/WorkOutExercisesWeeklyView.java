package com.example.sadiq.test.WeeklyList;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadiq.test.Database.Database;

import java.util.ArrayList;

/**
 * TextView with Id
 */
public class WorkOutExercisesWeeklyView extends LinearLayout {

    public long dayOftheWeekId;


    public WorkOutExercisesWeeklyView(Context context) {
        super(context);
    }
    public WorkOutExercisesWeeklyView(Context context,AttributeSet attrs){super(context,attrs);}

    public WorkOutExercisesWeeklyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr){super(context,attrs,defStyleAttr);}

    //public WorkOutExercisesWeeklyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){super(context,attrs,defStyleAttr,defStyleRes);}




    //Input : WeekDay Id
    public void populateLayoutWithExersices(){


        Cursor cursor=Database.getDatabaseInstance(getContext()).getWeeklyExersice(dayOftheWeekId);
        for(int i = 0;i<cursor.getCount();i++){

            TextView workoutid= new TextView(getContext());
            workoutid.setText(cursor.getString(2));
            this.addView(workoutid);
            cursor.moveToNext();


        }


    }


}
