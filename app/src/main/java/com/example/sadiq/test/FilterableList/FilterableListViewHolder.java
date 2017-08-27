package com.example.sadiq.test.FilterableList;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Mugen on 8/9/2017.
 */

public abstract class FilterableListViewHolder<O extends RealmObject> extends RecyclerView.ViewHolder {

        FilterableListAdapter.FilterableListAdapterOnViewClick filterableListAdapterOnViewClick;

        public FilterableListViewHolder(View itemView) {
                super(itemView);
        }

        public abstract void bind(RealmResults<O> realmResults, int position);

        public void setFilterableListAdapterOnViewClick(FilterableListAdapter.FilterableListAdapterOnViewClick filterableListAdapterOnViewClick) {
                this.filterableListAdapterOnViewClick = filterableListAdapterOnViewClick;
        }

}
