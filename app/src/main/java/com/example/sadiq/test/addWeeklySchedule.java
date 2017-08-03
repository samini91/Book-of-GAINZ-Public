package com.example.sadiq.test;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;

import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.sadiq.test.CustomDataTypes.addWorkoutListAdapter;
import com.example.sadiq.test.Database.Database;
import com.woxthebox.draglistview.BoardView;
import com.woxthebox.draglistview.DragItemRecyclerView;

import java.util.ArrayList;

/**
 * Created by Sadiq on 2/20/2016.
 */
public class addWeeklySchedule extends Fragment {

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle
                                     savedInstanceState) {


        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.addweeklyscedule, container, false);


        //ListView daysOfTheWeekView = (ListView)root.findViewById(R.id.daysOfTheWeek);

        String daysOfTheWeek[] ={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

        ArrayAdapter<String> daysOfTheWeekAdapter = new ArrayAdapter<>(getActivity(),R.layout.muscle_list_row_layout,R.id.listText,daysOfTheWeek);

        //daysOfTheWeekView.setAdapter(daysOfTheWeekAdapter);


        ArrayList<String> allExersice = new ArrayList<>();

        ArrayList<String>  workOutExersices = new ArrayList<>();


        addWorkoutListAdapter allExersiceAdapter;
        addWorkoutListAdapter workOutexersicesAdapter;

        Cursor cursor= Database.getDatabaseInstance(getActivity()).getAllWorkouts();
        for (int i =0; i<cursor.getCount();i++){
            //allExersice.add(new Pair<>(Long.parseLong(cursor.getString(0)), cursor.getString(1)));
            cursor.moveToNext();
        }

        BoardView mBoardView=(BoardView)root.findViewById(R.id.addWeeklyWorkoutBoardView);
        mBoardView.setSnapToColumnsWhenScrolling(true);
        mBoardView.setSnapToColumnWhenDragging(true);
        mBoardView.setSnapDragItemToTouch(true);

        allExersiceAdapter = new addWorkoutListAdapter(getActivity(),allExersice,R.layout.addworkout_column_item,R.id.item_layout,true);
        workOutexersicesAdapter = new addWorkoutListAdapter(getActivity(),workOutExersices,R.layout.addworkout_column_item,R.id.item_layout,true);

        mBoardView.addColumnList(allExersiceAdapter, null, true);
        mBoardView.addColumnList(workOutexersicesAdapter, null, true);







        final DragItemRecyclerView v = new DragItemRecyclerView(getActivity());
        //v.setAdapter(allExersiceAdapter);

        BoardView testBackground = (BoardView) root.findViewById(R.id.weekdaysbackground);

        ArrayList<String> empty = new ArrayList<>();
        ArrayList<String> background = new ArrayList<>();


        for(int i =0;i<daysOfTheWeek.length;i++){
        //background.add(new Pair<Long, String>((long)i,daysOfTheWeek[i]));
        }

        addWorkoutListAdapter backgroundAdapter = new addWorkoutListAdapter(getActivity(),background,R.layout.addworkout_column_item,R.id.item_layout,true);
        addWorkoutListAdapter emptyAdapter = new addWorkoutListAdapter(getActivity(),empty,R.layout.addworkout_column_item,R.id.item_layout,true);



        v.setAdapter(backgroundAdapter);

        testBackground.addColumnList(emptyAdapter,null,true);
        testBackground.addColumnList(backgroundAdapter,null,true);

        v.clearFocus();
       // root.addView(v);

        mBoardView.bringToFront();
        //daysOfTheWeekView.bringToFront();

        return root;

    }
}
