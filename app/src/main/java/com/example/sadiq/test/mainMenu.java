package com.example.sadiq.test;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import com.example.sadiq.test.CustomDataTypes.CaldroidFragmentView;
import com.example.sadiq.test.Database.Database;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;
import com.roomorama.caldroid.CaldroidListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 j* Created by Sadiq on 1/4/2016.
 */
public class mainMenu extends CaldroidFragmentView{
    public ArrayList<WorkoutTemplate> workOutValues=new ArrayList<>();
    public static Calendar cal;

    @Override
    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        cal = Calendar.getInstance();cal.set(Calendar.HOUR_OF_DAY, 0);cal.set(Calendar.MINUTE, 0);cal.set(Calendar.SECOND, 0);cal.set(Calendar.MILLISECOND, 0);

        this.setCaldroidListener(new CaldroidListener() {
            @Override
            public void onSelectDate(Date date, View view) {

                Database.getDatabaseInstance(getActivity()).addDateforWorkout(0,cal.getTime());

                if(date.getDay()==0 && (date.compareTo(cal.getTime())>=0)){
                    Cursor cursor=Database.getDatabaseInstance(getActivity()).getDateforWorkout(date);
                    String data = "";
                    for(int i = 0;i<cursor.getColumnCount();i++){
                        data+=cursor.getColumnName(i)+"   ";
                    }
                    data += "\n";
                    for (int i=0;i<cursor.getCount();i++){
                        for(int k =0;k<cursor.getColumnCount();k++){
                            data+=cursor.getString(k)+"   ";
                        }
                        data += "\n";
                        cursor.moveToNext();
                    }
                    System.out.println(data);

                }
            }
        });


        this.setBackgroundResourceForDate(R.drawable.redtest, new Date());
        //caldroidGridAdapter
        return new CaldroidGridAdapter(getActivity(), month, year,
                getCaldroidData(), extraData);
    }

    @Override
    public void createOtherViews(){
        ListView list = new ListView(getActivity());

        ArrayList<String> ex=new ArrayList<>();
        ArrayList<String> ex1=new ArrayList<>();
        ex.add("squats");
        ex.add("frontsquats");
        ex1.add("asdf");
        ex1.add("asdasdfasdff");
        workOutValues.add(new WorkoutTemplate("Legs", ex));
        workOutValues.add(new WorkoutTemplate("Leg1s", ex1));
        workOutValues.add(new WorkoutTemplate("Leg1s", ex1));
        workOutValues.add(new WorkoutTemplate("Leg1s", ex1));
        workOutValues.add(new WorkoutTemplate("Leg1s", ex1));
        workOutValues.add(new WorkoutTemplate("Leg1s", ex1));
        workOutValues.add(new WorkoutTemplate("Leg1s", ex1));

        list.setAdapter(new WorkOutAdapter(getActivity(), R.layout.workoutlistview, workOutValues));


        getViewGroup().addView(list);
        //getViewGroup().addView(new Button(getActivity()));


    }




}
