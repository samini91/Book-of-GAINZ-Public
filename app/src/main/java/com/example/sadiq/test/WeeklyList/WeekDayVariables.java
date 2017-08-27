package com.example.sadiq.test.WeeklyList;

/**
 * Created by Sadiq on 2/26/2016.
 */
public class WeekDayVariables {
        protected static int Sunday = 0;
        protected static int Monday = 1;
        protected static int Tuesday = 2;
        protected static int Wednesday = 3;
        protected static int Thursday = 4;
        protected static int Friday = 5;
        protected static int Saturday = 6;

        public static String WeekDayIntToString(int dayInt) {

                switch (dayInt) {
                        case (0):
                                return "Sunday";
                        case (1):
                                return "Monday";
                        case (2):
                                return "Tuesday";
                        case (3):
                                return "Wednesday";
                        case (4):
                                return "Thursday";
                        case (5):
                                return "Friday";
                        case (6):
                                return "Saturday";
                }
                return "Invalid Int";

        }
}
