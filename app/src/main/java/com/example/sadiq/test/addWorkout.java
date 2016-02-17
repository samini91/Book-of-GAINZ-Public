package com.example.sadiq.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.sadiq.test.CustomDataTypes.addWorkoutListAdapter;
import com.woxthebox.draglistview.BoardView;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;

/**
 * Created by Sadiq on 2/16/2016.
 */
public class addWorkout extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle
                                     savedInstanceState){


        ViewGroup root;
        root= (ViewGroup) inflater.inflate(R.layout.addworkout,container,false);

        //mDragListView.setLayoutManager(new LinearLayoutManager(getActivity()));



        ArrayList<WorkoutTemplate> workOutValues=new ArrayList<>();

        ArrayList<String> ex=new ArrayList<>();

        ex.add("squats");
        ex.add("frontsquats");
        final ArrayList<Pair<Long, String>> mItemArray = new ArrayList<>();
        int addItems = 15;
        int sCreatedItems = 0;
        for (int i = 0; i < addItems; i++) {
            long id = sCreatedItems++;
            mItemArray.add(new Pair<>(id, "Item " + id));
        }

        final ArrayList<Pair<Long, String>> mItemArray1 = new ArrayList<>();
        int addItems1 = 15;
        int sCreatedItems1 = 0;
        for (int i = 0; i < addItems1; i++) {
            long id = sCreatedItems1++;
            mItemArray1.add(new Pair<>(id, "Item " + id));
        }


        workOutValues.add(new WorkoutTemplate("Legs", ex));
      //  ItemAdapter listAdapter = new ItemAdapter(mItemArray, R.layout.list_item, R.id.image, false);

        WorkOutAdapter workOutAdapter= new WorkOutAdapter(getActivity(),R.layout.workoutlistview,workOutValues);
        BoardView mBoardView;
        mBoardView = (BoardView) root.findViewById(R.id.addWorkoutBoardView);
        mBoardView.setSnapToColumnsWhenScrolling(true);
        mBoardView.setSnapToColumnWhenDragging(true);
        mBoardView.setSnapDragItemToTouch(true);
        //mBoardView.setCustomDragItem(new MyDragItem(getActivity(), R.layout.column_item));

        addWorkoutListAdapter adapter= new addWorkoutListAdapter(mItemArray,R.layout.column_item,R.id.item_layout,true);
        addWorkoutListAdapter adapter1= new addWorkoutListAdapter(mItemArray1,R.layout.column_item,R.id.item_layout,true);
        //mBoardView.
        mBoardView.setMinimumWidth(23);

        mBoardView.addColumnList(adapter, null, true);
        mBoardView.addColumnList(adapter1, null, true);
        ViewPager vp=(ViewPager) getActivity().findViewById(R.id.pager);

        //vp.clearFocus();

        return root;
    }


    public ArrayList<WorkOutAdapter> getExersices(){


        return new ArrayList<WorkOutAdapter>();
    }
}
