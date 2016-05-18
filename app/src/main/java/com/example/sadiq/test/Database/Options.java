package com.example.sadiq.test.Database;

import android.content.Context;

/**
 * Created by Sadiq on 5/11/2016.
 */

//Used to get options from db
public class Options {
    static OptionDB options;
    //TODO 5/11
    public static OptionDB getOptions(Context context){
        if(options == null){
            RealmDB.getRealmInstance(context);


        }
        return options;
    }





}
