package com.example.sadiq.test.WeeklyorRecurringList;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.sadiq.test.Database.WeeklyorRecurringDayDB;
import com.example.sadiq.test.R;

import java.lang.reflect.Array;
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

        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
                super.onCreateView(inflater, container, savedInstanceState);

                root = (ViewGroup) inflater.inflate(R.layout.weeklyorrecurringlist,root,false);

                ButterKnife.bind(this,root);

                RealmList<WeeklyorRecurringDayDB> weeklyorRecurringDayDBs = new RealmList<>();

                for(int i = 0; i<100 ;i++) {

                        WeeklyorRecurringDayDB weeklyorRecurringDayDB = new WeeklyorRecurringDayDB();
                        weeklyorRecurringDayDB.setOrder(i);

                        weeklyorRecurringDayDBs.add(weeklyorRecurringDayDB);
                }

                ArrayList<String> spinnerAddValueAmount = new ArrayList<>();

                spinnerAddValueAmount.add("1");
                spinnerAddValueAmount.add("7");


                ArrayAdapter<String> spinnerAdapter  = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, spinnerAddValueAmount);


                addInstances.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                PopupMenu popupMenu = new PopupMenu(getActivity(),addInstances);
                                popupMenu.getMenuInflater().inflate(R.menu.addinstancesval,popupMenu.getMenu());
                                //popupMenu.getMenu().add(0,0,0,"asdf");
                                popupMenu.show();

                        }
                });
          //      addScheduledWorkoutInstanceSpinner.setAdapter(spinnerAdapter);



                WeeklyorRecurringListAdapter weeklyorRecurringListAdapter = new WeeklyorRecurringListAdapter(weeklyorRecurringDayDBs);

                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setAdapter(weeklyorRecurringListAdapter);

                return root;

        }


        private void setUpSpinnter(){


//                addScheduledWorkoutInstanceSpinner.


        }

}
