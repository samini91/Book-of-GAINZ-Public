package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.example.sadiq.test.Database.SetRepWeightDBObject;

/**
 * Created by Sadiq on 4/4/2016.
 */
public class RepCounter extends Counter {
    SetRepWeightDBObject setRepWeightDBObject;
    public RepCounter(Context context) {
        super(context);
        init();
    }

    public RepCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RepCounter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void updateSetRepWeightDBObject(SetRepWeightDBObject setRepWeightDBObject){
        this.setRepWeightDBObject=setRepWeightDBObject;

            }


    private void init(){



    }
    @Override
    public void onMinusPressOverload(){

        setRepWeightDBObject.setRep((int)getValue());
    }

    @Override
    public void onPlusPressOverload(){
        setRepWeightDBObject.setRep((int)getValue());
    }
}
