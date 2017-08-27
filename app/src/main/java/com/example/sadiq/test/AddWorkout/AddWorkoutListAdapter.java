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
import android.widget.Toast;

import com.easyandroidanimations.library.ScaleInAnimation;
import com.example.sadiq.test.CustomDataTypes.BodyPartHolder;
import com.example.sadiq.test.CustomDataTypes.MuscleGroupList;
import com.example.sadiq.test.CustomDataTypes.MuscleGroupListAdpater;
import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Database.ExerciseSetRep;
import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.ExerciseSetRep.ExerciseSetRepView;
import com.example.sadiq.test.PopUpWindow.PopUpListView;
import com.example.sadiq.test.R;
import com.woxthebox.draglistview.DragItemAdapter;

import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class AddWorkoutListAdapter extends DragItemAdapter<ExerciseSetRep, AddWorkoutListAdapter.ViewHolder> {

        public interface CustomListener {
                public void onCustomListenerEvent(String Exercise, int location);

        }

        private int mLayoutId;
        private int mGrabHandleId;
        private Context context;
        private PopUpListView popUpListView;
        private PopupWindow popupWindow = null;
        private ViewGroup root;
        private LinearLayout popUpLayout;
        private MuscleGroupList leftList;
        private MuscleGroupList rightList;
        CustomListener customListener;

        public void setCustomListener(CustomListener customListener) {
                this.customListener = customListener;
        }

        public AddWorkoutListAdapter(Context context, RealmList<ExerciseSetRep> list, int layoutId, int grabHandleId, boolean dragOnLongPress) {
                super(dragOnLongPress);
                mLayoutId = layoutId;
                mGrabHandleId = grabHandleId;
                setHasStableIds(true);
                setItemList(list);
                this.context = context;
                popUpListView = new PopUpListView(context);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
                ViewHolder viewHolder = new ViewHolder(view);

                viewHolder.setCustomListener(new CustomListener() {
                        @Override
                        public void onCustomListenerEvent(String Exercise, int location) {
                                if (customListener != null)
                                        customListener.onCustomListenerEvent(Exercise, location);
                        }
                });

                return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                holder.itemView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));

                ExerciseSetRep exerciseSetRep = mItemList.get(position);
                holder.exerciseSetRepView.Bindexercisesetrep(exerciseSetRep, false);
                holder.itemView.setTag(exerciseSetRep);
        }

        @Override
        public long getItemId(int position) {
                return (long) mItemList.get(position).hashCode();
        }

        public class ViewHolder extends DragItemAdapter<String, AddWorkoutListAdapter.ViewHolder>.ViewHolder {
                public ExerciseSetRepView exerciseSetRepView;
                private CustomListener customListener;

                public void setCustomListener(CustomListener customListener) {
                        this.customListener = customListener;
                }

                public ViewHolder(final View itemView) {
                        super(itemView, mGrabHandleId);
                        exerciseSetRepView = (ExerciseSetRepView) itemView.findViewById(R.id.ExerciseSetRepView);

                        this.customListener = null;

                        Button testButton = (Button) itemView.findViewById(R.id.addworkout_column_item_button);

                        testButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        if (customListener != null)
                                                customListener.onCustomListenerEvent(exerciseSetRepView.getExercisename(), getAdapterPosition());
                                }
                        });


                }

                @Override
                public void onItemClicked(View view) {

                        if (popupWindow == null) {
                                popupWindow = new PopupWindow(context);
                                LinearLayout layout = new LinearLayout(context);
                                ViewGroup root = (ViewGroup) layout;
                                popUpLayout = (LinearLayout) View.inflate(context, R.layout.popuplayout, root);
                                leftList = (MuscleGroupList) popUpLayout.findViewById(R.id.leftListView);
                                rightList = (MuscleGroupList) popUpLayout.findViewById(R.id.rightListView);
                                popUpLayout.setBackgroundColor(Color.rgb(115, 147, 158));
                        } else {
                                popupWindow.dismiss();
                        }

                        RealmDB realmDB = new RealmDB();

                        RealmQuery<Exercise> exerciseRealmQuery = RealmQuery.createQuery(realmDB.getRealm(), Exercise.class);
                        exerciseRealmQuery.equalTo("name", exerciseSetRepView.getExercisename());
                        RealmResults<Exercise> exerciseRealmResults = realmDB.getWhereAllExercises(exerciseRealmQuery);

                        BodyPartHolder primaryBodyPartHolder[] = new BodyPartHolder[exerciseRealmResults.get(0).getPrimaryMoversDBObject().size()];

                        for (int i = 0; i < exerciseRealmResults.get(0).getPrimaryMoversDBObject().size(); i++) {
                                primaryBodyPartHolder[i] = new BodyPartHolder();
                                primaryBodyPartHolder[i].name = exerciseRealmResults.get(0).getPrimaryMoversDBObject().get(i).getName();
                        }

                        BodyPartHolder secondaryBodyPartHolder[] = new BodyPartHolder[exerciseRealmResults.get(0).getSecondaryMoversDBObject().size()];

                        for (int i = 0; i < exerciseRealmResults.get(0).getSecondaryMoversDBObject().size(); i++) {
                                secondaryBodyPartHolder[i] = new BodyPartHolder();
                                secondaryBodyPartHolder[i].name = exerciseRealmResults.get(0).getSecondaryMoversDBObject().get(i).getName();
                        }

                        MuscleGroupListAdpater<BodyPartHolder> leftListAdapter = new MuscleGroupListAdpater<>(context, R.layout.muscle_list_row_layout, R.id.listText, primaryBodyPartHolder);

                        MuscleGroupListAdpater<BodyPartHolder> rightListAdapter = new MuscleGroupListAdpater<>(context, R.layout.muscle_list_row_layout, R.id.listText, secondaryBodyPartHolder);

                        leftList.setAdapter(leftListAdapter);
                        rightList.setAdapter(rightListAdapter);

                        popupWindow.setHeight(context.getResources().getDisplayMetrics().heightPixels * 3 / 4);
                        popupWindow.setWidth(context.getResources().getDisplayMetrics().widthPixels * 3 / 4);

                        popupWindow.setContentView(popUpLayout);

                        popupWindow.showAtLocation(popUpLayout, Gravity.BOTTOM, 10, 10);

                        popupWindow.setFocusable(true);

                        popupWindow.update();
                        new ScaleInAnimation(popUpLayout).animate();

                }

                @Override
                public boolean onItemLongClicked(View view) {
                        Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
                        return true;
                }
        }

}
