package com.example.sadiq.test.Options;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sadiq.test.R;

import java.util.List;

/**
 * Created by Mugen on 5/30/2017.
 */

public class Option_dynamic_values extends LinearLayout {
        View rootView;
        public Option_dynamic_values(Context context) {
                super(context);
                init(context);
        }

        public Option_dynamic_values(Context context, @Nullable AttributeSet attrs) {
                super(context, attrs);
                init(context);
        }

        public Option_dynamic_values(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init(context);
        }

        void init(Context context)
        {
                rootView = inflate(context, R.layout.option_option_dynamic_values, this);

        }


        public void setButtonList (Context context, List<String> list)
        {

                for (String  s : list)
                {
                        this.addOptionValue(context,s);
                }

        }
        public void addOptionValue(Context context,String s)
        {
                TextView myButton = new Button(context);

                myButton.setText(s);

                /*
                int resizeVal = width_dynamiclist / ((ViewGroup) option_dynamic_values.getChildAt(0)).getChildCount();

                for(int index = 0; index<((ViewGroup)option_dynamic_values.getChildAt(0)).getChildCount(); ++index) {
                        View nextChild = ((ViewGroup)option_dynamic_values.getChildAt(0)).getChildAt(index);

//                        nextChild.setLayoutParams();

                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, resizeVal);
                        nextChild.setLayoutParams(lp);

                }
*/
                //LinearLayout ll = (LinearLayout)findViewById(R.id.buttonlayout);
                //View ll = findViewById(R.id.option_option_dynamic_values);

                LinearLayout ls = (LinearLayout) findViewById(R.id.option_option_dynamic_values_linear_layout);

                //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(30*s.length()+50, LayoutParams.MATCH_PARENT);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

                ls.addView(myButton, lp);

        }

        public void removeOptionValue()
        {


        }


}
