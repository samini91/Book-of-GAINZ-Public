package com.example.sadiq.test.FilterableList;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sadiq.test.Database.WeeklyorRecurringListDB;
import com.example.sadiq.test.IActivityDataFactory;
import com.example.sadiq.test.R;
import com.example.sadiq.test.WeeklyorRecurringList.WeeklyorRecurringListFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mugen on 8/18/2017.
 */

public class FilterableWeeklyorRecurringScheduleListFragment extends DialogFragment {


        ViewGroup root;

        @Bind(R.id.FilterableListView)
        FilterableListView filterableListView ;
        FilterableWeeklyorRecurringScheduleListFragment filterableWeeklyorRecurringScheduleListFragment = this;

        FilterableListAdapter filterableListAdapter;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
                super.onCreateView(inflater, container, savedInstanceState);

                root = (ViewGroup) inflater.inflate(R.layout.filterablelistweekklyorreccuringlist,container,false);

                ButterKnife.bind(this,root);

                FiltersWeeklyorRecurringList filtersWeeklyorRecurringList = new FiltersWeeklyorRecurringList(getActivity());

                filterableListAdapter = new FilterableListAdapter<FilterableListViewHolder_WeeklyorRecurringList,WeeklyorRecurringListDB>(getActivity(),WeeklyorRecurringListDB.class, FilterableListViewHolder_WeeklyorRecurringList.class,R.layout.filterablelist_viewholder_weeklyorreccuringlist);

                filterableListAdapter.setFilterableListAdapterOnViewClick(new FilterableListAdapter.FilterableListAdapterOnViewClick() {
                        @Override
                        public void FilterableListAdapterOnViewClick(int position) {
                                Bundle bundle = new Bundle();

                                bundle.putString("name", ((WeeklyorRecurringListDB)filterableListAdapter.getRealmResultList().get(position)).getName());

                                ((IActivityDataFactory)getActivity()).ActivityDataFactory(filterableWeeklyorRecurringScheduleListFragment,"WeeklyorRecurringListFragment", WeeklyorRecurringListFragment.fromFilterableWeeklyorRecurringList,bundle);
                                Toast.makeText(filterableWeeklyorRecurringScheduleListFragment.getActivity(),filterableWeeklyorRecurringScheduleListFragment.getParentFragment().getTag(),Toast.LENGTH_SHORT).show();
                        }
                });
                filterableListView.setFilterView(filtersWeeklyorRecurringList);
                filterableListView.setAdapter(filterableListAdapter);



                return root;
        }
}
