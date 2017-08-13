package com.example.sadiq.test.FilterableList;

import android.content.Context;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.annotations.RealmClass;

/**
 * Created by Mugen on 8/9/2017.
 */

public class FilterableListAdapter <T extends FilterableListViewHolder, O extends RealmObject> extends RecyclerView.Adapter<T> {

        Class<T> cls;
        Class<O> clazz;
        RealmResults<O> realmResults;
        RealmQuery<O> where;
        int layout;
        RealmDB realmDB;
        public FilterableListAdapter(Context context,Class<O> clazz, Class<T>cls, int layout)
        {
                super();

                realmDB = new RealmDB();

                this.cls = cls;
                this.clazz = clazz;
                this.layout = layout;
                where = realmDB.getRealm().where(clazz);
                realmResults = where.findAll();
                Log.i("ExerciseCount",Integer.toString(where.findAll().size()));

        }

        @Override
        public T onCreateViewHolder(ViewGroup parent, int viewType) {
                //layout =
                //View vh = LayoutInflater.from(parent.getContext()).inflate(R.layout.filterablelist_viewholder_exercise,parent,false);
                View vh = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);

                try
                {
                        return cls.getConstructor(View.class).newInstance(vh);
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        return null;
                }
        }

        @Override
        public void onBindViewHolder( T holder, int position) {

                //holder.textView.setText(list.get(position));
                holder.bind(realmResults,position);
        }

        @Override
        public int getItemCount() {
                return realmResults.size();
        }

        public void updateFilter(HashMap<String,String> filterMap)
        {
                where = realmDB.getRealm().where(clazz);
                for( Map.Entry<String, String> set :filterMap.entrySet()){
                        where = where.beginsWith(set.getKey(),set.getValue());
                }
                realmResults = where.findAll();

                this.notifyDataSetChanged();
        }


        public void updateFilter(List<Pair<String,String>> filterList){

                where = realmDB.getRealm().where(clazz);

                for(Pair<String,String> filterKeyVal: filterList){
                        //
                        where = where.beginsWith(filterKeyVal.first,filterKeyVal.second);
                }

                realmResults = where.findAll();

                this.notifyDataSetChanged();
        }

        /*public static class ViewHolder extends RecyclerView.ViewHolder
        {
                @Bind (R.id.filterablelist_viewholder_textview) TextView textView;
                public ViewHolder(View itemView) {
                        super(itemView);
                        ButterKnife.bind(this,itemView);
                }
        }
        */

}
