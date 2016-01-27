package com.example.sadiq.test;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.LinkedList;

/**
 * Created by Sadiq on 1/12/2016.
 */
public class FragmentAdapterCreator extends FragmentPagerAdapter {

    private LinkedList<Fragment> fragmentList;

    public FragmentAdapterCreator(FragmentManager fm,LinkedList<Fragment> fragmentList){
        super(fm);
        this.fragmentList=fragmentList;

    }

    public Fragment getItem(int Position){
        return this.fragmentList.get(Position);
    }

    public int getCount(){
        return this.fragmentList.size();
    }


}
