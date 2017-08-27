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

public class MuscleGroupList extends ListView {
        private boolean[] bodyPartsStateinList;
        private String[] bodyParts;
        private BodyPartHolder[] bodyPartHolder;
        MuscleGroupListAdpater<BodyPartHolder> bodyPartListAdapter;

        public MuscleGroupList(Context activityContext) {
                super(activityContext);
        }


        public MuscleGroupList(Context context, AttributeSet attrs, int defStyle) {
                super(context, attrs, defStyle);
        }

        public MuscleGroupList(Context context, AttributeSet attrs) {
                super(context, attrs);
        }

        public void create(Context Activity) {
                bodyParts = getResources().getStringArray(R.array.bodyParts);


                bodyPartHolder = new BodyPartHolder[bodyParts.length];
                for (int i = 0; i < bodyParts.length; i++) {
                        bodyPartHolder[i] = new BodyPartHolder();
                        bodyPartHolder[i].name = bodyParts[i];
                }

                ArrayList<String> temptext = new ArrayList<>();

                bodyPartsStateinList = new boolean[bodyParts.length];

                //initilization
                for (boolean k : bodyPartsStateinList) {
                        k = false;
                }

                bodyPartListAdapter = new MuscleGroupListAdpater<BodyPartHolder>(Activity, R.layout.muscle_list_row_layout, R.id.listText, bodyPartHolder);

                this.setAdapter(bodyPartListAdapter);


                this.setOnItemClickListener(new ListView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                BodyPartHolder bodyPartHolderInstance = (BodyPartHolder) parent.getItemAtPosition(position);

                                if (bodyPartHolderInstance.activate == false) {

                                        bodyPartsStateinList[position] = true;

                                        bodyPartHolderInstance.activate = true;
                                        bodyPartHolderInstance.backGroundColor = getResources().getColor(R.color.caldroid_sky_blue);
                                } else {

                                        bodyPartHolderInstance.backGroundColor = Color.WHITE;
                                        bodyPartHolderInstance.activate = false;
                                        bodyPartsStateinList[position] = false;
                                }
                                bodyPartListAdapter.notifyDataSetChanged();
                        }
                });

        }

        public void clear() {
                for (int i = 0; i < bodyPartHolder.length; i++) {
                        bodyPartHolder[i].backGroundColor = Color.WHITE;
                        bodyPartHolder[i].activate = false;
                        bodyPartsStateinList[i] = false;
                }
                bodyPartListAdapter.notifyDataSetChanged();
        }

        public BodyPartHolder[] getBodyPartsState() {
                return this.bodyPartHolder;
        }

        public void initBodyPartsState(List<BodyPartHolderDBObject> bodyPartHolderDBObjectList) {

                this.clear();
                HashSet<String> exercisesMovers = new HashSet<>();

                for (BodyPartHolderDBObject bodyPartHolderDBObject : bodyPartHolderDBObjectList)
                        exercisesMovers.add(bodyPartHolderDBObject.getName());

                for (int i = 0; i < bodyPartHolder.length; i++) {
                        if (exercisesMovers.contains(bodyPartHolder[i].name)) {

                                bodyPartHolder[i].activate = true;
                                bodyPartHolder[i].backGroundColor = getResources().getColor(R.color.caldroid_sky_blue);
                        }

                }

        }

}
