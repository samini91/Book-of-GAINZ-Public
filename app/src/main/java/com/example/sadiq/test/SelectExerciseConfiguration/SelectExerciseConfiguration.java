package com.example.sadiq.test.SelectExerciseConfiguration;

import android.graphics.Color;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.Database.RealmString;
import com.example.sadiq.test.R;
import com.example.sadiq.test.WorkoutTemplate;

import java.util.ArrayList;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import org.parceler.Parcels;

import butterknife.OnItemSelected;
import co.moonmonkeylabs.realmsearchview.RealmSearchView;
import io.realm.Case;
import io.realm.RealmList;

/**
 * Created by Sadiq on 3/16/2016.
 */
public class SelectExerciseConfiguration extends Fragment {

    RealmSearchView filterableList;
    Spinner filterSelector;
    FilterableListExerciseAdapter filterableListExerciseAdapter;
    ViewGroup root;
    RealmDB realmDB = new RealmDB();

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        //Inflate the View

        ArrayList<String> test;

        RealmList<RealmString> strings = new RealmList<>();

        //WorkoutTemplate workoutTemplate = new WorkoutTemplate("parceltest",strings);

        strings.add(new RealmString("index0 of list parceable"));

        //Parcelable wrapped = Parcels.wrap(workoutTemplate);

        //workoutTemplate.Name = "parcable didnt work";

        //WorkoutTemplate workoutTemplate1 = Parcels.unwrap(wrapped);
        //Log.i(workoutTemplate1.Name,workoutTemplate1.Name);
        //Log.i(workoutTemplate1.Name,workoutTemplate1.strings.get(0).getValue());


        //if(savedInstanceState != null && !savedInstanceState.isEmpty())
        if(getArguments() != null)
        {
            test = (ArrayList<String>) getArguments().getSerializable("list");
            Log.i("test", test.get(0));
            Log.i("test", test.get(1));
        }

        root = (ViewGroup)inflater.inflate(R.layout.selectexerciseconfigurationmain, container, false);

        filterableList = (RealmSearchView) root.findViewById(R.id.FilterableList);




        filterableListExerciseAdapter = new FilterableListExerciseAdapter(getActivity(), realmDB.getRealm(),"name");
        //filterableListExerciseAdapter = new FilterableListExerciseAdapter(getActivity(), RealmDB.getRealmInstance(getActivity()).getRealm(),
          //      "primaryMoversDBObject.name");
        filterableListExerciseAdapter.setCasing(Case.INSENSITIVE);
        filterableListExerciseAdapter.setUseContains(true);
        filterableList.setAdapter(filterableListExerciseAdapter);



        filterableList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"asl;dkfjasl;dfjk",Toast.LENGTH_SHORT).show();
            }
        });


        initFilterSelector();

//        root.setBackgroundColor(Color.CYAN);

        return root;

    }



    @Override
    public String toString(){

        return "swagerdagerdo";
    }





    public void initFilterSelector(){
        filterSelector = (Spinner) root.findViewById(R.id.FilterSelector);

        ArrayList<String> filterValues = new ArrayList<>();

        filterValues.add("Name");
        filterValues.add("PrimaryMovers");
        filterValues.add("SecondaryMovers");

        final ArrayAdapter<String> filterValuesAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_spinner_item,filterValues);

        filterValuesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        filterSelector.setAdapter(filterValuesAdapter);

        filterSelector.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch((int)id){
                    case 0:{
                        filterableListExerciseAdapter.setFilterKey("name");
                    }break;
                    case 1:{
                        filterableListExerciseAdapter.setFilterKey("primaryMoversDBObject.name");
                    }break;
                    case 2:{
                        filterableListExerciseAdapter.setFilterKey("secondaryMoversDBObject.name");
                    }break;
                }
                filterableListExerciseAdapter.notifyDataSetChanged();
                filterableListExerciseAdapter.filter(filterableList.getSearchBarText());
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }





}
