package com.example.sadiq.test;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Sadiq on 2/10/2016.
 */
public class WorkOutAdapter extends ArrayAdapter<WorkoutTemplate> {
    private final Context context;
    private final ArrayList<WorkoutTemplate> data;
    private final int layoutResourceId;

    public class multipleTextViewHandler{
        TextView BodyPart;
        TextView Exersice;
    }

    public WorkOutAdapter(Context context,int layoutResourceId, ArrayList<WorkoutTemplate> data){
        super(context,layoutResourceId,data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        multipleTextViewHandler handler = null;
        WorkoutTemplate t = data.get(position);

        if(row==null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            handler = new multipleTextViewHandler();


            handler.BodyPart=(TextView)row.findViewById(R.id.bodypart);

//            handler.Exersice=(TextView)row.findViewById(R.id.exersice);

            handler.BodyPart.setText(t.Name);

            LinearLayout linearLayout= (LinearLayout)row.findViewById(R.id.workoutlistview);

            for (String exer:t.Exersice){
                TextView a = new TextView(this.getContext());
                a.setText(exer);
                a.setGravity(Gravity.CENTER);

                //a.setLayoutParams(new LinearLayout.LayoutParams(30,231));
                linearLayout.addView(a);
                // es.inflate(layoutResourceId,);
                //a.et
                System.out.println("fasdf");
            }



            row.setTag(handler);
        }
        else {
            handler=(multipleTextViewHandler)row.getTag();

        }
/*
        handler.BodyPart.setText(t.Name);

        LinearLayout linearLayout= (LinearLayout)row.findViewById(R.id.workoutlistview);

        for (String exer:t.Exersice){
            TextView a = new TextView(this.getContext());
            a.setText(exer);
            a.setGravity(Gravity.CENTER);

            //a.setLayoutParams(new LinearLayout.LayoutParams(30,231));
            linearLayout.addView(a);
            // es.inflate(layoutResourceId,);
            //a.et
            System.out.println("fasdf");
        }
*/

        return row;

    }


    @Override

    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    }
