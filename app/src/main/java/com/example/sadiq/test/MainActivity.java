package com.example.sadiq.test;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
//import android.support.v4.app.Fragment;
import android.app.Fragment;

//import android.support.v4.app.FragmentManager;
import android.app.FragmentManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

import com.example.sadiq.test.AddWorkout.AddWorkoutFragment;
import com.example.sadiq.test.CustomDataTypes.myViewPager;
import com.example.sadiq.test.Exercise.AddExercise;
import com.example.sadiq.test.FilterableList.FilterableExerciseListFragment;
import com.example.sadiq.test.FilterableList.FilterableWeeklyorRecurringScheduleListFragment;
import com.example.sadiq.test.FilterableList.FilterableWorkoutListFragment;
import com.example.sadiq.test.Options.Options;
import com.example.sadiq.test.SelectExerciseConfiguration.SelectExerciseConfiguration;
import com.example.sadiq.test.WeeklyorRecurringList.WeeklyorRecurringListFragment;


public class MainActivity extends Activity implements IActivityDataFactory {

        Context thisContext = this;
        Activity thisActivity = this;
        ViewGroup root;
        Bundle savedBundle;

        int currentInflatedViewLevel;

        int timer;

        myViewPager mPager;

        LinkedList<Class> fragmentList;
        LinkedList<Class> fragmentListButton3;
        FragmentAdapterCreator FragmentAdapter;

        TextView textView;


        ViewPagerFragment calendar, WorkoutConfig, test3;
        long startTime = 0;

        //runs without a timer by reposting this handler at the end of the runnable
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

                //root = new FrameLayout(this);
                //setContentView(root);
                init(savedInstanceState);
        }


        public void init(final Bundle savedBundle) {
                currentInflatedViewLevel = 0;
                setContentView(R.layout.mainmenutest);

                root = (ViewGroup) thisActivity.findViewById(R.id.mainlayout_root);
                //create empty framelayout and then do this
                //RelativeLayout item = (RelativeLayout)findViewById(R.id.item);
                //View child = getLayoutInflater().inflate(R.layout.child, null);
                //item.addView(child);
                // to swap out the view we want to display

                //   root.removeAllViewsInLayout();
//         View view = thisActivity.getLayoutInflater().inflate(R.layout.mainmenutest,null);

                //      root.addView(view);

                //setContentView(R.layout.mainmenutest);
                //root = (ViewGroup) this.findViewById(R.id.mainlayout_test);
                //root.removeAllViewsInLayout();
                //  root.removeAllViewsInLayout();

                Button button1 = (Button) findViewById(R.id.mainmenutest_button1);
                Button button2 = (Button) findViewById(R.id.mainmenutest_button2);
                Button button3 = (Button) findViewById(R.id.mainmenutest_button3);

                //if(currentInflatedView != )

        /*
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //replace the main layout with the view pager layout ... you may even pass the position if neccesarry
                Toast.makeText(thisActivity, "worksstill", Toast.LENGTH_SHORT).show();
                //root.removeAllViewsInLayout();
                root.removeAllViewsInLayout();
                currentInflatedView = R.layout.activity_main;


                View myViewPager =  thisActivity.getLayoutInflater().inflate(R.layout.activity_main, null);

                //myViewPager myViewPager = new myViewPager(thisActivity);
                //View view = thisActivity.getLayoutInflater().inflate(R.layout.addexersice,null);
                root.addView(myViewPager);


            if (fragmentList == null)
            {
                mPager = (myViewPager) findViewById(R.id.pager);
                fragmentList = new LinkedList<Fragment>();
                AddExercise a = new AddExercise();
                fragmentList.add(a);
                fragmentList.add(new AddExercise());
    //            FragmentAdapter = new FragmentAdapterCreator(, fragmentList);

                mPager.setAdapter(FragmentAdapter);

                FragmentAdapter.getFragmentList().remove(0);
                FragmentAdapter.notifyDataSetChanged();


            }


            }
        });
*/

                button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                currentInflatedViewLevel++;
                                //root.removeAllViewsInLayout();

                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                if (savedBundle != null) {
                                        WorkoutConfig = (ViewPagerFragment) getFragmentManager().findFragmentByTag("WorkoutConfig");
                                } else {

                                        WorkoutConfig = new ViewPagerFragment();

                                        //AddWorkoutFragment AddWorkoutFragment = new AddWorkoutFragment();
                                        final AddWorkoutSetRepWeightListFragment AddWorkoutSetRepWeightListFragment = new AddWorkoutSetRepWeightListFragment();
                                        /*
                                        AddWorkoutFragment.setAddWorkoutListAdapterCustomListerner(new addWorkoutListAdapter.CustomListener()
                                        {
                                                @Override
                                                public void onCustomListenerEvent(String Exercise) {

                                                        FragmentTransaction fragmentTransactionAddWorkout  = WorkoutConfig.getChildFragmentManager().beginTransaction();
                                                        fragmentTransactionAddWorkout.replace(R.id.viewpagerroot, AddWorkoutSetRepWeightListFragment);
                                                        fragmentTransactionAddWorkout.addToBackStack(null);
                                                        fragmentTransactionAddWorkout.commit();
                                                }
                                        });
*/

                                        fragmentList = new LinkedList<Class>();
                                        //fragmentList.add(AddWorkoutFragment.class);
                                        fragmentList.add(FilterableWeeklyorRecurringScheduleListFragment.class);
                                        fragmentList.add(FilterableExerciseListFragment.class);
                                        //fragmentList.add(WeeklyorRecurringListFragment.class);
                                        fragmentList.add(FilterableWorkoutListFragment.class);
                                        //fragmentList.add(AddWorkoutFragment.class);
                                        //fragmentList.add(AddExercise.class);
                                }
                                //Fragment test =
                                //fragmentTransaction.add(R.id., test);
                                fragmentTransaction.replace(R.id.mainlayout_root, WorkoutConfig, "WorkoutConfig");
                                //fragmentTransaction.add(R.id.mainlayout_test,test,"testtag");
                                //fragmentTransaction.add(test,"testtag");
                                ///fragmentTransaction.add(R.id.mainlayout_test,test);
                                fragmentTransaction.addToBackStack("ViewPager1");
                                //fragmentTransaction.add(test,"option");

                                //fragmentTransaction.add(test,"opiton");

                                fragmentTransaction.commit();

                                fragmentManager.executePendingTransactions();
                                WorkoutConfig.setFragmentList(fragmentList);

//                                WorkoutConfig.setMyViewPagerCurrentItem(0);


                        }
                });
                button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(thisActivity, "worksstill", Toast.LENGTH_SHORT).show();

                                currentInflatedViewLevel++;
                                //root.removeAllViewsInLayout();

                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                calendar = new ViewPagerFragment();

                                fragmentTransaction.add(R.id.mainlayout_root, calendar,"calendar");

                                fragmentTransaction.addToBackStack("calendar");

                                fragmentTransaction.commit();
                                fragmentManager.executePendingTransactions();

                                fragmentList = new LinkedList<Class>();
                                fragmentList.add(AddExercise.class);
                                fragmentList.add(AddWorkoutFragment.class);
                                fragmentList.add(WeeklyorRecurringListFragment.class);

                                calendar.setFragmentList(fragmentList);
                        }
                });

                button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                currentInflatedViewLevel++;
                                //root.removeAllViewsInLayout();

                                FragmentManager fragmentManager = getFragmentManager();
                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                if (savedBundle != null) {
                                        test3 = (ViewPagerFragment) getFragmentManager().findFragmentByTag("WorkoutConfig3");
                                } else {

                                        test3 = new ViewPagerFragment();

                                        fragmentListButton3 = new LinkedList<>();
                                        //fragmentListButton3.add(new ActualWorkoutFragment());
                                        fragmentListButton3.add(FilterableExerciseListFragment.class);

                                }
                                //Fragment test =
                                //fragmentTransaction.add(R.id., test);
                                fragmentTransaction.replace(R.id.mainlayout_root, test3, "WorkoutConfig3");
                                //fragmentTransaction.add(R.id.mainlayout_test,test,"testtag");
                                //fragmentTransaction.add(test,"testtag");
                                ///fragmentTransaction.add(R.id.mainlayout_test,test);
                                fragmentTransaction.addToBackStack("ViewPager3");
                                //fragmentTransaction.add(test,"option");

                                //fragmentTransaction.add(test,"opiton");
                                fragmentListButton3.add(FilterableExerciseListFragment.class);

                                fragmentTransaction.commit();

                                fragmentManager.executePendingTransactions();

                                test3.setFragmentList(fragmentListButton3);

                                //fragmentListButton3.add(FilterableExerciseListFragment;

//                                test3.getChildFragmentAdapter().notifyDataSetChanged();
                        }
                });

                //button3.setOnClickListener(i-> System.out.print(i.toString()));
                //fragmentList = new LinkedList<Fragment>();

//        fragmentList.add(new SelectExerciseConfiguration());

                //      FragmentAdapterCreator FragmentAdapter = new FragmentAdapterCreator(getFragmentManager(),fragmentList);

                textView = (TextView) findViewById(R.id.textView);
                timer = 0;
                final long max = 1000000000;
        /*
        new Thread() {
            public void run() {
                while (timer++ < max ) {
                    try {
                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                textView.setText("#" + timer);
                            }
                        });
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        HandlerThread handlerThread = new HandlerThread("timer");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();

        Handler handler = new Handler(looper);

        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("#" + timer);
                timer++;

            }
        });


        final long starttime = System.currentTimeMillis();
        final Handler h = new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                long millis = System.currentTimeMillis() - starttime;
                int seconds = (int) (millis / 1000);
                int minutes = seconds / 60;
                seconds     = seconds % 60;

                //textView.setText(String.format("%d:%02d", minutes, seconds));
                textView.setText(Long.toString(millis));
                return false;
            }
        });
*/
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
                else if(calendar != null && calendar.getViewPagerFragmentTransaction().getBackStackEntryCount() !=0)
                        calendar.getViewPagerFragmentTransaction().popBackStack();
                else if (this.getFragmentManager().getBackStackEntryCount() != 0) {
                        this.getFragmentManager().popBackStack();
                        init(savedBundle);
                } else
                        super.onBackPressed();
                //
                //super.onBackPressed();

// {
                //if (getFragmentManager().getBackStackEntryCount() > 0) {
//                FragmentManager.BackStackEntry first = getFragmentManager().getBackStackEntryAt(0);
                //              getFragmentManager().popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                //}


                //}
                //super.onBackPressed();

//        else
                //          init();

                //} else {
                // Otherwise, select the previous step.
                //  mPager.setCurrentItem(mPager.getCurrentItem() - 1);
                //}
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
                                //Fragment test =
                                //fragmentTransaction.add(R.id., test);
                                //fragmentTransaction.replace(thisActivity.vie,test);

                                //fragmentTransaction.add(test,"option");

                                //fragmentTransaction.add(test,"opiton");

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

                //noinspection SimplifiableIfStatement
                //if (id == R.id.action_settings) {
//            return true;
                //      }

                return super.onOptionsItemSelected(item);
        }

        /*
        public void setSetRepWeight(WorkoutTemplate workoutTemplate) {

                //if(WorkoutConfig.getViewPagerFragmentTransaction().findFragmentById(""))
                Log.i("bubbled up ", "bubbled up");
                Bundle args = new Bundle();

                //if(WorkoutConfig.getChildFragmentManager().findFragmentByTag("") == null )
                //need to tape down the other stuff so nobody trips over it
        }

*/
        @Override
        public void ActivityDataFactory(Fragment from, String to,int requestCode ,int type,Bundle bundle) {

                try {
                        FragmentTransaction fragmentTransaction;
                        ViewPagerFragment viewPagerFragment = null;

                        if (from.getParentFragment().getTag() == "WorkoutConfig") {
                                fragmentTransaction = WorkoutConfig.getChildFragmentManager().beginTransaction();
                                viewPagerFragment = WorkoutConfig;
                        }
                        else if(from.getParentFragment().getTag() == "calendar") {
                                fragmentTransaction = calendar.getChildFragmentManager().beginTransaction();
                                viewPagerFragment = calendar;
                        }
                        else if(from.getParentFragment().getTag() ==  "WeeklyorRecurringListFragment" || from.getTag() == FragmentIdMappingSingleton.FindFragmentId(WeeklyorRecurringListFragment.class.toString())) {

                                fragmentTransaction = from.getChildFragmentManager().beginTransaction();
                        }
                        //this is temporary i want to make sure that everything i am funneling into this method is hitting the if statement above
                        else
                                throw new Exception();


                        if(viewPagerFragment.getChildFragmentManager().getBackStackEntryCount() != 0) {
                                FragmentManager.BackStackEntry backStackEntry = viewPagerFragment.getChildFragmentManager().getBackStackEntryAt(viewPagerFragment.getChildFragmentManager().getBackStackEntryCount()-1);
                                Log.i(backStackEntry.getName(),"");
                        }

                switch(to) {
                        case "AddWorkoutSetRepWeightListFragment":

                                AddWorkoutSetRepWeightListFragment AddWorkoutSetRepWeightListFragment = (AddWorkoutSetRepWeightListFragment) viewPagerFragment.getChildFragmentManager().findFragmentByTag("AddWorkoutSetRepWeightListFragment");

                                if (AddWorkoutSetRepWeightListFragment == null) {
                                        AddWorkoutSetRepWeightListFragment = new AddWorkoutSetRepWeightListFragment();

                                        AddWorkoutSetRepWeightListFragment.setArguments(bundle);
                                }

                                else
                                {
                                        // call a method here to adhoc update the fragment

                                }
                                fragmentTransaction.setCustomAnimations(R.animator.card_flip_left_in,R.animator.card_flip_left_out,R.animator.card_flip_left_in,R.animator.card_flip_left_out);

                                fragmentTransaction.replace(R.id.viewpagerroot, AddWorkoutSetRepWeightListFragment, "AddWorkoutSetRepWeightListFragment");
                                fragmentTransaction.addToBackStack("AddWorkoutSetRepWeightListFragment");

                                fragmentTransaction.commit();

                                break;
                        case "AddWorkoutFragment":

                                AddWorkoutFragment addWorkoutFragment = null;

                                if(type == detail) {

                                        addWorkoutFragment = (AddWorkoutFragment) viewPagerFragment.getChildFragmentManager().findFragmentByTag(viewPagerFragment.getViewPagerFragmentId(AddWorkoutFragment.class.toString()));
                                        if( addWorkoutFragment == null)
                                                addWorkoutFragment = (AddWorkoutFragment) viewPagerFragment.getChildFragmentManager().findFragmentByTag("AddWorkoutFragment");
                                        addWorkoutFragment.setTargetFragment(from,requestCode);
                                        addWorkoutFragment.setWorkoutListExerciseSetRep(bundle,"ExerciseSetRepConfig");
                                        viewPagerFragment.getChildFragmentManager().popBackStack();
                                }
                                else if(type == newInstance ){
                                        addWorkoutFragment = new AddWorkoutFragment();
                                        addWorkoutFragment.setArguments(bundle);
                                        fragmentTransaction.replace(R.id.viewpagerroot, addWorkoutFragment,"AddWorkoutFragment");
                                        addWorkoutFragment.setTargetFragment(from,requestCode);
                                }

                                if(type == newInstance)
                                        fragmentTransaction.addToBackStack("AddWorkoutFragment");

                                fragmentTransaction.commit();
                                break;
                        case "FilterableWorkoutListFragment":

                                FilterableWorkoutListFragment filterableWorkoutListFragment = (FilterableWorkoutListFragment) viewPagerFragment.getChildFragmentManager().findFragmentByTag("FilterableListWorkoutListFragment");

                                if (filterableWorkoutListFragment == null) {
                                        filterableWorkoutListFragment  = new FilterableWorkoutListFragment();
                                        filterableWorkoutListFragment.setArguments(bundle);
                                }
                                else
                                {
                                        // call a method here to adhoc update the fragment

                                }

                                filterableWorkoutListFragment.setTargetFragment(from,requestCode);
                             // fragmentTransaction.addToBackStack("FilterableWorkoutListFragment");
                                filterableWorkoutListFragment.show(viewPagerFragment.getChildFragmentManager(),"Dialog");

                                break;


                        case "WeeklyorRecurringListFragment":

                                WeeklyorRecurringListFragment weeklyorRecurringListFragment = null;

                                if(type == detail){
                                       // WorkoutConfig.getChildFragmentManager().popBackStack();

                                        weeklyorRecurringListFragment = (WeeklyorRecurringListFragment) viewPagerFragment.getChildFragmentManager().findFragmentByTag("WeeklyorRecurringListFragment");

                                        if(weeklyorRecurringListFragment == null){
                                                weeklyorRecurringListFragment = (WeeklyorRecurringListFragment) viewPagerFragment.getChildFragmentManager().findFragmentByTag(FragmentIdMappingSingleton.FindFragmentId(WeeklyorRecurringListFragment.class.toString()));
                                        }

                                        weeklyorRecurringListFragment.setTargetFragment(from,requestCode);

                                        if(requestCode == WeeklyorRecurringListFragment.defaultInstance)
                                                weeklyorRecurringListFragment.updateWeeklyorRecurringList(bundle);
                                        else if(requestCode == WeeklyorRecurringListFragment.fromFilterableWeeklyorRecurringList)
                                                weeklyorRecurringListFragment.selectWeeklyorRecurringList(bundle);

                                }
                                else if(type == newInstance)
                                {
                                        weeklyorRecurringListFragment = new WeeklyorRecurringListFragment();
                                        weeklyorRecurringListFragment.setTargetFragment(from,requestCode);
                                        weeklyorRecurringListFragment.setArguments(bundle);

                                        fragmentTransaction.replace(R.id.viewpagerroot, weeklyorRecurringListFragment, "WeeklyorRecurringListFragment");
                                }

                                /*
                                if(weeklyorRecurringListFragment == null)
                                        weeklyorRecurringListFragment = (WeeklyorRecurringListFragment) WorkoutConfig.getChildFragmentManager().findFragmentByTag("WeeklyorRecurringListFragment");

                                if(from.getClass() != FilterableWeeklyorRecurringScheduleListFragment.class && weeklyorRecurringListFragment == null)
                                        //weeklyorRecurringListFragment = (WeeklyorRecurringListFragment) from.getChildFragmentManager().findFragmentByTag("WeeklyorRecurringListFragment");
                                        weeklyorRecurringListFragment = (WeeklyorRecurringListFragment) WorkoutConfig.getChildFragmentManager().findFragmentByTag(FragmentIdMappingSingleton.FindFragmentId(WeeklyorRecurringListFragment.class.toString()));

                                if (weeklyorRecurringListFragment == null)
                                {
                                        weeklyorRecurringListFragment = new WeeklyorRecurringListFragment();
                                        weeklyorRecurringListFragment.setTargetFragment(from,requestCode);
                                        weeklyorRecurringListFragment.setArguments(bundle);
                                }
                                else
                                {
                                        weeklyorRecurringListFragment.setTargetFragment(from,requestCode);
                                        if(requestCode == WeeklyorRecurringListFragment.defaultInstance)
                                                weeklyorRecurringListFragment.updateWeeklyorRecurringList(bundle);
                                        else if(requestCode == WeeklyorRecurringListFragment.fromFilterableWeeklyorRecurringList)
                                                weeklyorRecurringListFragment.selectWeeklyorRecurringList(bundle);
                                }

                                if(from.getClass() == FilterableWeeklyorRecurringScheduleListFragment.class ){
                                        try{
                                                fragmentTransaction.replace(R.id.viewpagerroot, weeklyorRecurringListFragment, "WeeklyorRecurringListFragment");
                                        }
                                        catch (Exception e)
                                        {
                                                weeklyorRecurringListFragment = new WeeklyorRecurringListFragment();
                                                weeklyorRecurringListFragment.setTargetFragment(from,requestCode);
                                                weeklyorRecurringListFragment.setArguments(bundle);
                                                fragmentTransaction.replace(R.id.viewpagerroot, weeklyorRecurringListFragment);

                                        }

                                }

                                //WorkoutConfig.getChildFragmentManager().popBackStack();
*/
                                if(type == newInstance)
                                        fragmentTransaction.addToBackStack("WeeklyorRecurringListFragment");

                                fragmentTransaction.commit();



                                break;
                        case "AddExercise":
                                AddExercise addExercise = null;

                                if(type == detail){
                                        addExercise = (AddExercise) viewPagerFragment.getChildFragmentManager().findFragmentByTag(FragmentIdMappingSingleton.FindFragmentId(AddExercise.class.toString()));
                                }
                                else if(type == newInstance)
                                {
                                        addExercise = new AddExercise();
                                        addExercise.setArguments(bundle);
                                        fragmentTransaction.replace(R.id.viewpagerroot, addExercise, "AddExercise");
                                }

                                addExercise.setTargetFragment(from, requestCode);
                                fragmentTransaction.addToBackStack("AddExercise");
                                fragmentTransaction.commit();
                                break;
                }

                }
                catch (Exception e) {
                        e.printStackTrace();
                }
        }
}
