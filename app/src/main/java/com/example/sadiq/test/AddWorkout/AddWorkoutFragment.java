package com.example.sadiq.test.AddWorkout;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
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

import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Database.ExerciseSetRep;
import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.IActivityDataFactory;
import com.example.sadiq.test.IViewPagerReplaceFragment;
import com.example.sadiq.test.R;
import com.example.sadiq.test.Database.WorkoutTemplate;
import com.woxthebox.draglistview.BoardView;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class AddWorkoutFragment extends DialogFragment {

        public static int in_WorkoutSetRepWeightListFragment = 1;
        public static int in_WorkoutName = 2;

        private BoardView mBoardView;

        private AddWorkoutListAdapter allExersiceAdapter;
        private AddWorkoutListAdapter workOutexersicesAdapter;

        private RealmList<ExerciseSetRep> allExersice;
        private RealmList<ExerciseSetRep> workOutExersices;

        String exerciseFilterType;
        private ViewGroup root;
        private EditText addWorkoutFilterEditText;
        private Spinner addWorkoutFilterSpinner;
        IViewPagerReplaceFragment iViewPagerReplaceFragment;
        AddWorkoutListAdapter.CustomListener addWorkoutListAdapterCustomListener;
        String workOutName;
        AddWorkoutFragment addWorkoutFragment = this;

        final static int a = 0;


        public void setIViewPagerReplaceFragment(IViewPagerReplaceFragment iViewPagerReplaceFragment) {
                this.iViewPagerReplaceFragment = iViewPagerReplaceFragment;
        }

        public void setAddWorkoutListAdapterCustomListerner(AddWorkoutListAdapter.CustomListener customListener) {
                addWorkoutListAdapterCustomListener = customListener;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

                root = (ViewGroup) inflater.inflate(R.layout.addworkout, container, false);

                addWorkoutFilterEditText = (EditText) root.findViewById(R.id.addworkout_exercisefilter_edittext);

                allExersice = new RealmList<>();
                workOutExersices = new RealmList<>();

                setUpExerciseFilter();

                final RealmDB realmDB = new RealmDB();
                RealmResults<Exercise> realmResults = realmDB.getAllExercise();

                for (int i = 0; i < realmResults.size(); i++) {
                        ExerciseSetRep exerciseSetRep = new ExerciseSetRep();
                        exerciseSetRep.setExerciseName(realmResults.get(i).getName());
                        allExersice.add(exerciseSetRep);
                }

                if (WorkOutExercisesSingleton.getWorkOutExercisesSingleton() != null) {
                        workOutExersices = (RealmList<ExerciseSetRep>) WorkOutExercisesSingleton.getWorkOutExercisesSingleton();
                        new AsyncFilter().execute(allExersice, workOutExersices);
                }


                mBoardView = (BoardView) root.findViewById(R.id.addWorkoutBoardView);
                mBoardView.setSnapToColumnsWhenScrolling(true);
                mBoardView.setSnapToColumnWhenDragging(true);
                mBoardView.setSnapDragItemToTouch(true);

                allExersiceAdapter = new AddWorkoutListAdapter(getActivity(), allExersice, R.layout.addworkout_column_item, R.id.item_layout, true);
                workOutexersicesAdapter = new AddWorkoutListAdapter(getActivity(), workOutExersices, R.layout.addworkout_column_item, R.id.item_layout, true);

                if (getArguments() != null) {
                        if (getTargetRequestCode() == in_WorkoutSetRepWeightListFragment) {
                        }
                        //  setWorkoutListExerciseSetRep((ExerciseSetRep) Parcels.unwrap(getArguments().getParcelable("ExerciseSetRepConfig")));
                        else if (getTargetRequestCode() == in_WorkoutName) {
                                setWorkoutListFromWorkoutName(((WorkoutTemplate) Parcels.unwrap(getArguments().getParcelable("workoutTemplate"))).getName());
                        }


                }

                workOutexersicesAdapter.setCustomListener(new AddWorkoutListAdapter.CustomListener() {
                        @Override
                        public void onCustomListenerEvent(String Exercise, int index) {
                                Bundle bundle = new Bundle();
                                bundle.putParcelable("ExerciseSetRep", Parcels.wrap(workOutexersicesAdapter.getItemList().get(index)));
                                ((IActivityDataFactory) getActivity()).ActivityDataFactory(addWorkoutFragment, "AddWorkoutSetRepWeightListFragment", 0, IActivityDataFactory.detail, bundle);
                        }
                });

                View leftColumnHeader = View.inflate(getActivity(), R.layout.boardviewcolumnheader, null);
                TextView leftColumnHeaderTextView = ((TextView) leftColumnHeader.findViewById(R.id.columnTextHeader));
                leftColumnHeaderTextView.setText("Exercises");

                View rightColumnHeader = View.inflate(getActivity(), R.layout.boardviewcolumnheader, null);
                ((TextView) rightColumnHeader.findViewById(R.id.columnTextHeader)).setText("Workout");


                mBoardView.addColumnList(allExersiceAdapter, leftColumnHeader, true);
                mBoardView.addColumnList(workOutexersicesAdapter, rightColumnHeader, true);

                EditText nameOfWorkout = (EditText) root.findViewById(R.id.workoutname);


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

                final EditText workOutNameView = (EditText) root.findViewById(R.id.workoutname);

                final AlertDialog.Builder clearAlertDialog = new AlertDialog.Builder(getActivity());
                clearAlertDialog.setMessage("Are you sure you want to clear?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                reset();

                        }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                });

                Button clear = (Button) root.findViewById(R.id.clear);
                clear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                clearAlertDialog.show();
                        }
                });


                Button submit = (Button) root.findViewById(R.id.submit);

                submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                workOutName = workOutNameView.getText().toString();

                                if ((workOutName.length() != 0) && workOutexersicesAdapter.getItemCount() > 0) {
                                        try {
                                                realmDB.getRealm().executeTransaction(new Realm.Transaction() {
                                                        @Override
                                                        public void execute(Realm realm) {
                                                                realmDB.addOrUpdateWorkout(new WorkoutTemplate(workOutName, (RealmList<ExerciseSetRep>) workOutexersicesAdapter.getItemList()));
                                                        }
                                                });
                                        } catch (Exception e) {
                                                e.printStackTrace();
                                                throw e;
                                        } finally {
                                                realmDB.getRealm().close();
                                        }

                                        reset();
                                } else {
                                        Toast.makeText(getActivity(), "You are missing Information", Toast.LENGTH_SHORT).show();
                                }
                        }
                });

                workOutexersicesAdapter.notifyDataSetChanged();
                return root;
        }

        public void reset() {
                allExersice.clear();
                RealmDB realmDB = new RealmDB();
                RealmResults<Exercise> realmResults = realmDB.getAllExercise();

                for (int i = 0; i < realmResults.size(); i++) {
                        ExerciseSetRep exerciseSetRep = new ExerciseSetRep();
                        exerciseSetRep.setExerciseName(realmResults.get(i).getName());
                        allExersice.add(exerciseSetRep);
                }

                workOutExersices.clear();
                WorkOutExercisesSingleton.clear();
                allExersiceAdapter.notifyDataSetChanged();
                workOutexersicesAdapter.notifyDataSetChanged();

        }

        private void setUpExerciseFilter() {

                addWorkoutFilterSpinner = (Spinner) root.findViewById(R.id.addworkout_exercisefilter_spinner);

                ArrayList<String> filterTypeList = new ArrayList<>();
                filterTypeList.add("Exercises");
                filterTypeList.add("Primary Mover");
                filterTypeList.add("Secondary Mover");

                ArrayAdapter<String> filterTypeListAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, filterTypeList);
                addWorkoutFilterSpinner.setAdapter(filterTypeListAdapter);

                addWorkoutFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position) {
                                        case 0:
                                                exerciseFilterType = "Exercises";
                                                break;
                                        case 1:
                                                exerciseFilterType = "Primary Movers";
                                                break;
                                        case 2:
                                                exerciseFilterType = "Secondary Movers";
                                                break;
                                        default:
                                                exerciseFilterType = "Exercises";
                                                break;
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


                                RealmQuery<Exercise> whereExercise = RealmQuery.createQuery(realmDB.getRealm(), Exercise.class);

                                switch (exerciseFilterType) {
                                        case "Exercises":
                                                whereExercise.contains("name", s.toString());
                                                break;
                                        case "Primary Movers":
                                                whereExercise.contains("primaryMoversDBObject.Name", s.toString()).findAll();
                                                break;
                                        case "Secondary Movers":
                                                whereExercise.contains("secondaryMoversDBObject.Name", s.toString()).findAll();
                                                break;
                                        default:
                                                whereExercise.contains("name", s.toString());
                                                break;
                                }


                                RealmResults<Exercise> realmResults = realmDB.getWhereAllExercises(whereExercise);

                                allExersice = new RealmList<ExerciseSetRep>();

                                for (int i = 0; i < realmResults.size(); i++) {

                                        ExerciseSetRep exerciseSetRep = new ExerciseSetRep();
                                        exerciseSetRep.setExerciseName(realmResults.get(i).getName());
                                        allExersice.add(exerciseSetRep);

                                }

                                new AsyncFilter().execute(allExersice, workOutExersices);

                                allExersiceAdapter.setItemList(allExersice);
                                allExersiceAdapter.notifyDataSetChanged();


                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                });

        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
                super.onSaveInstanceState(outState);
                if (workOutExersices.size() > 0)
                        outState.putParcelable("workOutExercises", Parcels.wrap(workOutExersices.get(0)));

        }


        @Override
        public void onPause() {
                super.onPause();
                WorkOutExercisesSingleton.setWorkOutExercisesSingleton(workOutExersices);
        }

        private class AsyncFilter extends AsyncTask<List<ExerciseSetRep>, Void, Void> {

                @Override
                //protected Void doInBackground(List<String>... params) {
                //May need to change both of the list too list<ExerciseSetRep for simplicity
                protected Void doInBackground(List<ExerciseSetRep>... params) {
                        // Params 0 will be the exercise list
                        // Params 1 will be the workout list
                        //ArrayList<String> a =  (ArrayList<String>) params[0];
                        params[0].removeAll(params[1]);

                        return null;
                }
        }


        public void setWorkoutListExerciseSetRep(Bundle bundle, String key) {
                setWorkoutListExerciseSetRep((ExerciseSetRep) Parcels.unwrap(bundle.getParcelable(key)));
        }

        public void setWorkoutListExerciseSetRep(ExerciseSetRep exerciseSetRep) {
                for (int i = 0; i < workOutexersicesAdapter.getItemList().size(); i++) {
                        if (exerciseSetRep.equals(workOutexersicesAdapter.getItemList().get(i))) {
                                workOutexersicesAdapter.getItemList().set(i, exerciseSetRep);
                        }
                }
                workOutexersicesAdapter.notifyDataSetChanged();

        }

        public void setWorkoutListFromWorkoutName(String workoutName) {
                RealmDB realmDB = new RealmDB();

                WorkoutTemplate workoutTemplate = realmDB.getWorkoutTemplateFromName(workoutName);

                workOutexersicesAdapter.getItemList().clear();

                for (ExerciseSetRep exerciseSetRep : workoutTemplate.getExerciseSetRep())
                        workOutexersicesAdapter.getItemList().add(exerciseSetRep);

                new AsyncFilter().execute(allExersice, workOutExersices);
                workOutexersicesAdapter.notifyDataSetChanged();


        }


}
