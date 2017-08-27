package com.example.sadiq.test.PopUpWindow;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Sadiq on 2/18/2016.
 */
public class PopUpListView extends ListView {
        ArrayAdapter<String> adapter;

        public PopUpListView(Context activityContext) {
                super(activityContext);

        }
        public PopUpListView(Context context, AttributeSet attrs, int defStyle) {
                super(context, attrs, defStyle);
        }

        public PopUpListView(Context context, AttributeSet attrs) {
                super(context, attrs);
        }

}
