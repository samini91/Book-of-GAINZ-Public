package com.example.sadiq.test.FilterableList;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Database.RealmString;
import com.example.sadiq.test.R;
import com.example.sadiq.test.WorkoutTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmList;

/**
 * Created by Mugen on 8/9/2017.
 */

public class FilterableWorkoutListFragment extends Fragment {


        ViewGroup root ;

        RecyclerView recyclerView;
        @Bind(R.id.FilterableListView)
        FilterableListView filterableListView ;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
                super.onCreate(savedInstanceState);


                root = (ViewGroup) inflater.inflate(R.layout.filterablelisttest,container,false);


                ButterKnife.bind(this,root);

//                FilterableListAdapter filterableListAdapter = new FilterableListAdapter<FilterableListViewHolder_Exercise,Exercise>(getActivity(),Exercise.class, FilterableListViewHolder_Exercise.class,R.layout.filterablelist_viewholder_exercise);
                FilterableListAdapter filterableListAdapter = new FilterableListAdapter<FilterableListViewHolder_Workout,WorkoutTemplate>(getActivity(),WorkoutTemplate.class, FilterableListViewHolder_Workout.class,R.layout.filterablelist_viewholder_workouttemplate);

                filterableListView.setAdapter(filterableListAdapter);


                Filters filters = new Filters(getActivity());

                filterableListView.setFilterView(filters);

                return root;
        }








}
