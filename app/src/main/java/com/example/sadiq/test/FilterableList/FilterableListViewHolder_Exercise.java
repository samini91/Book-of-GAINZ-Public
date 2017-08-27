package com.example.sadiq.test.FilterableList;

import android.view.View;
import android.widget.TextView;

import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Exercise.PrimaryAndSecondaryMovers;
import com.example.sadiq.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by Mugen on 8/9/2017.
 */

public class FilterableListViewHolder_Exercise extends FilterableListViewHolder {

        @Bind(R.id.filterablelist_viewholder_textview)
        TextView textView;

        @Bind(R.id.PrimaryAndSecondaryMovers)
        PrimaryAndSecondaryMovers primaryAndSecondaryMovers;

        public FilterableListViewHolder_Exercise(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);

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
                RealmResults<Exercise> realmResultsExercise = realmResults;

                textView.setText(realmResultsExercise.get(position).getName());

                primaryAndSecondaryMovers.bind(realmResultsExercise.get(position).getName());


        }
}
