package com.example.sadiq.test;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadiq.test.CustomDataTypes.myViewPager;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mugen on 7/7/2017.
 */

public class ViewPagerFragment extends Fragment {
        List<Pair<Class, String>> fragmentList;
        ViewGroup root;

        @Bind(R.id.pager)
        myViewPager myViewPager;

        @Bind(R.id.tabLayout)
        TabLayout tabLayout;

        FragmentAdapterCreator childFragmentAdapter;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

                root = (ViewGroup) inflater.inflate(R.layout.viewpager, container, false);

                ButterKnife.bind(this, root);

                return root;
        }

        public void setFragmentList(List<Pair<Class, String>> fragmentList) {
                if (childFragmentAdapter == null) {
                        myViewPager = (myViewPager) root.findViewById(R.id.pager);
                        this.fragmentList = fragmentList;


                        tabLayout.setupWithViewPager(myViewPager);

                        childFragmentAdapter = new FragmentAdapterCreator(getChildFragmentManager(), fragmentList);
                        ///FragmentAdapterCreator childFragmentAdapter = new FragmentAdapterCreator(getFragmentManager(),fragmentList);

                        this.myViewPager.setAdapter(childFragmentAdapter);

                        childFragmentAdapter.notifyDataSetChanged();
                }
        }

        public String getViewPagerFragmentId(String id) {
                return childFragmentAdapter.getFragmentIdMappingHashMap().get(id);
        }

        public void setMyViewPagerCurrentItem(int i) {
                this.myViewPager.setCurrentItem(i, true);

        }

        public void setOffscreenPageLimit(int i) {
                myViewPager.setOffscreenPageLimit(i);
        }

        public FragmentManager getViewPagerFragmentTransaction() {
                return getChildFragmentManager();

        }

        public List<Pair<Class, String>> getFragmentList() {
                return fragmentList;
        }

        public FragmentAdapterCreator getChildFragmentAdapter() {
                return childFragmentAdapter;
        }

}
