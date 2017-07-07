package com.example.sadiq.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.SelectExerciseConfiguration.FilterableListExerciseAdapter;

import java.util.ArrayList;

import co.moonmonkeylabs.realmsearchview.RealmSearchView;
import io.realm.Case;

/**
 * Created by Mugen on 6/6/2016.
 */
public class FilterableListExercise extends RelativeLayout {
    //TODO need to create getters and setters for values associated with this
    RealmSearchView filterableList;
    Spinner filterSelector;
    FilterableListExerciseAdapter filterableListExerciseAdapter;
    ViewGroup root;
    RealmDB realmDB = new RealmDB();

    Context context;
    public FilterableListExercise(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public FilterableListExercise(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FilterableListExercise(Context context) {
        super(context);
        init(context);
    }

    private void init(final Context context){

        this.context=context;

        root = (ViewGroup) inflate(context, R.layout.filterablelist_exersice, this);

        filterableList = (RealmSearchView) root.findViewById(R.id.FilterableList);

        filterableListExerciseAdapter = new FilterableListExerciseAdapter(context, realmDB.getRealm(),"name");
        //filterableListExerciseAdapter = new FilterableListExerciseAdapter(getActivity(), RealmDB.getRealmInstance(getActivity()).getRealm(),
        //      "primaryMoversDBObject.name");
        filterableListExerciseAdapter.setCasing(Case.INSENSITIVE);
        filterableListExerciseAdapter.setUseContains(true);
        filterableList.setAdapter(filterableListExerciseAdapter);



        filterableList.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(context,"asl;dkfjasl;dfjk",Toast.LENGTH_SHORT).show();
            }
        });


        initFilterSelector();

    }


    public void initFilterSelector(){
        filterSelector = (Spinner) root.findViewById(R.id.FilterSelector);

        ArrayList<String> filterValues = new ArrayList<>();

        filterValues.add("Name");
        filterValues.add("PrimaryMovers");
        filterValues.add("SecondaryMovers");

        final ArrayAdapter<String> filterValuesAdapter = new ArrayAdapter<>(context,android.R.layout.simple_spinner_item,filterValues);

        filterValuesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        filterSelector.setAdapter(filterValuesAdapter);

        filterSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
