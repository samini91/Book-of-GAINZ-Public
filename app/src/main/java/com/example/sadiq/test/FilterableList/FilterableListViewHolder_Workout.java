package com.example.sadiq.test.FilterableList;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.sadiq.test.AddWorkout.WorkoutExerciseSetRepTableView;
import com.example.sadiq.test.Database.ExerciseSetRep;
import com.example.sadiq.test.R;
import com.example.sadiq.test.Database.WorkoutTemplate;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmList;
import io.realm.RealmResults;

public class FilterableListViewHolder_Workout extends FilterableListViewHolder {



        @Bind(R.id.filterablelist_viewholder_textview)
        TextView textView;

        @Bind(R.id.ExerciseSetRepTable)
        WorkoutExerciseSetRepTableView exerciseSetRepTable;

        Context context;

        public FilterableListViewHolder_Workout(final View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                context = itemView.getContext();

                itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                if (filterableListAdapterOnViewClick != null)
                                        filterableListAdapterOnViewClick.FilterableListAdapterOnViewClick(getAdapterPosition());
                        }
                });

        }

        @Override
        public void bind(RealmResults realmResults, int position) {
                RealmResults<WorkoutTemplate> realmResultsWorkoutTemplate = realmResults;
                RealmList<ExerciseSetRep> setRepRealmList = realmResultsWorkoutTemplate.get(position).getExerciseSetRep();

                exerciseSetRepTable.bind(realmResultsWorkoutTemplate.get(position));

                textView.setText(realmResultsWorkoutTemplate.get(position).getName().toString());
        }


}



