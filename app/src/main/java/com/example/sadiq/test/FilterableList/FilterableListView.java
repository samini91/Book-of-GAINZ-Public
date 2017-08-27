package com.example.sadiq.test.FilterableList;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.sadiq.test.R;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mugen on 8/10/2017.
 */

public class FilterableListView extends LinearLayout implements IUpdateFilter {


        ViewGroup root;
        @Bind(R.id.FilterableList_RecyclerView)
        RecyclerView recyclerView;
        @Bind(R.id.FilterRoot)
        FrameLayout filterRoot;

        HashMap<String, String> filterMap;

        IUpdateFilter filterViewInterface;

        public FilterableListView(Context context) {
                super(context);
                init();
        }

        public FilterableListView(Context context, @Nullable AttributeSet attrs) {
                super(context, attrs);
                init();
        }

        public FilterableListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init();
        }

        void init() {
                root = (ViewGroup) inflate(getContext(), R.layout.filterablelist, this);

                ButterKnife.bind(this, root);

                filterMap = new HashMap<>();

                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

                recyclerView.setLayoutManager(mLayoutManager);

        }

        public void setAdapter(FilterableListAdapter adapter) {
                recyclerView.setAdapter(adapter);
        }

        public void setFilterView(FiltersBase filterView) {
                filterView.setLayoutParams(new ViewGroup.LayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)));
                filterRoot.addView(filterView);
                filterView.setUpdateFilter(this);
        }


        public void UpdateFilter(String key, String val) {

                filterMap.put(key, val);

                ((FilterableListAdapter) recyclerView.getAdapter()).updateFilter(filterMap);
        }

}
