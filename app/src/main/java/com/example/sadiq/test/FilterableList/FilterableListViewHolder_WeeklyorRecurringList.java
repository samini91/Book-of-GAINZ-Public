package com.example.sadiq.test.FilterableList;

import android.view.View;
import android.widget.TextView;

import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Database.WeeklyorRecurringListDB;
import com.example.sadiq.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * Created by Mugen on 8/18/2017.
 */

public class FilterableListViewHolder_WeeklyorRecurringList extends FilterableListViewHolder{


        @Bind(R.id.filterablelist_viewholder_textview)
        TextView textView;

        public FilterableListViewHolder_WeeklyorRecurringList(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);

                itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                if(filterableListAdapterOnViewClick != null)
                                        filterableListAdapterOnViewClick.FilterableListAdapterOnViewClick(getAdapterPosition());
                        }
                });
        }

        @Override
        public void bind(RealmResults realmResults, int position) {
                RealmResults<WeeklyorRecurringListDB> realmResultsExercise = realmResults;

                textView.setText(realmResultsExercise.get(position).getName());


        }

}
