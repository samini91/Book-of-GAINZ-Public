package com.example.sadiq.test;

//import android.support.v4.app.Fragment;
import android.app.Fragment;
//import android.support.v4.app.FragmentManager;
import android.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentPagerAdapter;
import com.example.sadiq.test.FragmentIdMappingSingleton;

import android.support.v4.app.ListFragment;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sadiq on 1/12/2016.
 */
public class FragmentAdapterCreator extends FragmentPagerAdapter  {

        private List<Class> fragmentList;
        SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
        HashMap<String,String> fragmentIdMappingHashMap = new HashMap<>();

    public FragmentAdapterCreator(FragmentManager fm,List fragmentList){
        super(fm);

        this.fragmentList=fragmentList;

    }
    @Override
    public Fragment getItem(int Position) {

        try {
            return ((Fragment)this.fragmentList.get(Position).newInstance());
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (IllegalAccessException e )
        {
            e.printStackTrace();
            return null;
        }
    }

    public int getCount(){
        return this.fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
            Fragment returnVal = (Fragment) super.instantiateItem(container, position);

            registeredFragments.put(position,returnVal);
            fragmentIdMappingHashMap.put(returnVal.getClass().toString(),returnVal.getTag().toString());
            //FragmentIdMappingSingleton.PutFragmentId(returnVal.getClass().toString(),returnVal.getTag().toString());
            return returnVal;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
            fragmentIdMappingHashMap.remove(registeredFragments.get(position).getClass().toString());
            registeredFragments.remove(position);
            super.destroyItem(container, position, object);
    }

        public HashMap<String, String> getFragmentIdMappingHashMap() {
                return fragmentIdMappingHashMap;
        }

        //public List<Fragment> getFragmentList (){return fragmentList;}

}
