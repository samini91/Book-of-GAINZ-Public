package com.example.sadiq.test.CalendarMenu;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.sadiq.test.R;
import com.example.sadiq.test.WeeklyList.WeekDayVariables;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mugen on 8/23/2017.
 */

public class CalendarMenu extends Fragment {

        ViewGroup root;
        @Bind(R.id.calendarView)
        CalendarView calendarView;

        //@Bind(R.id.template)
        //TabItem templateTab;

        //@Bind(R.id.actual)
        //TabItem actualTab;

        @Bind(R.id.tabLayout)
        TabLayout tabLayout;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
                super.onCreateView(inflater, container, savedInstanceState);

                root = (ViewGroup) inflater.inflate(R.layout.calendarmenu, container, false);

                ButterKnife.bind(this, root);

                setCalendarView();


                //   TabLayout.Tab t ;


                return root;

        }

        private void setCalendarView() {

                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                        @Override
                        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {


                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, month, dayOfMonth);
                                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                                Toast.makeText(getActivity(), (WeekDayVariables.WeekDayIntToString(dayOfWeek)), Toast.LENGTH_SHORT).show();

                        }
                });

        }

}
