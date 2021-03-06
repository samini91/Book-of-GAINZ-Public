package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import com.example.sadiq.test.Database.SetRepWeightDBObject;

public class WeightCounter extends Counter {
        SetRepWeightDBObject setRepWeightDBObject;

        public WeightCounter(Context context) {
                super(context);
                init();
        }

        public WeightCounter(Context context, AttributeSet attrs) {
                super(context, attrs);
                init();
        }

        public WeightCounter(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init();
        }

        public void updateSetRepWeightDBObject(SetRepWeightDBObject setRepWeightDBObject) {
                this.setRepWeightDBObject = setRepWeightDBObject;

        }

        //TODO 5/10 can make crementval an option
        private void init() {
                this.valueTextView.setClickable(true);
                this.valueTextView.setEnabled(true);
                this.valueTextView.setSelectAllOnFocus(true);
                this.valueTextView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                valueTextView.setOnKeyListener(new OnKeyListener() {
                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {
                                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                                        counterValue = Float.parseFloat(valueTextView.getText().toString());
                                        update();

                                        return true;

                                }

                                return false;
                        }
                });

                setCrementValue(5);
        }

        @Override
        public void onMinusPressOverload() {

                setRepWeightDBObject.setWeight((float) (Math.round(getValue() * 100.0) / 100.0));
        }

        @Override
        public void onPlusPressOverload() {
                setRepWeightDBObject.setWeight((float) (Math.round(getValue() * 100.0) / 100.0));

        }


}

