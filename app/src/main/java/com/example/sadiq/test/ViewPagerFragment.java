package com.example.sadiq.test;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadiq.test.CustomDataTypes.myViewPager;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mugen on 7/7/2017.
 */

public class ViewPagerFragment extends Fragment {
        List<Class> fragmentList;
        ViewGroup root;
        myViewPager myViewPager;
        FragmentAdapterCreator childFragmentAdapter;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

                root = (ViewGroup) inflater.inflate(R.layout.viewpager, container, false);

                myViewPager = (myViewPager) root.findViewById(R.id.pager);

                return root;
        }

        public void setFragmentList(List<Class> fragmentList) {
                if (childFragmentAdapter == null) {
                        myViewPager = (myViewPager) root.findViewById(R.id.pager);
                        this.fragmentList = fragmentList;

                        childFragmentAdapter = new FragmentAdapterCreator(getChildFragmentManager(), fragmentList);
                        ///FragmentAdapterCreator childFragmentAdapter = new FragmentAdapterCreator(getFragmentManager(),fragmentList);

                        this.myViewPager.setAdapter(childFragmentAdapter);

                        childFragmentAdapter.notifyDataSetChanged();
                }
        }

        public String getViewPagerFragmentId(String id){
                return childFragmentAdapter.getFragmentIdMappingHashMap().get(id);
        }

        public void setMyViewPagerCurrentItem(int i) {
                this.myViewPager.setCurrentItem(i, true);

        }

        public void setOffscreenPageLimit(int i ){
                myViewPager.setOffscreenPageLimit(i);
        }

        public FragmentManager getViewPagerFragmentTransaction() {
                return getChildFragmentManager();

        }

        public List<Class> getFragmentList() {
                return fragmentList;
        }

        public FragmentAdapterCreator getChildFragmentAdapter() {
                return childFragmentAdapter;
        }

}
