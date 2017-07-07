package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sadiq.test.R;

import java.util.HashMap;


/**
 * Created by Sadiq on 3/13/2016.
 */
public class Counter extends RelativeLayout {

    private int minValue = 0;

    private int maxValue = 30;

    private boolean plusButtonIsPressed = false;
    private boolean minusButtonIsPressed = false;
    private final long REPEAT_INTERVAL_MS = 150;
    protected float counterValue=0;
    private HashMap<Float,String> valueChanger;
    private float crementValue=1;




    View rootView;
   // TextView valueTextView;
   EditText valueTextView;
    View minusButton;
    View plusButton;
    TextView valueLabel;

    Handler handler = new Handler();



    public Context context;




    public Counter(Context context) {
        super(context);
        init(context);
    }

    public Counter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);


    }

    public Counter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }



    public void setCrementValue(float value){
        crementValue=value;
    }

    public void setMin(int min){
        this.minValue = min;

        for(int i = 0 ;i <min;i++){
            incrementValue();
        }

    }
    public int getMinValue() {
        return minValue;
    }
    public int getMaxValue(){
        return maxValue;
    }
    public void setMax(int max){
        this.maxValue=max;
        update();
    }

    public float getValue() {
        //return Integer.valueOf(valueTextView.getText().toString());
        return counterValue;
    }

    public void setValue(int val){
        counterValue=val;
        update();

        if(valueChanger.containsValue(val)){


        }
        //valueTextView.setText(Integer.toString(val));

    }


    public void addNewValueChanger(float val, String changeTo){
        valueChanger.put(val, changeTo);
    }

    public void setLabel(String label){valueLabel.setText(label);}
    public String getLabel(){return valueLabel.getText().toString();}

    private void init(Context context) {

        rootView = inflate(context, R.layout.numcounter, this);
        //rootView = inflate(context, R.layout.numcounter_revamp, this);
        //valueTextView = (TextView) rootView.findViewById(R.id.valueTextView);
        valueTextView = (EditText) rootView.findViewById(R.id.valueTextView);
        valueLabel = (TextView) rootView.findViewById(R.id.valueLabel);

        valueChanger=new HashMap<>();

        valueTextView.setInputType(InputType.TYPE_NULL);


        minusButton = rootView.findViewById(R.id.minusButton);
        plusButton = rootView.findViewById(R.id.plusButton);

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementValue();
                onMinusPressOverload();
            }
        });
        minusButton.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View arg0) {
                        minusButtonIsPressed = true;
                        handler.post(new AutoDecrementer());
                        return false;
                    }
                }
        );

        minusButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)) {
                    minusButtonIsPressed = false;
                }
                return false;
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementValue();
                onPlusPressOverload();
            }
        });
        plusButton.setOnLongClickListener(
                new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View arg0) {
                        plusButtonIsPressed = true;
                        handler.post(new AutoIncrementer());
                        return false;
                    }
                }
        );

        plusButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if ((event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL)) {
                    plusButtonIsPressed = false;
                }
                return false;
            }
        });


    }

    public void addTextChangeListener(TextWatcher textWatcher){

        valueTextView.addTextChangedListener(textWatcher);

    }

    private void incrementValue(){
        if(counterValue < maxValue){
            //counterValue++;
            counterValue += crementValue;
            update();
        }

    }

    private void decrementValue(){
        if(counterValue > minValue){
            counterValue = counterValue-crementValue >=minValue ? counterValue-crementValue : minValue;

            update();
        }
    }

    //probably faster in a try catch block but O(1) *2 so no problem

    //it can either be a float or a integer
    public void update(){
        //System.out.println(counterValue+"  as;dfjl;asdkfj  " + valueChanger.containsKey(counterValue)+"   "+getMaxValue());
        if(valueChanger.containsKey(counterValue)){
            valueTextView.setText(valueChanger.get(counterValue));

        }
        else {//valueTextView.setText(Float.toString(counterValue));

                valueTextView.setText( counterValue%1>0?Float.toString((float)(Math.round(counterValue*100.0)/100.0)) :Integer.toString((int)counterValue));

        }
        /*
        else {

            if (Integer.class.isInstance(counterValue)) {
                // Use if statement for
                valueTextView.setText(Integer.toString(counterValue));
            }
            //it is a float
            else {
                //this means that it is not a whole number
               if(counterValue%1!=0){
                   valueTextView.setText(Float.toString(counterValue));
               }
                else{
                   valueTextView.setText(Float.toString((int)counterValue));
               }
            }
        }
        */
    }
    //make it gud
    /*private void incrementValue() {
        int currentVal = Integer.valueOf(valueTextView.getText().toString());
        if(currentVal < maxValue) {
            valueTextView.setText(String.valueOf(currentVal + 1));
        }
    }

    private void decrementValue() {
        int currentVal = Integer.valueOf(valueTextView.getText().toString());
        if(currentVal > minValue) {
            valueTextView.setText(String.valueOf(currentVal - 1));
        }
    }
*/



    private class AutoIncrementer implements Runnable {
        @Override
        public void run() {
            if(plusButtonIsPressed){
                incrementValue();
                onPlusPressOverload();
                handler.postDelayed( new AutoIncrementer(), REPEAT_INTERVAL_MS);
            }
        }
    }
    private class AutoDecrementer implements Runnable {
        @Override
        public void run() {
            if (minusButtonIsPressed) {
                decrementValue();
                onMinusPressOverload();
                handler.postDelayed(new AutoDecrementer(), REPEAT_INTERVAL_MS);
            }
        }
    }



    public void onMinusPressOverload(){}

    public void onPlusPressOverload(){}


}
