package com.example.sadiq.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.WeekdayArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Sadiq on 2/15/2016.
 */
public class test extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle
                                     savedInstanceState) {

        ViewGroup root;
        root = (ViewGroup)inflater.inflate(R.layout.emptylinearlayout,container,false);

        //root = new LinearLayout(getActivity());

        ListView list = new ListView(getActivity());

        ArrayList<WorkoutTemplate> workOutValues= new ArrayList<>();
        ArrayList<String> ex=new ArrayList<>();

        ex.add("squats");
        ex.add("frontsquats");

        workOutValues.add(new WorkoutTemplate("Legs",ex));
        //workOutValues.add(new WorkoutTemplate("Legs",ex));


        workOutValues.add(new WorkoutTemplate("legs", ex));

        WorkOutAdapter workOutAdapter= new WorkOutAdapter(getActivity(),R.layout.workoutlistview,workOutValues);
        list.setAdapter(workOutAdapter);

        root.addView(list);

        return root;
    }


}
