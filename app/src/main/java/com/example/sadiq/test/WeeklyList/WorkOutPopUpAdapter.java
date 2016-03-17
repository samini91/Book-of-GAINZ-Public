package com.example.sadiq.test.WeeklyList;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sadiq.test.R;
import com.woxthebox.draglistview.DragItemAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sadiq on 2/26/2016.
 */
//todo change this into a baseadapter so i can add pair functionality
public class WorkOutPopUpAdapter<T> extends BaseAdapter {
    LayoutInflater mInflater;
    public ArrayList<Pair<Long,String>> values;

    private static class WorkoutViewHolder{
        TextView workOutName;
        long workOutId;
    }


    public WorkOutPopUpAdapter(Context context, @LayoutRes int resource, @IdRes int textViewResourceId, Cursor cursor) {
        //super(context,resource,textViewResourceId,createWorkOutPopUpAdapter(cursor));
        this.createWorkOutPopUpAdapter(cursor);
        mInflater = LayoutInflater.from(context);
    }
    //TODO instead return a pair of id and name
    public List<Pair<Long,String>> createWorkOutPopUpAdapter(Cursor cursor){
        cursor.moveToFirst();
        values = new ArrayList<>();
        for (int i = 0;i<cursor.getCount();i++){
            values.add(new Pair<>(Long.parseLong(cursor.getString(0)), cursor.getString(1)));
            cursor.moveToNext();
        }


        return values;

    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return values.get(position).first;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        WorkoutViewHolder workoutViewHolder;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.weeklyschedule_workout_rowlayout,parent,false);
            workoutViewHolder=new WorkoutViewHolder();
            workoutViewHolder.workOutName=(TextView)convertView.findViewById(R.id.listText);
            convertView.setTag(workoutViewHolder);
        }
        else{
            workoutViewHolder=(WorkoutViewHolder)convertView.getTag();
        }

        String data = ((Pair<Long,String>) getItem (position)).second;

        workoutViewHolder.workOutName.setText(data);

        return convertView;

    }




    /*public WorkOutPopUpAdapter(Context context, @LayoutRes int resource, @IdRes int textViewResourceId, List<T> list) {
        super(context, resource,textViewResourceId,list);
    }*/

}
