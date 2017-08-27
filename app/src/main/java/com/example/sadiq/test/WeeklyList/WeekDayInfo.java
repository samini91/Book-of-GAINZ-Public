package com.example.sadiq.test.WeeklyList;

import java.util.ArrayList;

/**
 * Created by Sadiq on 2/22/2016.
 */
public class WeekDayInfo {

        protected String name;
        protected long Id;
        protected int dayOfTheWeek;


        public WeekDayInfo() {
        }

        private WeekDayInfo(int dayOfTheWeek) {
                this.dayOfTheWeek = dayOfTheWeek;
                Id = dayOfTheWeek;
        }

        public static ArrayList<WeekDayInfo> initilizeWeeklySchedule() {
                ArrayList<WeekDayInfo> weekDayInfoArrayList = new ArrayList<>();

                weekDayInfoArrayList.add(new WeekDayInfo(WeekDayVariables.Sunday));
                weekDayInfoArrayList.add(new WeekDayInfo(WeekDayVariables.Monday));
                weekDayInfoArrayList.add(new WeekDayInfo(WeekDayVariables.Tuesday));
                weekDayInfoArrayList.add(new WeekDayInfo(WeekDayVariables.Wednesday));
                weekDayInfoArrayList.add(new WeekDayInfo(WeekDayVariables.Thursday));
                weekDayInfoArrayList.add(new WeekDayInfo(WeekDayVariables.Friday));
                weekDayInfoArrayList.add(new WeekDayInfo(WeekDayVariables.Saturday));


                return weekDayInfoArrayList;
        }


}




