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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sadiq.test.R;


/**
 * Created by Sadiq on 3/13/2016.
 */
public class Counter extends RelativeLayout {

    private int minValue = 0;

    private int maxValue = 30;

    private boolean plusButtonIsPressed = false;
    private boolean minusButtonIsPressed = false;
    private final long REPEAT_INTERVAL_MS = 1001;

    View rootView;
    TextView valueTextView;
    View minusButton;
    View plusButton;

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

    public int getMinValue() {
        return minValue;
    }

    public int getValue() {
        return Integer.valueOf(valueTextView.getText().toString());
    }

    public void setValue(int val){
        valueTextView.setText(Integer.toString(val));

    }

    private void init(Context context) {
        rootView = inflate(context, R.layout.numcounter, this);
        valueTextView = (TextView) rootView.findViewById(R.id.valueTextView);
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

    private void incrementValue() {
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


    //probably need runnables
    public void onMinusPressOverload(){}

    public void onPlusPressOverload(){}


}
