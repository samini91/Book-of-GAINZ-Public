package com.example.sadiq.test;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sadiq.test.SetRepWeightConfigurationView.SetRepWeightConfigurationView;

/**
 * Created by Mugen on 7/13/2017.
 */

public class addWorkoutSetRepWeightListFragment extends Fragment {

        setSetRepWeight callback;

        public interface setSetRepWeight{
                public void setSetRepWeight(WorkoutTemplate workoutTemplate);
        }

        WorkoutTemplate workoutTemplate;
        ViewGroup root;
        Button submitButton;
        SetRepWeightConfigurationView setRepWeightConfigurationView;

        @Override
        public void onAttach(Context context){
                super.onAttach(context);

                try{
                        callback = (setSetRepWeight) context;
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


                submitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                callback.setSetRepWeight(workoutTemplate);
                                
                                setRepWeightConfigurationView.getAdapterList();

                        }
                });


                return root;
        }



}
