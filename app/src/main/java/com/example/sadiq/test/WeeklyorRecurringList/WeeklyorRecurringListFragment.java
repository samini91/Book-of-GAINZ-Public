package com.example.sadiq.test.WeeklyorRecurringList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.Database.WeeklyorRecurringDayDB;
import com.example.sadiq.test.Database.WeeklyorRecurringListDB;
import com.example.sadiq.test.FilterableList.FilterableExerciseListFragment;
import com.example.sadiq.test.FilterableList.FilterableWorkoutListFragment;
import com.example.sadiq.test.IActivityDataFactory;
import com.example.sadiq.test.R;
import com.example.sadiq.test.WorkoutTemplate;

import org.parceler.Parcels;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Mugen on 8/12/2017.
 */

public class WeeklyorRecurringListFragment extends Fragment {


        public static int defaultInstance = 0;
        public static int fromFilterableWeeklyorRecurringList = 1;



        ViewGroup root;

        @Bind(R.id.RecyclerView)
        RecyclerView recyclerView;
        //@Bind(R.id.)
        //Spinner addScheduledWorkoutInstanceSpinner;
        @Bind (R.id.addinstances)
        ImageButton addInstances;

        @Bind(R.id.weeklyScheduleName)
        EditText weeklyScheduleName;

        @Bind(R.id.clear)
        Button clearButton;
        @Bind(R.id.submit)
        Button submitButton;

        IActivityDataFactory callback;

        Fragment thisFragment = this;

        WeeklyorRecurringListAdapter weeklyorRecurringListAdapter;
        RealmList<WeeklyorRecurringDayDB> weeklyorRecurringDayDBs;

        WeeklyorRecurringListFragment weeklyorRecurringListFragment = this;


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

                final RealmDB realmDB = new RealmDB();

                if(getTargetRequestCode() == defaultInstance)
                        weeklyorRecurringListAdapter = WeeklyorRecurringListSingleton.getWeeklyorRecurringListSingleton().getWeeklyorRecurringListAdapter();

                if(weeklyorRecurringListAdapter == null) {
                        weeklyorRecurringDayDBs = new RealmList<>();
                        weeklyorRecurringListAdapter = new WeeklyorRecurringListAdapter(weeklyorRecurringDayDBs);
                }

                weeklyorRecurringListAdapter.setCustomListener(new WeeklyorRecurringListAdapter.CustomListener() {
                                @Override
                                public void onWorkoutDayPlusButton( int index) {

                                        Bundle bundle = new Bundle();
                                        bundle.putInt("index", index);

                                        ((IActivityDataFactory)getActivity()).ActivityDataFactory(weeklyorRecurringListFragment,"FilterableWorkoutListFragment", FilterableWorkoutListFragment.out_workOutInstance,IActivityDataFactory.detail,bundle);
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
                                                                weeklyorRecurringListAdapter.weeklyorRecurringDayDBRealmList.add(new WeeklyorRecurringDayDB(weeklyorRecurringListAdapter.weeklyorRecurringDayDBRealmList.size()));
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



                submitButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                try {
                                        realmDB.getRealm().executeTransaction(new Realm.Transaction() {
                                                @Override
                                                public void execute(Realm realm) {
                                                        realmDB.addOrUpdateWeeklyorReccuringList(weeklyScheduleName.getText().toString(),weeklyorRecurringListAdapter.weeklyorRecurringDayDBRealmList);
                                                        Toast.makeText(getActivity(),"Schedule Created",Toast.LENGTH_SHORT).show();
                                                }
                                        });
                                }
                                catch (Exception e)
                                {
                                        e.printStackTrace();
                                        throw e;
                                }
                                finally {
                                        realmDB.getRealm().close();
                                }

                        }
                });


                final AlertDialog.Builder clearAlertDialog = new AlertDialog.Builder(getActivity());
                clearAlertDialog.setMessage("Are you sure you want to clear?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                weeklyorRecurringListAdapter.weeklyorRecurringDayDBRealmList.clear();
                                weeklyorRecurringListAdapter.notifyDataSetChanged();

                        }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                });

                clearButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                clearAlertDialog.show();
                        }
                });


                return root;

        }

        @Override
        public void onResume() {
                super.onResume();

                if(getTargetRequestCode() == WeeklyorRecurringListFragment.fromFilterableWeeklyorRecurringList)
                        selectWeeklyorRecurringList(getArguments());
        }

        @Override
        public void onPause() {
                super.onPause();
                if(getTargetRequestCode() == defaultInstance)
                        WeeklyorRecurringListSingleton.getWeeklyorRecurringListSingleton().setWeeklyorRecurringListAdapter(weeklyorRecurringListAdapter);
        }

        public void updateWeeklyorRecurringList(Bundle bundle){

                int index = bundle.getInt("index");
                int order = bundle.getInt("order");

                WorkoutTemplate workoutTemplate = Parcels.unwrap(bundle.getParcelable("workoutTemplate"));

                try {
                        RealmList<WorkoutTemplate> workoutTemplateRealmList = new RealmList<>();
                        workoutTemplateRealmList.add(workoutTemplate);
                        WeeklyorRecurringDayDB weeklyorRecurringDayDB = new WeeklyorRecurringDayDB();
                        weeklyorRecurringDayDB.setWorkoutTemplates(workoutTemplateRealmList);

                        weeklyorRecurringDayDB.setOrder(index);

                        weeklyorRecurringListAdapter.weeklyorRecurringDayDBRealmList.set(index, weeklyorRecurringDayDB);

                        weeklyorRecurringListAdapter.notifyDataSetChanged();
                }
                catch (NullPointerException e)
                {
                        e.printStackTrace();
                }
        }

        public void selectWeeklyorRecurringList(Bundle bundle){

                String name = bundle.getString("name");

                RealmDB realmDB = new RealmDB();

                WeeklyorRecurringListDB weeklyorRecurringListDB = realmDB.getWeeklyorRecurringListDB(name);


                weeklyorRecurringListAdapter.weeklyorRecurringDayDBRealmList = weeklyorRecurringListDB.getWeeklyorRecurringDayDB();

//                for(WeeklyorRecurringDayDB weeklyorRecurringDayDBs: weeklyorRecurringListDB.getWeeklyorRecurringDayDB()){
  //                      weeklyorRecurringListAdapter.weeklyorRecurringDayDBRealmList.add(weeklyorRecurringDayDBs);
    //            }
                //weeklyorRecurringListAdapter.weeklyorRecurringDayDBRealmList.

                weeklyorRecurringListAdapter.notifyDataSetChanged();

        }
}
