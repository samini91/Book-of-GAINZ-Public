package com.example.sadiq.test.Exercise;

import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadiq.test.CustomDataTypes.BodyPartHolder;
import com.example.sadiq.test.CustomDataTypes.MuscleGroupList;
import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmQuery;
import io.realm.RealmResults;

//import android.support.v4.app.Fragment;

public class AddExercise extends Fragment {

        public static int initWithExerciseName = 1;

        RealmDB realmDB = new RealmDB();
        boolean[] primaryBodyPartsforNewExersice;

        @Bind(R.id.leftListView)
        MuscleGroupList primaryMuscleList;
        @Bind(R.id.rightListView)
        MuscleGroupList secondaryMuscleList;
        @Bind(R.id.nameofexersice)
        EditText nameOfExersice;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                ViewGroup root = (ViewGroup) inflater.inflate(R.layout.addexersice, container, false);

                ButterKnife.bind(this, root);

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


                Button submit = (Button) root.findViewById(R.id.submit);

                submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                if ((nameOfExersice.getText().length() != 0)) {
                                        BodyPartHolder[] primaryMovers = primaryMuscleList.getBodyPartsState();
                                        BodyPartHolder[] secondaryMovers = secondaryMuscleList.getBodyPartsState();

                                        realmDB.addorUpdateExersice(nameOfExersice.getText().toString(), primaryMovers, secondaryMovers);
                                        primaryMuscleList.clear();
                                        secondaryMuscleList.clear();
                                } else {
                                        Toast.makeText(getActivity(), "Enter the name of the Exercise", Toast.LENGTH_SHORT).show();

                                }
                        }
                });


                return root;

        }

        @Override
        public void onResume() {
                super.onResume();
                if (getTargetRequestCode() == initWithExerciseName) {
                        String exerciseName = getArguments().getString("name");


                        RealmQuery<Exercise> exerciseRealmQuery = RealmQuery.createQuery(realmDB.getRealm(), Exercise.class);
                        exerciseRealmQuery.equalTo("name", exerciseName);
                        RealmResults<Exercise> exerciseRealmResults = realmDB.getWhereAllExercises(exerciseRealmQuery);


                        if (!exerciseRealmResults.isEmpty()) {
                                Exercise exercise = exerciseRealmResults.first();

                                nameOfExersice.setText(exercise.getName());

                                primaryMuscleList.initBodyPartsState(exercise.getPrimaryMoversDBObject());
                                secondaryMuscleList.initBodyPartsState(exercise.getSecondaryMoversDBObject());

                        }

                }
        }
}

