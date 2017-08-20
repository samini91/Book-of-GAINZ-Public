package com.example.sadiq.test.WeeklyorRecurringList;

import android.app.Fragment;

/**
 * Created by Mugen on 8/3/2017.
 */

public class WeeklyorRecurringListSingleton {

        private static WeeklyorRecurringListSingleton weeklyorRecurringListSingleton;

        private  WeeklyorRecurringListAdapter weeklyorRecurringListAdapter;

        private WeeklyorRecurringListSingleton(){}

        public static WeeklyorRecurringListSingleton getWeeklyorRecurringListSingleton(){
                if(weeklyorRecurringListSingleton == null ){
                        weeklyorRecurringListSingleton = new WeeklyorRecurringListSingleton();
                }
                return weeklyorRecurringListSingleton;
        }

        public static void setWeeklyorRecurringListAdapter (WeeklyorRecurringListAdapter weeklyorRecurringListAdapter){
                weeklyorRecurringListSingleton.weeklyorRecurringListAdapter = weeklyorRecurringListAdapter;
        }

        public WeeklyorRecurringListAdapter getWeeklyorRecurringListAdapter() {
                return weeklyorRecurringListAdapter;
        }

}
