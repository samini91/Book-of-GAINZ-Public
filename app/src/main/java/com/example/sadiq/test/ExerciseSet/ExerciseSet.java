package com.example.sadiq.test.ExerciseSet;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.view.View;

import com.example.sadiq.test.R;

/**
 * Created by Mugen on 6/14/2017.
 */

public class ExerciseSet extends Fragment {

        @Override

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {

                final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.exersiceset_main, container, false);


                return root;
        }

        @Override
        public void onCreate(Bundle bundle){
                super.onCreate(bundle);
                setHasOptionsMenu(true);

        }

}
