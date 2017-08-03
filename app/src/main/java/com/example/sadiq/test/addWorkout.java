package com.example.sadiq.test;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadiq.test.CustomDataTypes.addWorkoutListAdapter;
import com.example.sadiq.test.Database.Database;
import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.SelectExerciseConfiguration.SelectExerciseConfiguration;
import com.woxthebox.draglistview.BoardView;

import org.parceler.Parcels;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Sadiq on 2/16/2016.
 */
public class addWorkout extends Fragment   {

    private BoardView mBoardView;

    private addWorkoutListAdapter allExersiceAdapter;
    private addWorkoutListAdapter workOutexersicesAdapter;

    private ArrayList<String> allExersice;
    private ArrayList<String> workOutExersices;

    String exerciseFilterType;
    private ViewGroup root;
    private EditText addWorkoutFilterEditText;
    private Spinner addWorkoutFilterSpinner;
    IViewPagerReplaceFragment iViewPagerReplaceFragment;


    public void setIViewPagerReplaceFragment(IViewPagerReplaceFragment iViewPagerReplaceFragment)
    {
        this.iViewPagerReplaceFragment = iViewPagerReplaceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle
                                     savedInstanceState){

        root = (ViewGroup) inflater.inflate(R.layout.addworkout,container,false);

        addWorkoutFilterEditText = (EditText) root.findViewById(R.id.addworkout_exercisefilter_edittext);

        allExersice = new ArrayList<>();
        workOutExersices = new ArrayList<>();

        setUpExerciseFilter();

        RealmDB realmDB = new RealmDB();
        RealmResults<Exercise> realmResults = realmDB.getAllExercise();
        for (int i = 0; i < realmResults.size(); i++)
            allExersice.add(realmResults.get(i).getName());

        //new AsyncFilter().execute(allExersice,workOutExersices);


        mBoardView = (BoardView) root.findViewById(R.id.addWorkoutBoardView);
        mBoardView.setSnapToColumnsWhenScrolling(true);
        mBoardView.setSnapToColumnWhenDragging(true);
        mBoardView.setSnapDragItemToTouch(true);

        allExersiceAdapter = new addWorkoutListAdapter(getActivity(),allExersice,R.layout.addworkout_column_item,R.id.item_layout,true);
        workOutexersicesAdapter = new addWorkoutListAdapter(getActivity(),workOutExersices,R.layout.addworkout_column_item,R.id.item_layout,true);


        workOutexersicesAdapter.setCustomListener(new addWorkoutListAdapter.CustomListener() {
            @Override
            public void onCustomListenerEvent(String Exercise) {
                Toast.makeText(getActivity(),Exercise,Toast.LENGTH_SHORT).show();

//                iViewPagerReplaceFragment.getViewPagerFragmentManager(R.id.add_workout_topVertical,new SelectExerciseConfiguration());
                //getParentFragment();
                //root.removeAllViewsInLayout();
                //Literally send everything
                FragmentTransaction fragmentTransaction = getParentFragment().getChildFragmentManager().beginTransaction();

//                SelectExerciseConfiguration selectExerciseConfiguration = new SelectExerciseConfiguration();
                addWorkoutSetRepWeightListFragment addWorkoutSetRepWeightListFragment = new addWorkoutSetRepWeightListFragment();

                ArrayList<String> list = new ArrayList<String>();
                list.add("bundletest1");
                list.add("bundletest2");

                Bundle bundle = new Bundle();
                bundle.putSerializable("list",list);
                bundle.putParcelable("asdf", Parcels.wrap(new WorkoutTemplate()));
                bundle.putParcelable("ExerciseSetIndex", Parcels.wrap(Exercise));
                //bundle.putInt("list",(int) list)


                //selectExerciseConfiguration.setArguments(bundle);

                //fragmentTransaction.replace(root.getId(),new SelectExerciseConfiguration());
                fragmentTransaction.replace(R.id.viewpagerroot, addWorkoutSetRepWeightListFragment);
                //fragmentTransaction.replace(R.id.mainlayout_test, addWorkoutSetRepWeightListFragment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        View leftColumnHeader = View.inflate(getActivity(),R.layout.boardviewcolumnheader,null);
        TextView leftColumnHeaderTextView  = ((TextView) leftColumnHeader.findViewById(R.id.columnTextHeader));
        leftColumnHeaderTextView.setText("Exercises");

        View rightColumnHeader = View.inflate(getActivity(),R.layout.boardviewcolumnheader,null);
        ((TextView) rightColumnHeader.findViewById(R.id.columnTextHeader)).setText("Workout");


        mBoardView.addColumnList(allExersiceAdapter, leftColumnHeader, true);
        mBoardView.addColumnList(workOutexersicesAdapter, rightColumnHeader, true);

        EditText nameOfWorkout= (EditText)root.findViewById(R.id.workoutname);


        nameOfWorkout.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    System.out.println(v.getText());
                    return false;
                }
                return false;
            }
        });

        //ViewPager vp=(ViewPager) getActivity().findViewById(R.id.pager);

        //vp.clearFocus();

        final EditText workOutNameView= (EditText)root.findViewById(R.id.workoutname);

        Button clear = (Button)root.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        Button submit = (Button) root.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workOutName = workOutNameView.getText().toString();

                if((workOutName.length() !=0) && workOutexersicesAdapter.getItemCount()>0){
                    //Database.getDatabaseInstance(getActivity()).addWorkout(workOutName,workOutExersices);
                    Toast.makeText(getActivity(),"Workout Created",Toast.LENGTH_SHORT).show();

                    reset();
                }
                else{
                    Toast.makeText(getActivity(),"You are missing Information",Toast.LENGTH_SHORT).show();
                }
            }
        });





        return root;
    }

    public void reset(){
        allExersice.clear();
        //Cursor cursor= Database.getDatabaseInstance(getActivity()).getAllExersice();
        //for (int i =0; i<cursor.getCount();i++){
//            allExersice.add();
//            cursor.moveToNext();
  //      }
        workOutExersices.clear();
        allExersiceAdapter.notifyDataSetChanged();
        workOutexersicesAdapter.notifyDataSetChanged();

    }

    private void setUpExerciseFilter()
    {

        addWorkoutFilterSpinner = (Spinner) root.findViewById(R.id.addworkout_exercisefilter_spinner);

        ArrayList<String> filterTypeList = new ArrayList<>();
        filterTypeList.add("Exercises");
        filterTypeList.add("Primary Mover");
        filterTypeList.add("Secondary Mover");

        ArrayAdapter<String> filterTypeListAdapter  = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, filterTypeList);
        addWorkoutFilterSpinner.setAdapter(filterTypeListAdapter);

        addWorkoutFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0: exerciseFilterType = "Exercises";break;
                    case 1: exerciseFilterType = "Primary Movers" ;break;
                    case 2: exerciseFilterType = "Secondary Movers";break;
                    default: exerciseFilterType = "Exercises";break;
                }
                addWorkoutFilterEditText.setHint(exerciseFilterType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                exerciseFilterType = "Exercises";
                addWorkoutFilterEditText.setHint(exerciseFilterType);
            }
        });

        addWorkoutFilterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                RealmDB realmDB = new RealmDB();


                RealmQuery<Exercise> whereExercise = RealmQuery.createQuery(realmDB.getRealm(),Exercise.class);

                switch (exerciseFilterType)
                {
                    case "Exercises":
                        whereExercise.contains("name",s.toString());
                        break;
                    case "Primary Movers":
                        whereExercise.contains("primaryMoversDBObject.Name", s.toString()).findAll();
                        break;
                    case "Secondary Movers":
                        whereExercise.contains("secondaryMoversDBObject.Name", s.toString()).findAll();
                        break;
                    default:
                        whereExercise.contains("name",s.toString());
                        break;
                }


                RealmResults<Exercise> realmResults = realmDB.getWhereAllExercises(whereExercise);

                allExersice = new ArrayList< String>();

                for (int i = 0; i < realmResults.size(); i++)
                    allExersice.add(realmResults.get(i).getName());

                new AsyncFilter().execute(allExersice,workOutExersices);

                allExersiceAdapter.setItemList(allExersice);
                allExersiceAdapter.notifyDataSetChanged();


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    // right now this will take both lists and filter them to have unique values
    private class AsyncFilter extends AsyncTask<List<String>, Void, Void>
    {

        @Override
        protected Void doInBackground(List<String>... params) {
            // Params 0 will be the exercise list
            // Params 1 will be the workout list
            params[0].removeAll(params[1]);

            return null;
        }
    }


}
