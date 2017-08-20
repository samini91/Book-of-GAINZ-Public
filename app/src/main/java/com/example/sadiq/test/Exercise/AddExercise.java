package com.example.sadiq.test.Exercise;

import android.app.Activity;
import android.app.LauncherActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadiq.test.CustomDataTypes.BodyPartHolder;
import com.example.sadiq.test.CustomDataTypes.muscleGroupList;
import com.example.sadiq.test.Database.Database;
import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.R;


import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

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


public class AddExercise extends Fragment {

    public static int initWithExerciseName = 1;

    RealmDB realmDB = new RealmDB();
    boolean[] primaryBodyPartsforNewExersice;

    @Bind(R.id.leftListView)
    muscleGroupList primaryMuscleList;
    @Bind(R.id.rightListView)
    muscleGroupList secondaryMuscleList;
    @Bind(R.id.nameofexersice)
    EditText nameOfExersice;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.addexersice, container, false);

        ButterKnife.bind(this,root);

        primaryMuscleList.create(getActivity());
        secondaryMuscleList.create(getActivity());

        nameOfExersice.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    System.out.println(v.getText());

                }
                return false;
            }
        });


        Button clear = (Button) root.findViewById(R.id.clear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primaryMuscleList.clear();
                secondaryMuscleList.clear();
            }
        });


        Button submit= (Button) root.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //go thorugh the muscleGroupLists and send a set of movers to the db
                if((nameOfExersice.getText().length() !=0)) {
                    BodyPartHolder[] primaryMovers = primaryMuscleList.getBodyPartsState();
                    BodyPartHolder[] secondaryMovers = secondaryMuscleList.getBodyPartsState();
                    //Database.getDatabaseInstance(getActivity()).AddExercise(nameOfExersice.getText().toString(), primaryMovers,secondaryMovers);

                    realmDB.addorUpdateExersice(nameOfExersice.getText().toString(), primaryMovers,secondaryMovers);
                 //   Database.getDatabaseInstance(getActivity()).AddExercise(nameOfExersice.getText().toString(),primaryMovers,secondaryMovers);


                    primaryMuscleList.clear();
                    secondaryMuscleList.clear();
                }
                else {
                    Toast.makeText(getActivity(),"Enter the name of the Exercise",Toast.LENGTH_SHORT).show();

              }
            }
        });

        //RealmInspectorModulesProvider.builder(getActivity()).build();

        //nameOfExersice.setText()


        return root;
//        return super.onCreateView(inflater, container, savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        if(getTargetRequestCode() == initWithExerciseName){
            String exerciseName = getArguments().getString("name");


            RealmQuery<Exercise> exerciseRealmQuery = RealmQuery.createQuery(realmDB.getRealm(), Exercise.class);
            exerciseRealmQuery.equalTo("name",exerciseName);
            RealmResults<Exercise> exerciseRealmResults = realmDB.getWhereAllExercises(exerciseRealmQuery);


            if(!exerciseRealmResults.isEmpty()) {
                Exercise exercise = exerciseRealmResults.first();

                nameOfExersice.setText(exercise.getName());

                primaryMuscleList.initBodyPartsState(exercise.getPrimaryMoversDBObject());
                secondaryMuscleList.initBodyPartsState(exercise.getSecondaryMoversDBObject());

                // we may need to make a deep copy here. We can always use parceable

                //primaryMuscleList.getB

            }




        }





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
//LinearLayout e = (LinearLayout) inflater.inflate(R.layout.addexersice,container,false);

//  e.addView(new TextView(getActivity()));

        /*
        TextView nameOfExerisiceDialog= (TextView) root.findViewById(R.id.nameofexersice);

        final ListView leftList = (ListView)root.findViewById(R.id.leftListView);

        final ArrayList<String> bodyPartList=new ArrayList<>();
        bodyPartList.add("Legs");
        bodyPartList.add("Chest");
        bodyPartList.add("Legs");bodyPartList.add("Legs");bodyPartList.add("Legs");bodyPartList.add("Legs");bodyPartList.add("Legs");bodyPartList.add("Legs");



        final ArrayAdapter<String> bodyPartListAdapter= new ArrayAdapter<String>(getActivity(),R.layout.muscle_list_row_layout,R.id.listText,bodyPartList);
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
*/
