package com.example.sadiq.test.SelectExerciseConfiguration;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sadiq.test.R;

/**
 * Created by Sadiq on 3/16/2016.
 */
public class SelectExerciseConfiguration extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){
        //Inflate the View
        ViewGroup root;
        root = (ViewGroup)inflater.inflate(R.layout.selectexerciseconfigurationmain,container,false);
        TextView test = new TextView(getActivity());
        test.setText("asdkfjal;sdjkfaskdjf;aklsjdf;k");
        root.addView(test);

        root.setBackgroundColor(Color.CYAN);

        return root;

    }
    @Override
    public String toString(){

        return "swagerdagerdo";
    }

}
