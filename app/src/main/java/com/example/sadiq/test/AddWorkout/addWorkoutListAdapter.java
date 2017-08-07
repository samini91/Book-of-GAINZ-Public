package com.example.sadiq.test.AddWorkout;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.easyandroidanimations.library.ScaleInAnimation;
import com.example.sadiq.test.CustomDataTypes.BodyPartHolder;
import com.example.sadiq.test.CustomDataTypes.muscleGroupList;
import com.example.sadiq.test.CustomDataTypes.muscleGroupListAdpater;
import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Database.ExerciseSetRep;
import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.ExerciseSetRep.ExerciseSetRepView;
import com.example.sadiq.test.PopUpWindow.PopUpListView;
import com.example.sadiq.test.R;
import com.woxthebox.draglistview.DragItemAdapter;


import java.util.ArrayList;

import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Sadiq on 2/16/2016.
 */


public class addWorkoutListAdapter extends DragItemAdapter<ExerciseSetRep,addWorkoutListAdapter.ViewHolder> {

    public interface CustomListener
    {
        public void onCustomListenerEvent(String Exercise, int location);

    }

    //todo create a custom on adapter update so we can filter the other list as well do remvoe dups
    private int mLayoutId;
    private int mGrabHandleId;
    private Context context;
    private PopUpListView popUpListView;
    private PopupWindow popupWindow=null;
    private ViewGroup root;
    private LinearLayout popUpLayout;
    private muscleGroupList leftList;
    private muscleGroupList rightList;
    CustomListener customListener;

    public void setCustomListener(CustomListener customListener) {
        this.customListener = customListener;
    }

    public addWorkoutListAdapter(Context context, ArrayList<ExerciseSetRep> list, int layoutId, int grabHandleId, boolean dragOnLongPress) {
        super(dragOnLongPress);
        mLayoutId = layoutId;
        mGrabHandleId = grabHandleId;
        setHasStableIds(true);
        setItemList(list);
        this.context=context;
        popUpListView=new PopUpListView(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.setCustomListener(new CustomListener() {
            @Override
            public void onCustomListenerEvent(String Exercise, int location) {
                //need to fire off another event that the main class picks up since viewholder is hidden at the fragment level
                if(customListener != null)
                    customListener.onCustomListenerEvent(Exercise, location);
                //Toast.makeText(context, "adapter on button press", Toast.LENGTH_SHORT).show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));

        ExerciseSetRep exerciseSetRep = mItemList.get(position);
        holder.exerciseSetRepView.Bindexercisesetrep(exerciseSetRep);
        holder.itemView.setTag(exerciseSetRep);
    }

    @Override
    public long getItemId(int position) {
        return (long) mItemList.get(position).hashCode();
    }

    public class ViewHolder extends DragItemAdapter<String, addWorkoutListAdapter.ViewHolder>.ViewHolder {
        //public TextView mText;
        public ExerciseSetRepView exerciseSetRepView;
        private CustomListener customListener;

        public void setCustomListener(CustomListener customListener) {
            this.customListener = customListener;
        }

        public ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId);
          //  mText = (TextView) itemView.findViewById(R.id.text);
            exerciseSetRepView = (ExerciseSetRepView) itemView.findViewById(R.id.ExerciseSetRepView);

            this.customListener = null;

            Button testButton = (Button) itemView.findViewById(R.id.addworkout_column_item_button);

            testButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(customListener != null)
                        customListener.onCustomListenerEvent(exerciseSetRepView.getExercisename(), getAdapterPosition());
                    //FragmentTransaction fragmentTransaction = getSupportTransaction
                }
            });


        }

        @Override
        public void onItemClicked(View view) {

            if(popupWindow==null){
                popupWindow=new PopupWindow(context);
                LinearLayout layout = new LinearLayout(context);
                ViewGroup root= (ViewGroup)layout;
                popUpLayout = (LinearLayout) View.inflate(context, R.layout.popuplayout, root);
                leftList = (muscleGroupList) popUpLayout.findViewById(R.id.leftListView);
                rightList =(muscleGroupList) popUpLayout.findViewById(R.id.rightListView);
                popUpLayout.setBackgroundColor(Color.rgb(115,147,158));
            }
            else{
                popupWindow.dismiss();
            }

            RealmDB realmDB = new RealmDB();

            RealmQuery<Exercise> exerciseRealmQuery = RealmQuery.createQuery(realmDB.getRealm(),Exercise.class);
            exerciseRealmQuery.equalTo("name",exerciseSetRepView.getExercisename());
            RealmResults<Exercise> exerciseRealmResults = realmDB.getWhereAllExercises(exerciseRealmQuery);

            BodyPartHolder primaryBodyPartHolder[] = new BodyPartHolder[exerciseRealmResults.get(0).getPrimaryMoversDBObject().size()];

            for (int i=0;i<exerciseRealmResults.get(0).getPrimaryMoversDBObject().size();i++){
                primaryBodyPartHolder[i]=new BodyPartHolder();
                primaryBodyPartHolder[i].name = exerciseRealmResults.get(0).getPrimaryMoversDBObject().get(i).getName();
            }

            BodyPartHolder secondaryBodyPartHolder [] = new BodyPartHolder[exerciseRealmResults.get(0).getSecondaryMoversDBObject().size()];

            for (int i=0;i<exerciseRealmResults.get(0).getSecondaryMoversDBObject().size();i++){
                secondaryBodyPartHolder[i]=new BodyPartHolder();
                secondaryBodyPartHolder[i].name = exerciseRealmResults.get(0).getSecondaryMoversDBObject().get(i).getName();
            }

            muscleGroupListAdpater<BodyPartHolder> leftListAdapter=new muscleGroupListAdpater<>(context,R.layout.muscle_list_row_layout,R.id.listText,primaryBodyPartHolder);

            muscleGroupListAdpater<BodyPartHolder> rightListAdapter=new muscleGroupListAdpater<>(context,R.layout.muscle_list_row_layout,R.id.listText,secondaryBodyPartHolder);

            leftList.setAdapter(leftListAdapter);
            rightList.setAdapter(rightListAdapter);
            /*
            Button dismissButton = (Button)popUpLayout.findViewById(R.id.dismisspopupwindow);
            dismissButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });*/
            //layout.setOrientation(LinearLayout.HORIZONTAL);
            //popupWindow.setContentView(popUpLayout);
            //popupWindow.setHeight(((View) view.getParent()).getHeight());
            //popupWindow.setWidth(((View) view.getParent()).getWidth());
            //popupWindow.setHeight(popUpLayout.getHeight());
            //popupWindow.setWidth(popUpLayout.getWidth());
            //popUpLayout.setClickable(true);
            //root.setClickable(true);
            /*
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("askdfjal;skdjf;laskjdfl;aksjdfl;kajs;df");
                    popupWindow.dismiss();
                }
            });
*/
            //popupWindow.setHeight(1500);
            popupWindow.setHeight(context.getResources().getDisplayMetrics().heightPixels*3/4);
            popupWindow.setWidth(context.getResources().getDisplayMetrics().widthPixels*3/4);
            //layout.addView(listViewleft);

            //layout.addView(listViewright);

//            popupWindow.setContentView(layout);
            popupWindow.setContentView(popUpLayout);

            popupWindow.showAtLocation(popUpLayout, Gravity.BOTTOM, 10, 10);

            popupWindow.setFocusable(true);

            popupWindow.update();
            new ScaleInAnimation(popUpLayout).animate();

            //new UnfoldAnimation(leftList).animate();
        }

        @Override
        public boolean onItemLongClicked(View view) {
            Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

}
