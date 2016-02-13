package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sadiq.test.R;

/**
 * Created by Sadiq on 2/13/2016.
 */
public class muscleGroupList extends ListView {
    private boolean[] bodyPartsStateinList;
    private String[] bodyParts;
    public muscleGroupList(Context activityContext){
        super(activityContext);
    }

    public void create(Context Activity){
        bodyParts= getResources().getStringArray(R.array.bodyParts);
        bodyPartsStateinList=new boolean[bodyParts.length];
        //initilization
        for(boolean k : bodyPartsStateinList){k=false;}

        final ArrayAdapter<String> bodyPartListAdapter= new ArrayAdapter<String>(Activity,R.layout.row_layout,R.id.listText,bodyParts);

        this.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (bodyPartsStateinList[position] == false) {
                    bodyPartsStateinList[position] = true;
                    view.setBackgroundColor(Color.GREEN);
                } else {
                    bodyPartsStateinList[position] = false;
                    view.setBackgroundColor(Color.WHITE);
                }
            }
        });
    }
}
