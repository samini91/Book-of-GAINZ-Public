package com.example.sadiq.test.MainMenu;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import com.example.sadiq.test.CustomDataTypes.CaldroidFragmentView;
import com.example.sadiq.test.Database.Database;
import com.example.sadiq.test.R;
import com.example.sadiq.test.WorkOutAdapter;
import com.example.sadiq.test.WorkoutTemplate;
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
        LayoutInflater inflater = getActivity().getLayoutInflater();

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_blank, getViewGroup(), true);



        //RecyclerView workOutInstanceList= new RecyclerView(getActivity());
        //getViewGroup().addView(workOutInstanceList);


        ListView list = new ListView(getActivity());
        ArrayList<String> ex=new ArrayList<>();
        ArrayList<String> ex1=new ArrayList<>();
        ex.add("squats");
        ex.add("frontsquats");
        workOutValues.add(new WorkoutTemplate("Leg1s", ex));

        list.setAdapter(new WorkOutAdapter(getActivity(), R.layout.workoutlistview, workOutValues));

       // getViewGroup().addView(list);

    }




}
