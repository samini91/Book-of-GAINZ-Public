package com.example.sadiq.test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Sadiq on 2/10/2016.
 */
public class workOutListView extends TextView {
    private View mValue;
    private ImageView mImage;

    public workOutListView(Context context,AttributeSet attrs){
        super(context,attrs);

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.workOutListView, 0, 0);
    String titleText = a.getString(R.styleable.workOutListView_titleText);


    a.recycle();


    setGravity(Gravity.CENTER_VERTICAL);
        ArrayList stringList= new ArrayList<String>();
        stringList.add("youdidit");
        setBackgroundColor(Color.BLACK);
        setEnabled(false);
        setRight(Color.BLUE);
        setWidth(22);
        setText("asdfasdfasdfasdf");

    //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    //inflater.inflate(R.layout.workoutlistview, this, true);
        /*
    TextView title = (TextView) getChildAt(0);
    title.setText(titleText);

    mValue = getChildAt(1);
    mValue.setBackgroundColor(Color.BLACK);
    mImage = (ImageView) getChildAt(2);
}

    public workOutListView(Context context) {
        this(context, null);
    }

    public void setValueColor(int color) {
        mValue.setBackgroundColor(color);
    }

    public void setImageVisible(boolean visible) {
        mImage.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
    */}
}
