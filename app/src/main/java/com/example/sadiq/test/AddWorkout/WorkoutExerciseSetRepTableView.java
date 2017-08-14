package com.example.sadiq.test.AddWorkout;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.sadiq.test.Database.ExerciseSetRep;
import com.example.sadiq.test.ExerciseSetRep.ExerciseSetRepView;
import com.example.sadiq.test.WorkoutTemplate;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Mugen on 8/14/2017.
 */

public class WorkoutExerciseSetRepTableView extends LinearLayout {
        public WorkoutExerciseSetRepTableView(Context context) {
                super(context);
                init(context);
        }

        public WorkoutExerciseSetRepTableView(Context context, @Nullable AttributeSet attrs) {
                super(context, attrs);
                init(context);
        }

        public WorkoutExerciseSetRepTableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init(context);
        }


        private void init(Context context){

        }

        public void bind( WorkoutTemplate workOutTemplate){
                RealmList<ExerciseSetRep> setRepRealmList = workOutTemplate.getExerciseSetRep();

                this.removeAllViews();

                for(ExerciseSetRep exerciseSetRep : setRepRealmList) {

                        ExerciseSetRepView exerciseSetRepView = new ExerciseSetRepView(this.getContext());

                        exerciseSetRepView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT));

                        exerciseSetRepView.Bindexercisesetrep(exerciseSetRep);

                        this.addView(exerciseSetRepView);


                }




        }




}
