package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.util.AttributeSet;

import com.example.sadiq.test.Database.SetRepWeightDBObject;

/**
 * Created by Sadiq on 4/4/2016.
 */
public class RepCounter extends Counter {
    SetRepWeightDBObject setRepWeightDBObject;
    public RepCounter(Context context) {
        super(context);
    }

    public RepCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RepCounter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void updateSetRepWeightDBObject(SetRepWeightDBObject setRepWeightDBObject){
        this.setRepWeightDBObject=setRepWeightDBObject;
            }
    @Override
    public void onMinusPressOverload(){
        System.out.println("as;ldkf;alskj");
        setRepWeightDBObject.setRep(getValue());
    }

    @Override
    public void onPlusPressOverload(){
        setRepWeightDBObject.setRep(getValue());
    }
}
