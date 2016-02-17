package com.example.sadiq.test;

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
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidGridAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 j* Created by Sadiq on 1/4/2016.
 */
public class mainMenu extends CaldroidFragmentView{
    public ArrayList<WorkoutTemplate> workOutValues=new ArrayList<>();

    @Override
    public CaldroidGridAdapter getNewDatesGridAdapter(int month, int year) {
        // TODO Auto-generated method stub
        //CaldroidGridAdapter caldroidGridAdapter= new CaldroidGridAdapter(getActivity(),month,year,getCaldroidData(),extraData);
        //Date today= new Date();
        //this.setBackgroundResourceForDate(,new Date());
        //this.setBackgroundResourceForDate(R.drawable.red_border,new Date());
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
