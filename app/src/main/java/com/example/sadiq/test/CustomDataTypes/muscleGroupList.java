package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sadiq.test.R;

import java.util.ArrayList;

/**
 * Created by Sadiq on 2/13/2016.
 */
public class muscleGroupList extends ListView {
    private boolean[] bodyPartsStateinList;
    private String[] bodyParts;


    public muscleGroupList(Context activityContext){
        super(activityContext);
    }


    public muscleGroupList(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public muscleGroupList(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
/*
    public muscleGroupList(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        super (context, attrs, defStyleAttr, defStyleRes);


    }*/



    public void create(Context Activity){
        bodyParts= getResources().getStringArray(R.array.bodyParts);

        ArrayList<String> temptext= new ArrayList<>();
       // temptext.add("asdf");temptext.add("asdf");temptext.add("asdf");temptext.add("asdf");temptext.add("asdf");

        bodyPartsStateinList=new boolean[bodyParts.length];
        //bodyPartsStateinList=new boolean[];

        //initilization
        for(boolean k : bodyPartsStateinList){k=false;}


        //final ArrayAdapter<String> bodyPartListAdapter= new ArrayAdapter<String>(Activity,R.layout.row_layout,R.id.listText,bodyParts);
        //final ArrayAdapter<String> bodyPartListAdapter= new ArrayAdapter<String>(Activity,R.layout.row_layout,R.id.listText,bodyParts);
        final muscleGroupListAdpater<String> bodyPartListAdapter= new muscleGroupListAdpater<String>(Activity,R.layout.row_layout,R.id.listText,bodyParts);


        this.setAdapter(bodyPartListAdapter);
        this.setChoiceMode(ListView.CHOICE_MODE_SINGLE);




        this.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(position);
                if (bodyPartsStateinList[position] == false) {
                    bodyPartsStateinList[position] = true;
                    //parent.getChildAt(position).setBackgroundColor(Color.GREEN);

                    //parent.getItemAtPosition(position);

                    view.setBackgroundColor(Color.GREEN);


                } else {
                    bodyPartsStateinList[position] = false;
                    //parent.getChildAt(position).setBackgroundColor(Color.WHITE);

                    view.setBackgroundColor(Color.WHITE);

                }
                bodyPartListAdapter.notifyDataSetChanged();
            }
        });
    }

}
