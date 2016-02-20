package com.example.sadiq.test;

import android.app.Activity;

import android.app.FragmentContainer;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

import com.example.sadiq.test.CustomDataTypes.myViewPager;
import com.example.sadiq.test.Database.DatabaseDisplayFragment;
import com.example.sadiq.test.dateManipulator;

public class MainActivity extends AppCompatActivity {

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return new fragmentTest();
        }

        @Override
        public int getCount() {
            return 1;
        }

    }

    private myViewPager mPager;

    private LinkedList fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.);
        setContentView(R.layout.activity_main);
        mPager = (myViewPager) findViewById(R.id.pager);

        fragmentList = new LinkedList<Fragment>();


        //fragmentList.add(Fragment.instantiate(this, fragmentTest.class.getName()));
        //fragmentList.add(Fragment.instantiate(this, fragmentTest.class.getName()));
        //fragmentList.add(new fragmentTest());
      //  fragmentList.add(new fragmentTest());
        //fragmentList.add(new test());
        //fragmentList.add(new workOutList());

        //fragmentList.add(new addWorkout());
        //fragmentList.add(new DatabaseDisplayFragment());
        //fragmentList.add(new addExersice());
        //fragmentList.add(new mainMenu());

        fragmentList.add(new addWeeklySchedule());


        FragmentAdapterCreator FragmentAdapter = new FragmentAdapterCreator(getSupportFragmentManager(),fragmentList);

        mPager.setAdapter(FragmentAdapter);



       // mPageAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        //mPageAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        //mPager.setAdapter(mPageAdapter);


       // mPager.setCurrentItem(3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Context context = this;

        //Fragment agf = new Fragment();

        Button moveToAddWorkout = (Button) findViewById(R.id.moveToAddWorkout);

        moveToAddWorkout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                /*  AnimationSet set = new AnimationSet(true);

                Animation animation = new AlphaAnimation(2.0f, 1.0f);
                animation.setDuration(100);
                set.addAnimation(animation);

                animation = new TranslateAnimation(
                        Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                        Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f
                );
                animation.setDuration(500);
                set.addAnimation(animation);

                LayoutAnimationController controller =
                        new LayoutAnimationController(set, 0.25f);

*/

                Intent intent = new Intent(context, addWorkoutActivity.class);
                startActivity(intent);

            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        FloatingActionButton a= (FloatingActionButton) findViewById(R.id.fab1);
      /*
        final EditText editText = (EditText) findViewById(R.id.editText);
        editText.showContextMenu();

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        System.out.println(dayOfWeek);

       /editText.setText(dateManipulator.getCurrentDayOfWeek());

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    // sendMessage();
                    handled = true;

                }
                return handled;
            }
        });
*/

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // setContentView(R.layout.fragment_blank);
                fragmentTest ae = new fragmentTest();

                Snackbar.make(view, "Replace with your own actiofdsa,l,,,,.,,..,,.,.asdfn", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own fasdfasdfffffffffffffrqerfqerfwerfwerfwerfwerfwerfwerfwerfw" +
                        "werfwerfwerfwerfwerf" +
                        "werfwerfwerfwerfwerfwerfwerfwerfwerfactiofdsa,l,,,,.,,..,,.,.asdfn", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();

  //              android.app.FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                //fragmentTest test = new fragmentTest();
                //fragmentTransaction.replace(android.R.id.content, test);

                //fragmentTransaction.commit();

            }
        });






    }
    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.

            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
