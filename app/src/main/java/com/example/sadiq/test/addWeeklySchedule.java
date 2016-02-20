package com.example.sadiq.test;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sadiq.test.CustomDataTypes.addWorkoutListAdapter;
import com.example.sadiq.test.Database.Database;
import com.woxthebox.draglistview.BoardView;

import java.util.ArrayList;

/**
 * Created by Sadiq on 2/20/2016.
 */
public class addWeeklySchedule extends Fragment {

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle
                                     savedInstanceState) {


        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.addweeklyscedule, container, false);


        ListView daysOfTheWeekView = (ListView)root.findViewById(R.id.daysOfTheWeek);

        String daysOfTheWeek[] ={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

        ArrayAdapter<String> daysOfTheWeekAdapter = new ArrayAdapter<String>(getActivity(),R.layout.row_layout,R.id.listText,daysOfTheWeek);

        daysOfTheWeekView.setAdapter(daysOfTheWeekAdapter);


        ArrayList<Pair<Long, String>> allExersice = new ArrayList<>();

        ArrayList<Pair<Long, String>>  workOutExersices = new ArrayList<>();


        addWorkoutListAdapter allExersiceAdapter;
        addWorkoutListAdapter workOutexersicesAdapter;

        Cursor cursor= Database.getDatabaseInstance(getActivity()).getAllExersice();
        for (int i =0; i<cursor.getCount();i++){
            allExersice.add(new Pair<>(Long.parseLong(cursor.getString(0)), cursor.getString(1)));
            cursor.moveToNext();
        }

        BoardView mBoardView=(BoardView)root.findViewById(R.id.addWeeklyWorkoutBoardView);
        mBoardView.setSnapToColumnsWhenScrolling(true);
        mBoardView.setSnapToColumnWhenDragging(true);
        mBoardView.setSnapDragItemToTouch(true);

        allExersiceAdapter = new addWorkoutListAdapter(getActivity(),allExersice,R.layout.column_item,R.id.item_layout,true);
        workOutexersicesAdapter = new addWorkoutListAdapter(getActivity(),workOutExersices,R.layout.column_item,R.id.item_layout,true);

        mBoardView.addColumnList(allExersiceAdapter, null, true);
        mBoardView.addColumnList(workOutexersicesAdapter, null, true);


        mBoardView.bringToFront();


        //daysOfTheWeekView.bringToFront();

        return root;

    }
}
