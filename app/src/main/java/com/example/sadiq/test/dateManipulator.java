package com.example.sadiq.test;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sadiq on 1/4/2016.
 */
public class dateManipulator {
    public static String getCurrentDayOfWeek(){
        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        String dayValue;
        /*
        * Sunday = 1
        * Monday = 2
        * etc...
        * */
        switch (dayOfWeek){
            case 1 : dayValue="Sunday";
                        break;
            case 2 : dayValue="Monday";
                        break;
            case 3 : dayValue="Tuesday";
                        break;
            case 4 : dayValue="Wednesday";
                break;
            case 5 : dayValue="Thursday";
                break;
            case 6 : dayValue="Friday";
                break;
            case 7 : dayValue="Saturday";
                break;
            default: dayValue="Your phone is broke bro : no Date";
                break;

        }


        return dayValue;
    }

}
