package com.example.sadiq.test.WeeklyList;

/**
 * Created by Sadiq on 2/26/2016.
 */
public class WeekDayVariables {
    protected static int Sunday = 1;
    protected static int Monday = 2;
    protected static int Tuesday = 3;
    protected static int Wednesday = 4;
    protected static int Thursday = 5;
    protected static int Friday = 6;
    protected static int Saturday = 7;

    public static String WeekDayIntToString(int dayInt){

        switch (dayInt){
            case (1): return "Sunday";
            case (2): return "Monday";
            case (3): return "Tuesday";
            case (4):return "Wednesday";
            case (5):return"Thursday";
            case (6):return "Friday";
            case (7):return "Saturday";
        }
        return "Invalid Int";

    }
}
