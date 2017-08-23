package com.example.sadiq.test.FilterableList;

import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Exercise.AddExercise;
import com.example.sadiq.test.IActivityDataFactory;
import com.example.sadiq.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mugen on 8/17/2017.
 */





public class FilterableExerciseListFragment extends DialogFragment {


        ViewGroup root ;

        RecyclerView recyclerView;
        @Bind(R.id.FilterableListView)
        FilterableListView filterableListView ;
        @Bind(R.id.addExerciseFAB)
        FloatingActionButton addExerciseFAB;
        FilterableExerciseListFragment filterableExerciseListFragment = this;


        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
                super.onCreateView(inflater, container, savedInstanceState);


                root = (ViewGroup) inflater.inflate(R.layout.filterablelistexercise,container,false);
                ButterKnife.bind(this,root);

                FiltersExercise filtersExercise = new FiltersExercise(getActivity());

                final FilterableListAdapter filterableListAdapter = new FilterableListAdapter<FilterableListViewHolder_Exercise,Exercise>(getActivity(),Exercise.class, FilterableListViewHolder_Exercise.class,R.layout.filterablelist_viewholder_exercise);

                filterableListView.setFilterView(filtersExercise);
                filterableListView.setAdapter(filterableListAdapter);

                filterableListAdapter.setFilterableListAdapterOnViewClick(new FilterableListAdapter.FilterableListAdapterOnViewClick() {
                        @Override
                        public void FilterableListAdapterOnViewClick(int position) {
                                Exercise exercise = (Exercise) filterableListAdapter.realmResults.get(position);

                                Bundle bundle = new Bundle();

                                bundle.putString("name",exercise.getName());

                                ((IActivityDataFactory) filterableExerciseListFragment.getActivity()).ActivityDataFactory(filterableExerciseListFragment,"AddExercise", AddExercise.initWithExerciseName,IActivityDataFactory.newInstance, bundle);


                        }
                });


                addExerciseFAB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                ((IActivityDataFactory) filterableExerciseListFragment.getActivity()).ActivityDataFactory(filterableExerciseListFragment,"AddExercise", 0,IActivityDataFactory.newInstance, new Bundle());
                        }
                });

                //filterableListView.


                return root;

        }
}
