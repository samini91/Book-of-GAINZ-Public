package com.example.sadiq.test.FilterableList;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadiq.test.Database.RealmDB;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Case;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Mugen on 8/9/2017.
 */

public class FilterableListAdapter<T extends FilterableListViewHolder, O extends RealmObject> extends RecyclerView.Adapter<T> {

        public interface FilterableListAdapterOnViewClick {
                void FilterableListAdapterOnViewClick(int position);
        }

        public void setFilterableListAdapterOnViewClick(FilterableListAdapterOnViewClick filterableListAdapterOnViewClick) {
                this.filterableListAdapterOnViewClick = filterableListAdapterOnViewClick;
        }

        Class<T> cls;
        Class<O> clazz;
        RealmResults<O> realmResults;
        RealmQuery<O> where;
        int layout;
        RealmDB realmDB;
        FilterableListAdapterOnViewClick filterableListAdapterOnViewClick;

        public FilterableListAdapter(Context context, Class<O> clazz, Class<T> cls, int layout) {
                super();

                realmDB = new RealmDB();

                this.cls = cls;
                this.clazz = clazz;
                this.layout = layout;
                where = realmDB.getRealm().where(clazz);
                realmResults = where.findAll();

        }

        @Override
        public T onCreateViewHolder(ViewGroup parent, int viewType) {

                View vh = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
                try {
                        T retVal = cls.getConstructor(View.class).newInstance(vh);
                        retVal.setFilterableListAdapterOnViewClick(filterableListAdapterOnViewClick);
                        return retVal;
                } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                }
        }

        @Override
        public void onBindViewHolder(T holder, int position) {

                holder.bind(realmResults, position);
        }

        @Override
        public int getItemCount() {
                return realmResults.size();
        }

        public void updateFilter(HashMap<String, String> filterMap) {
                where = realmDB.getRealm().where(clazz);
                for (Map.Entry<String, String> set : filterMap.entrySet()) {
                        where = where.contains(set.getKey(), set.getValue(), Case.INSENSITIVE);
                }
                realmResults = where.findAll();

                this.notifyDataSetChanged();
        }


        public void updateFilter(List<Pair<String, String>> filterList) {

                where = realmDB.getRealm().where(clazz);

                for (Pair<String, String> filterKeyVal : filterList) {
                        //
                        where = where.beginsWith(filterKeyVal.first, filterKeyVal.second);
                }

                realmResults = where.findAll();

                this.notifyDataSetChanged();
        }

        public RealmResults<O> getRealmResultList() {
                return realmResults;
        }

}
