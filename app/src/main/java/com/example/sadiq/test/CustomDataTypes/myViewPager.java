package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.HashMap;


public class myViewPager extends ViewPager {
        public HashMap<String, Integer> framgmentPositionInMyViewPager;

        public myViewPager(Context context) {
                super(context);
        }

        public myViewPager(Context context, AttributeSet attrs) {
                super(context, attrs);
        }

}
