package com.example.sadiq.test.WeeklyorRecurringList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.sadiq.test.Database.WeeklyorRecurringDayDB;
import com.example.sadiq.test.IActivityDataFactory;
import com.example.sadiq.test.R;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmList;

/**
 * Created by Mugen on 8/12/2017.
 */

public class WeeklyorRecurringListFragment extends Fragment {


        ViewGroup root;

        @Bind(R.id.RecyclerView)
        RecyclerView recyclerView;
        //@Bind(R.id.)
        //Spinner addScheduledWorkoutInstanceSpinner;
        @Bind (R.id.addinstances)
        ImageButton addInstances;

        IActivityDataFactory callback;


        @Override
        public void onAttach(Context context){
                super.onAttach(context);
                try
                {
                        callback = (IActivityDataFactory) getActivity();
                }
                catch(ClassCastException e)
                {
                        throw new ClassCastException(getActivity().toString()
                                + " must implement setSetRepWeightListener");
                }
        }


        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
                super.onCreateView(inflater, container, savedInstanceState);

                root = (ViewGroup) inflater.inflate(R.layout.weeklyorrecurringlist,root,false);

                ButterKnife.bind(this,root);

                final RealmList<WeeklyorRecurringDayDB> weeklyorRecurringDayDBs = new RealmList<>();


                //ArrayList<String> spinnerAddValueAmount = new ArrayList<>();

                //spinnerAddValueAmount.add("1");
                //spinnerAddValueAmount.add("7");


                //ArrayAdapter<String> spinnerAdapter  = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, spinnerAddValueAmount);


                final WeeklyorRecurringListAdapter weeklyorRecurringListAdapter = new WeeklyorRecurringListAdapter(weeklyorRecurringDayDBs);
                weeklyorRecurringListAdapter.setCustomListner(new WeeklyorRecurringListAdapter.CustomListener() {
                        @Override
                        public void onWorkoutDayPlusButton(int order, int location) {

                                Bundle bundle = new Bundle();
                                bundle.putInt("order",order);
                                bundle.putInt("location",location);
                                ((IActivityDataFactory)getActivity()).ActivityDataFactory(WeeklyorRecurringListFragment.class.toString(),"FilterableWorkoutListFragment",bundle);
                        }
                });


                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(weeklyorRecurringListAdapter);


                addInstances.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                PopupMenu popupMenu = new PopupMenu(getActivity(),addInstances);

                                popupMenu.getMenuInflater().inflate(R.menu.addinstancesval,popupMenu.getMenu());

                                popupMenu.show();

                                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                                        @Override
                                        public boolean onMenuItemClick(MenuItem item) {

                                                try {
                                                        for (int i = 0 ; i < Integer.parseInt(item.getTitle().toString()); i++)
                                                                weeklyorRecurringDayDBs.add(new WeeklyorRecurringDayDB(weeklyorRecurringDayDBs.size()));
                                                }
                                                catch (Exception e ){
                                                        e.printStackTrace();
                                                        return false;
                                                }

                                                weeklyorRecurringListAdapter.notifyDataSetChanged();

                                                return true;
                                        }
                                });
                        }
                });

                return root;

        }



}
