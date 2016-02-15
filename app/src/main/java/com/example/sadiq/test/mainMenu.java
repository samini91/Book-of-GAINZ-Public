package com.example.sadiq.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.util.Calendar;

/**
 j* Created by Sadiq on 1/4/2016.
 */
public class mainMenu extends Fragment{


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final ViewGroup root=(ViewGroup) inflater.inflate(R.layout.mainmenu,container,false);

        final CalendarView workoutCalendar=(CalendarView) root.findViewById(R.id.calendarView);



        //return container;
        return root;

    }



}
