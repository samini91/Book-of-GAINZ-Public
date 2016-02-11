package com.example.sadiq.test;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sadiq on 2/9/2016.
 */
public class workOutList extends android.support.v4.app.ListFragment {

    public workOutList(){


    }
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle
                                     savedInstanceState) {

        ArrayList stringList= new ArrayList<String>();
        stringList.add("SQWHATS");
        stringList.add("SQWHATS");
        stringList.add("SQWHATS");
        ArrayList<WorkoutTemplate> workOutValues=new ArrayList<>();

        ArrayList<String> ex=new ArrayList<>();

        ex.add("squats");
        ex.add("frontsquats");

        workOutValues.add(new WorkoutTemplate("Legs",ex));
        WorkOutAdapter workOutAdapter= new WorkOutAdapter(getActivity(),R.layout.workoutlistview,workOutValues);


        //ArrayAdapter<String> myAdapter = new ArrayAdapter <String>(getActivity(),R.layout.workoutlistview, R.id.listText, stringList);
        ArrayAdapter<String> myAdapter = new ArrayAdapter <String>(getActivity(),R.layout.workoutlistview, R.id.listText, stringList);

//        setListAdapter(myAdapter);
        setListAdapter(workOutAdapter);

        SimpleAdapter e;
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
       // super.onListItemClick(l, v, position, id);


        Toast.makeText(getActivity(), "asdfl;kjas;dlfkj;alsdkfj",
                Toast.LENGTH_LONG).show();
    }

}





