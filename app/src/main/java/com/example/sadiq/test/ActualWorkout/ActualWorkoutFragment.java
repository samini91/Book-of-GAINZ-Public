package com.example.sadiq.test.ActualWorkout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadiq.test.R;
import com.example.sadiq.test.ViewPagerFragment;

/**
 * Created by Mugen on 8/1/2017.
 */

public class ActualWorkoutFragment extends ViewPagerFragment {

        ViewGroup root;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

                root = (ViewGroup) inflater.inflate(R.layout.addexersice, container, false);

                return root;
        }


}
