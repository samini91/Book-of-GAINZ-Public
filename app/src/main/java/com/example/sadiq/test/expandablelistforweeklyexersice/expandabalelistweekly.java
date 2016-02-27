package com.example.sadiq.test.expandablelistforweeklyexersice;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

/**
 * Created by Sadiq on 2/22/2016.
 */
public class expandabalelistweekly extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstances){

        ViewGroup e = new LinearLayout(getActivity());


        ExpandableListView expandableListView = new ExpandableListView(getActivity());

        //ExpandableListAdapter adapter = new ExpandableListAdapter();

        e.addView(expandableListView);
        return e;

    }
}
