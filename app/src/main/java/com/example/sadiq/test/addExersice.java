package com.example.sadiq.test;

import android.app.Activity;
import android.app.LauncherActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sadiq on 2/11/2016.
 */
/*
* Notes:
*
* Having a hard time toggleing the postions based on whether they are checked or not The clicklistener is not saving the state of wheter they are checked or not
 * can forgo using that shit and just hashmap everything but the other way is probably better
*
* */


public class addExersice extends Fragment {
    boolean[] primaryBodyPartsforNewExersice;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.addexersice,container,false);
        //LinearLayout e = (LinearLayout) inflater.inflate(R.layout.addexersice,container,false);

      //  e.addView(new TextView(getActivity()));

                
        TextView nameOfExerisiceDialog= (TextView) root.findViewById(R.id.nameofexersice);

        final ListView leftList = (ListView)root.findViewById(R.id.leftListView);

        final ArrayList<String> bodyPartList=new ArrayList<>();
        bodyPartList.add("Legs");
        bodyPartList.add("Chest");
        bodyPartList.add("Legs");bodyPartList.add("Legs");bodyPartList.add("Legs");bodyPartList.add("Legs");bodyPartList.add("Legs");bodyPartList.add("Legs");



        final ArrayAdapter<String> bodyPartListAdapter= new ArrayAdapter<String>(getActivity(),R.layout.row_layout,R.id.listText,bodyPartList);
        //leftList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        leftList.setAdapter(bodyPartListAdapter);
        leftList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //leftList.setItemChecked(1,false);
        //leftList.setOnItemClickListener(new AbsListView.OnItemClickListener() {
        primaryBodyPartsforNewExersice=new boolean[leftList.getAdapter().getCount()];
        // initilizer
        for(boolean k:primaryBodyPartsforNewExersice){
            k=false;
        }
        bodyPartListAdapter.notifyDataSetChanged();

        leftList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(primaryBodyPartsforNewExersice[position]==false){
                    primaryBodyPartsforNewExersice[position]=true;
                    view.setBackgroundColor(Color.GREEN);
                }
                else{
                    primaryBodyPartsforNewExersice[position]=false;
                    view.setBackgroundColor(Color.WHITE);
                }
            }
        });


return root;
//        return super.onCreateView(inflater, container, savedInstanceState);


    }

}
/*
* leftList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //leftList.getItemAtPosition(position);
                //leftList.setItemChecked(position,false);

                System.out.println(leftList.isItemChecked(position) + "         asdfasdf");
                if (leftList.isItemChecked(position)) {

                    leftList.setItemChecked(position, false);

                    view.setBackgroundColor(Color.WHITE);
                } else {
                    leftList.setItemChecked(position, true);
                    view.setBackgroundColor(Color.GREEN);
                    System.out.println(position);
                }

                bodyPartListAdapter.notifyDataSetChanged();
                System.out.println(leftList.isItemChecked(position) + "         asdfasdfasdfasdfasdfasdfa");

            }
        });
*
* */