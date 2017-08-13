package com.example.sadiq.test.FilterableList;

import android.view.View;
import android.widget.TextView;

import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by Mugen on 8/9/2017.
 */

public class FilterableListViewHolder_Exercise extends FilterableListViewHolder  {

        @Bind(R.id.filterablelist_viewholder_textview)
        TextView textView;

        public FilterableListViewHolder_Exercise(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
        }

        @Override
        public void bind(RealmResults realmResults, int position) {
                RealmResults<Exercise> realmResultsExercise = realmResults;

                textView.setText(realmResultsExercise.get(position).getName().toString());
        }
}
