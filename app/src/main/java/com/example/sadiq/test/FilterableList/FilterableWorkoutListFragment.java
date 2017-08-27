package com.example.sadiq.test.FilterableList;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.sadiq.test.AddWorkout.AddWorkoutFragment;
import com.example.sadiq.test.IActivityDataFactory;
import com.example.sadiq.test.R;
import com.example.sadiq.test.Database.WorkoutTemplate;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mugen on 8/9/2017.
 */

public class FilterableWorkoutListFragment extends DialogFragment {

        public static int out_workOutInstance = 1;


        ViewGroup root;

        RecyclerView recyclerView;
        @Bind(R.id.FilterableListView)
        FilterableListView filterableListView;

        @Bind(R.id.addWorkoutFAB)
        FloatingActionButton addWorkoutFAB;

        FilterableWorkoutListFragment filterableWorkoutListFragment = this;


        int order;
        int index;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
                final Dialog dialog = super.onCreateDialog(savedInstanceState);

                dialog.getWindow().getAttributes().windowAnimations = R.style.AppTheme;

                return dialog;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                root = (ViewGroup) inflater.inflate(R.layout.filterablelistworkout, container, false);

                ButterKnife.bind(this, root);

                if (getArguments() != null) {
                        order = getArguments().getInt("order");
                        index = getArguments().getInt("index");
                }

//                FilterableListAdapter filterableListAdapter = new FilterableListAdapter<FilterableListViewHolder_Exercise,Exercise>(getActivity(),Exercise.class, FilterableListViewHolder_Exercise.class,R.layout.filterablelist_viewholder_exercise);
                final FilterableListAdapter filterableListAdapter = new FilterableListAdapter<FilterableListViewHolder_Workout, WorkoutTemplate>(getActivity(), WorkoutTemplate.class, FilterableListViewHolder_Workout.class, R.layout.filterablelist_viewholder_workouttemplate);

                filterableListView.setAdapter(filterableListAdapter);

                FiltersWorkout filtersWorkout = new FiltersWorkout(getActivity());

                filterableListView.setFilterView(filtersWorkout);


                if (getTargetRequestCode() == 0) {
                        filterableListAdapter.setFilterableListAdapterOnViewClick(new FilterableListAdapter.FilterableListAdapterOnViewClick() {
                                @Override
                                public void FilterableListAdapterOnViewClick(int position) {

                                        WorkoutTemplate workoutTemplate = (WorkoutTemplate) filterableListAdapter.realmResults.get(position);

                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("workoutTemplate", Parcels.wrap(workoutTemplate));
                                        bundle.putInt("index", index);
                                        bundle.putInt("order", order);

                                        Toast.makeText(getActivity(), filterableWorkoutListFragment.getParentFragment().getTag(), Toast.LENGTH_SHORT).show();

                                        ((IActivityDataFactory) filterableWorkoutListFragment.getActivity()).ActivityDataFactory(filterableWorkoutListFragment, "AddWorkoutFragment", AddWorkoutFragment.in_WorkoutName, IActivityDataFactory.newInstance, bundle);

                                }
                        });

                } else if (getTargetRequestCode() == 1) {
                        filterableListAdapter.setFilterableListAdapterOnViewClick(new FilterableListAdapter.FilterableListAdapterOnViewClick() {
                                @Override
                                public void FilterableListAdapterOnViewClick(int position) {

                                        WorkoutTemplate workoutTemplate = (WorkoutTemplate) filterableListAdapter.realmResults.get(position);

                                        Bundle bundle = new Bundle();
                                        bundle.putParcelable("workoutTemplate", Parcels.wrap(workoutTemplate));
                                        bundle.putInt("index", index);
                                        bundle.putInt("order", order);

                                        Toast.makeText(getActivity(), filterableWorkoutListFragment.getParentFragment().getTag(), Toast.LENGTH_SHORT).show();


                                        ((IActivityDataFactory) filterableWorkoutListFragment.getActivity()).ActivityDataFactory(filterableWorkoutListFragment, "WeeklyorRecurringListFragment", 0, IActivityDataFactory.detail, bundle);

                                        filterableWorkoutListFragment.dismiss();
                                }
                        });
                }


                addWorkoutFAB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                ((IActivityDataFactory) filterableWorkoutListFragment.getActivity()).ActivityDataFactory(filterableWorkoutListFragment, "AddWorkoutFragment", 0, IActivityDataFactory.newInstance, new Bundle());
                        }
                });


                return root;
        }


        @Override
        public void onResume() {
                super.onResume();
                if (getTargetRequestCode() == 1 || getDialog() != null) {
                        getDialog().getWindow().setLayout(getResources().getDisplayMetrics().widthPixels * 4 / 5, getResources().getDisplayMetrics().heightPixels * 6 / 7

                        );

                        WindowManager.LayoutParams lp = getDialog().getWindow().getAttributes();
                        lp.gravity = Gravity.BOTTOM | Gravity.RIGHT;
                        //lp.x = 1000;
                        //lp.y = 1000;

                        getDialog().getWindow().setAttributes(lp);

                }
        }

        private void getTargetRequestCodeTransformation() {
        }
}
