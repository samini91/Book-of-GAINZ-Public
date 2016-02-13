package com.example.sadiq.test;

import java.util.LinkedList;

/**
 * Created by Sadiq on 2/10/2016.
 */
//singleton class for the database
public class Database {

    private static Database db= new Database();
    private LinkedList<String> list;
    private Database(){
        //create that shit but for now reaturn dummy values for testing
        list = new LinkedList<>();

    }

    public static Database getDatabase(){
        return db;
    }
}
