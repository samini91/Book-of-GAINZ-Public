package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.HashMap;

/**
 * Created by Sadiq on 2/17/2016.
 */
public class myViewPager extends ViewPager {
    public HashMap<String,Integer> framgmentPositionInMyViewPager;

    public myViewPager(Context context) {
        super(context);
    }

    public myViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }



    //type in fragment class to go to the last instance of the fragment


}
