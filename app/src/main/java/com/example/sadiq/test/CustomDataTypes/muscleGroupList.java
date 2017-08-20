package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sadiq.test.Database.BodyPartHolderDBObject;
import com.example.sadiq.test.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Sadiq on 2/13/2016.
 */
public class muscleGroupList extends ListView {
    private boolean[] bodyPartsStateinList;
    private String[] bodyParts;
    private BodyPartHolder[] bodyPartHolder;
    muscleGroupListAdpater<BodyPartHolder> bodyPartListAdapter;
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
        

        bodyPartHolder=new BodyPartHolder[bodyParts.length];
        for(int i =0;i<bodyParts.length;i++){
            bodyPartHolder[i]=new BodyPartHolder();
            bodyPartHolder[i].name=bodyParts[i];
        }

        ArrayList<String> temptext= new ArrayList<>();
       // temptext.add("asdf");temptext.add("asdf");temptext.add("asdf");temptext.add("asdf");temptext.add("asdf");

        bodyPartsStateinList=new boolean[bodyParts.length];
        //bodyPartsStateinList=new boolean[];

        //initilization
        for(boolean k : bodyPartsStateinList){k=false;}


        //final ArrayAdapter<String> bodyPartListAdapter= new ArrayAdapter<String>(Activity,R.layout.muscle_list_row_layout,R.id.listText,bodyParts);
        //final ArrayAdapter<String> bodyPartListAdapter= new ArrayAdapter<String>(Activity,R.layout.muscle_list_row_layout,R.id.listText,bodyParts);
        //final muscleGroupListAdpater<String> bodyPartListAdapter= new muscleGroupListAdpater<String>(Activity,R.layout.muscle_list_row_layout,R.id.listText,bodyParts);
        bodyPartListAdapter = new muscleGroupListAdpater<BodyPartHolder>(Activity,R.layout.muscle_list_row_layout,R.id.listText,bodyPartHolder);


        this.setAdapter(bodyPartListAdapter);
        //this.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        this.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BodyPartHolder bodyPartHolderInstance = (BodyPartHolder) parent.getItemAtPosition(position);


                //if (bodyPartsStateinList[position] == false) {
                if(bodyPartHolderInstance.activate==false){

                    bodyPartsStateinList[position] = true;

                    //parent.getChildAt(position).setBackgroundColor(Color.GREEN);

                    //parent.getItemAtPosition(position);
                    bodyPartHolderInstance.activate=true;
                    bodyPartHolderInstance.backGroundColor = Color.GREEN;

                    // view.setBackgroundColor(Color.GREEN);


                }
                else {

                    bodyPartHolderInstance.backGroundColor = Color.WHITE;
                    bodyPartHolderInstance.activate=false;
                    bodyPartsStateinList[position] = false;
                    //parent.getChildAt(position).setBackgroundColor(Color.WHITE);

                    // view.setBackgroundColor(Color.WHITE);
                }
                System.out.println(position + "  :  " + bodyPartsStateinList[position]+bodyPartHolderInstance.activate);
                bodyPartListAdapter.notifyDataSetChanged();
            }
        });


    }

    public void clear(){
        for (int i = 0; i < bodyPartHolder.length; i++) {
            bodyPartHolder[i].backGroundColor = Color.WHITE;
            bodyPartHolder[i].activate=false;
            bodyPartsStateinList[i] = false;
        }
        bodyPartListAdapter.notifyDataSetChanged();
    }

    public BodyPartHolder[] getBodyPartsState(){
        return this.bodyPartHolder;
    }

    public void initBodyPartsState(List<BodyPartHolderDBObject> bodyPartHolderDBObjectList) {

        this.clear();
        HashSet<String> exercisesMovers = new HashSet<>();

        for(BodyPartHolderDBObject bodyPartHolderDBObject : bodyPartHolderDBObjectList)
            exercisesMovers.add(bodyPartHolderDBObject.getName());

        for(int i = 0 ; i< bodyPartHolder.length; i++){
            if(exercisesMovers.contains(bodyPartHolder[i].name) ) {

                bodyPartHolder[i].activate = true;
                bodyPartHolder[i].backGroundColor = Color.GREEN;
            }

        }

        //bodyPartHolder


    }

}
