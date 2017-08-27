package com.example.sadiq.test;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadiq.test.AddWorkout.AddWorkoutFragment;
import com.example.sadiq.test.CalendarMenu.CalendarMenu;
import com.example.sadiq.test.CustomDataTypes.myViewPager;
import com.example.sadiq.test.Exercise.AddExercise;
import com.example.sadiq.test.FilterableList.FilterableExerciseListFragment;
import com.example.sadiq.test.FilterableList.FilterableWeeklyorRecurringScheduleListFragment;
import com.example.sadiq.test.FilterableList.FilterableWorkoutListFragment;
import com.example.sadiq.test.OneRepMax.OneRepMaxFragment;
import com.example.sadiq.test.Options.Options;
import com.example.sadiq.test.SetRepWeightConfigurationView.AddWorkoutSetRepWeightListFragment;
import com.example.sadiq.test.WeeklyorRecurringList.WeeklyorRecurringListFragment;

import java.util.LinkedList;

public class MainActivity extends Activity implements IActivityDataFactory {

        Context thisContext = this;
        Activity thisActivity = this;
        ViewGroup root;
        Bundle savedBundle;

        int currentInflatedViewLevel;

        int timer;

        myViewPager mPager;

        LinkedList<Pair<Class, String>> fragmentList;
        LinkedList<Class> fragmentListButton3;
        FragmentAdapterCreator FragmentAdapter;

        TextView textView;


        ViewPagerFragment calendar, WorkoutConfig, test3;
        long startTime = 0;

        Handler timerHandler = new Handler();
        Runnable timerRunnable = new Runnable() {

                @Override
                public void run() {
                        long millis = System.currentTimeMillis() - startTime;
                        int seconds = (int) (millis / 1000);
                        int minutes = seconds / 60;
                        seconds = seconds % 60;

                        textView.setText(String.format("%d:%02d", minutes, seconds));

                        timerHandler.postDelayed(this, 500);
                        //todo plate calculator
                }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {

                super.onCreate(savedInstanceState);
                savedBundle = savedInstanceState;
                init(savedInstanceState);
        }


        public void init(final Bundle savedBundle) {
                currentInflatedViewLevel = 0;
                setContentView(R.layout.mainmenutest);

                root = (ViewGroup) thisActivity.findViewById(R.id.mainlayout_root);

                Button button1 = (Button) findViewById(R.id.mainmenutest_button1);
                Button button2 = (Button) findViewById(R.id.mainmenutest_button2);
                Button button3 = (Button) findViewById(R.id.mainmenutest_button3);

                button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                currentInflatedViewLevel++;

                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                if (savedBundle != null) {
                                        WorkoutConfig = (ViewPagerFragment) getFragmentManager().findFragmentByTag("WorkoutConfig");
                                } else {

                                        WorkoutConfig = new ViewPagerFragment();

                                        final AddWorkoutSetRepWeightListFragment AddWorkoutSetRepWeightListFragment = new AddWorkoutSetRepWeightListFragment();

                                }

                                fragmentList = new LinkedList<Pair<Class, String>>();
                                fragmentList.add(new Pair<Class, String>(FilterableExerciseListFragment.class, "Exercises"));
                                fragmentList.add(new Pair<Class, String>(FilterableWorkoutListFragment.class, "Workouts"));
                                fragmentList.add(new Pair<Class, String>(FilterableWeeklyorRecurringScheduleListFragment.class, "Schedule"));

                                fragmentTransaction.replace(R.id.mainlayout_root, WorkoutConfig, "WorkoutConfig");
                                fragmentTransaction.addToBackStack("ViewPager1");
                                fragmentTransaction.commit();

                                fragmentManager.executePendingTransactions();
                                WorkoutConfig.setFragmentList(fragmentList);

                        }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(thisActivity, "worksstill", Toast.LENGTH_SHORT).show();

                                currentInflatedViewLevel++;

                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                calendar = new ViewPagerFragment();

                                fragmentTransaction.add(R.id.mainlayout_root, calendar, "calendar");

                                fragmentTransaction.addToBackStack("calendar");

                                fragmentTransaction.commit();
                                fragmentManager.executePendingTransactions();

                                fragmentList = new LinkedList<Pair<Class, String>>();
                                fragmentList.add(new Pair<Class, String>(OneRepMaxFragment.class, "One Rep Max Calculator"));
                                fragmentList.add(new Pair<Class, String>(CalendarMenu.class, "Calander"));

                                calendar.setFragmentList(fragmentList);
                        }
                });

                button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                currentInflatedViewLevel++;

                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                if (savedBundle != null) {
                                        test3 = (ViewPagerFragment) getFragmentManager().findFragmentByTag("WorkoutConfig3");
                                } else {

                                        test3 = new ViewPagerFragment();

                                        fragmentListButton3 = new LinkedList<>();

                                        fragmentListButton3.add(Options.class);

                                }

                                fragmentTransaction.replace(R.id.mainlayout_root, test3, "WorkoutConfig3");

                                fragmentTransaction.addToBackStack("ViewPager3");

                                fragmentListButton3.add(FilterableExerciseListFragment.class);

                                fragmentTransaction.commit();

                                fragmentManager.executePendingTransactions();

                        }
                });

                textView = (TextView) findViewById(R.id.textView);
                timer = 0;
                final long max = 1000000000;
                startTime = System.currentTimeMillis();
                timerHandler.postDelayed(timerRunnable, 0);

        }

        @Override
        public void onBackPressed() {

                //if (mPager.getCurrentItem() == 0) {
                // If the user is currently looking at the first step, allow the system to handle the
                // Back button. This calls finish() on this activity and pops the back stack.

                if (WorkoutConfig != null && WorkoutConfig.getViewPagerFragmentTransaction().getBackStackEntryCount() != 0)
                        WorkoutConfig.getViewPagerFragmentTransaction().popBackStack();
                else if (calendar != null && calendar.getViewPagerFragmentTransaction().getBackStackEntryCount() != 0)
                        calendar.getViewPagerFragmentTransaction().popBackStack();
                else if (this.getFragmentManager().getBackStackEntryCount() != 0) {
                        this.getFragmentManager().popBackStack();
                        init(savedBundle);
                } else
                        super.onBackPressed();

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.menu_main, menu);
                menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                                Toast.makeText(thisContext, "asdf", Toast.LENGTH_SHORT).show();
                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                Fragment test = new Options();

                                fragmentTransaction.commit();
                                return false;
                        }
                });

                return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
                // Handle action bar item clicks here. The action bar will
                // automatically handle clicks on the Home/Up button, so long
                // as you specify a parent activity in AndroidManifest.xml.
                int id = item.getItemId();
                return super.onOptionsItemSelected(item);
        }


        @Override
        public void ActivityDataFactory(Fragment from, String to, int requestCode, int type, Bundle bundle) {

                try {
                        FragmentTransaction fragmentTransaction;
                        ViewPagerFragment viewPagerFragment = null;

                        if (from.getParentFragment().getTag() == "WorkoutConfig") {
                                fragmentTransaction = WorkoutConfig.getChildFragmentManager().beginTransaction();
                                viewPagerFragment = WorkoutConfig;
                        } else if (from.getParentFragment().getTag() == "calendar") {
                                fragmentTransaction = calendar.getChildFragmentManager().beginTransaction();
                                viewPagerFragment = calendar;
                        } else
                                throw new Exception();


                        if (viewPagerFragment.getChildFragmentManager().getBackStackEntryCount() != 0) {
                                FragmentManager.BackStackEntry backStackEntry = viewPagerFragment.getChildFragmentManager().getBackStackEntryAt(viewPagerFragment.getChildFragmentManager().getBackStackEntryCount() - 1);
                                Log.i(backStackEntry.getName(), "");
                        }

                        switch (to) {
                                case "AddWorkoutSetRepWeightListFragment":

                                        AddWorkoutSetRepWeightListFragment AddWorkoutSetRepWeightListFragment = (AddWorkoutSetRepWeightListFragment) viewPagerFragment.getChildFragmentManager().findFragmentByTag("AddWorkoutSetRepWeightListFragment");

                                        if (AddWorkoutSetRepWeightListFragment == null) {
                                                AddWorkoutSetRepWeightListFragment = new AddWorkoutSetRepWeightListFragment();

                                                AddWorkoutSetRepWeightListFragment.setArguments(bundle);
                                        } else {
                                                // call a method here to adhoc update the fragment

                                        }
                                        fragmentTransaction.setCustomAnimations(R.animator.card_flip_left_in, R.animator.card_flip_left_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out);

                                        fragmentTransaction.replace(R.id.viewpagerroot, AddWorkoutSetRepWeightListFragment, "AddWorkoutSetRepWeightListFragment");
                                        fragmentTransaction.addToBackStack("AddWorkoutSetRepWeightListFragment");

                                        fragmentTransaction.commit();

                                        break;


                                case "AddWorkoutFragment":

                                        AddWorkoutFragment addWorkoutFragment = null;

                                        if (type == detail) {

                                                addWorkoutFragment = (AddWorkoutFragment) viewPagerFragment.getChildFragmentManager().findFragmentByTag(viewPagerFragment.getViewPagerFragmentId(AddWorkoutFragment.class.toString()));
                                                if (addWorkoutFragment == null)
                                                        addWorkoutFragment = (AddWorkoutFragment) viewPagerFragment.getChildFragmentManager().findFragmentByTag("AddWorkoutFragment");
                                                addWorkoutFragment.setTargetFragment(from, requestCode);
                                                addWorkoutFragment.setWorkoutListExerciseSetRep(bundle, "ExerciseSetRepConfig");
                                                viewPagerFragment.getChildFragmentManager().popBackStack();
                                        } else if (type == newInstance) {
                                                addWorkoutFragment = new AddWorkoutFragment();
                                                addWorkoutFragment.setArguments(bundle);
                                                fragmentTransaction.replace(R.id.viewpagerroot, addWorkoutFragment, "AddWorkoutFragment");
                                                addWorkoutFragment.setTargetFragment(from, requestCode);
                                        }

                                        if (type == newInstance)
                                                fragmentTransaction.addToBackStack("AddWorkoutFragment");

                                        fragmentTransaction.commit();
                                        break;
                                case "FilterableWorkoutListFragment":

                                        FilterableWorkoutListFragment filterableWorkoutListFragment = (FilterableWorkoutListFragment) viewPagerFragment.getChildFragmentManager().findFragmentByTag("FilterableListWorkoutListFragment");

                                        if (filterableWorkoutListFragment == null) {
                                                filterableWorkoutListFragment = new FilterableWorkoutListFragment();
                                                filterableWorkoutListFragment.setArguments(bundle);
                                        } else {
                                                // call a method here to adhoc update the fragment

                                        }

                                        filterableWorkoutListFragment.setTargetFragment(from, requestCode);
                                        filterableWorkoutListFragment.show(viewPagerFragment.getChildFragmentManager(), "Dialog");

                                        break;


                                case "WeeklyorRecurringListFragment":

                                        WeeklyorRecurringListFragment weeklyorRecurringListFragment = null;

                                        if (type == detail) {
                                                // WorkoutConfig.getChildFragmentManager().popBackStack();

                                                weeklyorRecurringListFragment = (WeeklyorRecurringListFragment) viewPagerFragment.getChildFragmentManager().findFragmentByTag("WeeklyorRecurringListFragment");

                                                if (weeklyorRecurringListFragment == null) {
                                                        weeklyorRecurringListFragment = (WeeklyorRecurringListFragment) viewPagerFragment.getChildFragmentManager().findFragmentByTag(viewPagerFragment.getViewPagerFragmentId(WeeklyorRecurringListFragment.class.toString()));
                                                }

                                                weeklyorRecurringListFragment.setTargetFragment(from, requestCode);

                                                if (requestCode == WeeklyorRecurringListFragment.defaultInstance)
                                                        weeklyorRecurringListFragment.updateWeeklyorRecurringList(bundle);
                                                else if (requestCode == WeeklyorRecurringListFragment.fromFilterableWeeklyorRecurringList)
                                                        weeklyorRecurringListFragment.selectWeeklyorRecurringList(bundle);

                                        } else if (type == newInstance) {
                                                weeklyorRecurringListFragment = new WeeklyorRecurringListFragment();
                                                weeklyorRecurringListFragment.setTargetFragment(from, requestCode);
                                                weeklyorRecurringListFragment.setArguments(bundle);

                                                fragmentTransaction.replace(R.id.viewpagerroot, weeklyorRecurringListFragment, "WeeklyorRecurringListFragment");
                                        }

                                        if (type == newInstance)
                                                fragmentTransaction.addToBackStack("WeeklyorRecurringListFragment");

                                        fragmentTransaction.commit();

                                        break;


                                case "AddExercise":
                                        AddExercise addExercise = null;

                                        if (type == detail) {
                                                addExercise = (AddExercise) viewPagerFragment.getChildFragmentManager().findFragmentByTag(viewPagerFragment.getViewPagerFragmentId(AddExercise.class.toString()));
                                        } else if (type == newInstance) {
                                                addExercise = new AddExercise();
                                                addExercise.setArguments(bundle);
                                                fragmentTransaction.replace(R.id.viewpagerroot, addExercise, "AddExercise");
                                        }

                                        addExercise.setTargetFragment(from, requestCode);
                                        fragmentTransaction.addToBackStack("AddExercise");
                                        fragmentTransaction.commit();
                                        break;

                        }

                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
}
