package com.example.sadiq.test;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sadiq.test.Database.ExerciseSetRep;
import com.example.sadiq.test.Database.SetRepWeightDBObject;
import com.example.sadiq.test.SetRepWeightConfigurationView.SetRepWeightConfigurationView;

import org.parceler.Parcels;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by Mugen on 7/13/2017.
 */

public class AddWorkoutSetRepWeightListFragment extends Fragment {

        IActivityDataFactory callback;

        public interface setSetRepWeight{
               void setSetRepWeight(WorkoutTemplate workoutTemplate);
        }

        WorkoutTemplate workoutTemplate;

        ExerciseSetRep exerciseSetRep;
        ViewGroup root;
        Button submitButton;
        SetRepWeightConfigurationView setRepWeightConfigurationView;
        ExerciseSetRep  exerciseSetRepFromAddWorkout;

        AddWorkoutSetRepWeightListFragment addWorkoutSetRepWeightListFragment = this;

        @Override
        public void onAttach(Context context){
                super.onAttach(context);
                try
                {
                        callback = (IActivityDataFactory) context;
                }
                catch(ClassCastException e)
                {
                        throw new ClassCastException(context.toString()
                                + " must implement setSetRepWeightListener");
                }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

                root = (ViewGroup) inflater.inflate(R.layout.addworkoutsetrepweightlist,root);

                submitButton = (Button) root.findViewById(R.id.SubmitButton);

                setRepWeightConfigurationView = (SetRepWeightConfigurationView) root.findViewById(R.id.setrepweightconfigurationview);

                setRepWeightConfigurationView.getAdapterList();


                if(getArguments()!=null)
                {
                        unpackageExerciseSetRepWeight(getArguments(),"ExerciseSetRep");

                }

                submitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                exerciseSetRepFromAddWorkout = Parcels.unwrap(getArguments().getParcelable("ExerciseSetRep"));

                                Bundle bundle = new Bundle();

                                //bundle.putParcelable("ExerciseSetRepConfig", Parcels.wrap(packageExerciseSetRepWeight(setRepWeightConfigurationView.getAdapterList())));
                                ExerciseSetRep exerciseSetRep = packageExerciseSetRepWeight(setRepWeightConfigurationView.getAdapterList());

                                exerciseSetRep.setExerciseName(exerciseSetRepFromAddWorkout.getExerciseName());

                                bundle.putParcelable("ExerciseSetRepConfig", Parcels.wrap(exerciseSetRep));

                                callback.ActivityDataFactory(addWorkoutSetRepWeightListFragment,"AddWorkoutFragment",0,bundle);
                                //callback.setSetRepWeight(workoutTempl1ate);
                                
                                //setRepWeightConfigurationView.getAdapterList();

                        }
                });

                return root;
        }

        @Override
        public void onResume() {
                super.onResume();
                //Bind the Exercisesetrep

        }

        public void setExerciseSetRepArgs(ExerciseSetRep exerciseSetRepArgs)
        {
                this.exerciseSetRep = exerciseSetRepArgs;
        }

        public ExerciseSetRep packageExerciseSetRepWeight(List<SetRepWeightDBObject> SetRepWeightDBObjectList)
        {
                ExerciseSetRep exerciseSetRep = new ExerciseSetRep();
                RealmList<SetRepWeightDBObject>  setRepWeightDBObjectRealmList = new RealmList<>();

                for (SetRepWeightDBObject s: SetRepWeightDBObjectList) {
                        SetRepWeightDBObject setRepWeightDBObject = new SetRepWeightDBObject();
                        setRepWeightDBObjectRealmList.add(setRepWeightDBObject);

                        setRepWeightDBObject.setSet(s.getSet());
                        setRepWeightDBObject.setRep(s.getRep());
                        setRepWeightDBObject.setWeight(s.getWeight());
                }
                exerciseSetRep.setSetRepWeightDBObjectRealmList(setRepWeightDBObjectRealmList);

                return exerciseSetRep;

        }

        public void unpackageExerciseSetRepWeight(Bundle bundle, String key)
        {
                unpackageExerciseSetRepWeight((ExerciseSetRep) Parcels.unwrap(bundle.getParcelable(key)));
        }

        public void unpackageExerciseSetRepWeight(ExerciseSetRep exerciseSetRep)
        {
                if(exerciseSetRep.getSetRepWeightDBObjectRealmList() != null)
                        setRepWeightConfigurationView.getAdapter().setAdapterList(exerciseSetRep.getSetRepWeightDBObjectRealmList());
        }

}
