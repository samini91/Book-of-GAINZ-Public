package com.example.sadiq.test.WeeklyList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.example.sadiq.test.WeeklyList.WeekDayVariables;
import com.example.sadiq.test.R;

import java.util.ArrayList;

/**
 * Created by Sadiq on 2/22/2016.
 */
public class WeeklyList extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances){


        ViewGroup root =  (ViewGroup) inflater.inflate(R.layout.recyclerviewlayout, container, false);

        RecyclerView list = (RecyclerView) root.findViewById(R.id.my_recycler_view);

        list.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        list.setLayoutManager(mLayoutManager);
        //WeekDayInfo[] a = new WeekDayInfo[]{e};

        ArrayList<WeekDayInfo> weekDayAdapterInfo= WeekDayInfo.initilizeWeeklySchedule();

        Adapter adapter = new Adapter(weekDayAdapterInfo,getActivity());

        list.setAdapter(adapter);



        list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });



        return root;
    }
}
