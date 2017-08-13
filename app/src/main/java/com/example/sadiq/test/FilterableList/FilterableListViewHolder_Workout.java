package com.example.sadiq.test.FilterableList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Database.ExerciseSetRep;
import com.example.sadiq.test.ExerciseSetRep.ExerciseSetRepView;
import com.example.sadiq.test.R;
import com.example.sadiq.test.WorkoutTemplate;

import java.util.Iterator;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Mugen on 8/11/2017.
 */



//Will contain the workout name but clicking on it will launch the other window to configure it

public class FilterableListViewHolder_Workout extends FilterableListViewHolder{

        @Bind(R.id.filterablelist_viewholder_textview)
        TextView textView;

        @Bind(R.id.ExerciseSetRepTable)
        LinearLayout exerciseSetRepTable;

        Context context;

        public FilterableListViewHolder_Workout(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                context = itemView.getContext();
        }

        @Override
        public void bind(RealmResults realmResults, int position) {
                RealmResults<WorkoutTemplate> realmResultsWorkoutTemplate = realmResults;
                RealmList<ExerciseSetRep> setRepRealmList = realmResultsWorkoutTemplate.get(position).getExerciseSetRep();

                exerciseSetRepTable.removeAllViews();
                for(ExerciseSetRep exerciseSetRep : setRepRealmList) {

                        ExerciseSetRepView exerciseSetRepView = new ExerciseSetRepView(context);

                        exerciseSetRepView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT));

                        exerciseSetRepView.Bindexercisesetrep(exerciseSetRep);

                        exerciseSetRepTable.addView(exerciseSetRepView);

                        //break;
                }
                textView.setText(realmResultsWorkoutTemplate.get(position).getName().toString());
        }


}



