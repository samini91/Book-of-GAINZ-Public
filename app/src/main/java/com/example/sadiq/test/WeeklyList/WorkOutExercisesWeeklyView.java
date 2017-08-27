package com.example.sadiq.test.WeeklyList;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * TextView with Id
 */
public class WorkOutExercisesWeeklyView extends LinearLayout {

        public long dayOftheWeekId;


        public WorkOutExercisesWeeklyView(Context context) {
                super(context);
        }

        public WorkOutExercisesWeeklyView(Context context, AttributeSet attrs) {
                super(context, attrs);
        }

        public WorkOutExercisesWeeklyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
        }

        public void populateLayoutWithExersices() {

        }


}
