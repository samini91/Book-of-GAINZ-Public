package com.example.sadiq.test.WeeklyList;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
public class WorkOutPopUpAdapter<T> extends ArrayAdapter {

    LayoutInflater mInflater= LayoutInflater.from(getContext());

    private static class WorkoutViewHolder{
        TextView workOutName;
        long workOutId;
    }


    public WorkOutPopUpAdapter(Context context, @LayoutRes int resource, @IdRes int textViewResourceId, Cursor cursor) {
        super(context,resource,textViewResourceId,createWorkOutPopUpAdapter(cursor));

    }
    //TODO instead return a pair of id and name
    public static <T> List createWorkOutPopUpAdapter(Cursor cursor){
        cursor.moveToFirst();

        ArrayList<T> values= new ArrayList<>();

        for (int i = 0;i<cursor.getCount();i++){
            values.add((T)cursor.getString(1));
            cursor.moveToNext();
        }
        return values;

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

        String data = (String) getItem(position);

        workoutViewHolder.workOutName.setText(data);

        return convertView;

    }




    /*public WorkOutPopUpAdapter(Context context, @LayoutRes int resource, @IdRes int textViewResourceId, List<T> list) {
        super(context, resource,textViewResourceId,list);
    }*/

}
