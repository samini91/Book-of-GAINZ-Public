package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sadiq.test.R;


/**
 * Created by Sadiq on 3/13/2016.
 */
public class Counter extends RelativeLayout {


    public TextView num;
    public Button subtract;
    public Button add;
    public Context context;
    public Counter(Context context) {
        super(context);
        initilizeView(context);
    }

    public Counter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initilizeView(context);
    }

    public Counter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initilizeView(context);
    }

    private void initilizeView(Context context){
       // ViewGroup root = (ViewGroup)inflate(context,R.layout.numcounter,this);

    }

    public View onCreateView(){
        //ViewGroup root = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.mainmenu,this);
        ViewGroup root = (ViewGroup)inflate(context,R.layout.calandar,this);

        /*
        subtract= new Button(getContext());
        num = new TextView(getContext());
        add = new Button(getContext());
        num.setText(0);

        addView(subtract);
        addView(num);
        addView(add);
*/
        return root;
    }


    @Override
    public void onFinishInflate(){
        super.onFinishInflate();

        subtract= new Button(getContext());
        num = new TextView(getContext());
        add = new Button(getContext());
        num.setText(0);

        addView(subtract);
        addView(num);
        addView(add);
    }


}
