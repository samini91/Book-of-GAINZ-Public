package com.example.sadiq.test;

//import android.support.v4.app.Fragment;
import android.app.Fragment;
//import android.support.v4.app.FragmentManager;
import android.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentPagerAdapter;


import android.support.v4.app.ListFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sadiq on 1/12/2016.
 */
public class FragmentAdapterCreator extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    ListFragment a;
    public FragmentAdapterCreator(FragmentManager fm,List fragmentList){
        super(fm);

        this.fragmentList=fragmentList;

    }

    public Fragment getItem(int Position){
        return  this.fragmentList.get(Position);
    }

    public int getCount(){
        return this.fragmentList.size();
    }

    public List<Fragment> getFragmentList (){return fragmentList;}

}
