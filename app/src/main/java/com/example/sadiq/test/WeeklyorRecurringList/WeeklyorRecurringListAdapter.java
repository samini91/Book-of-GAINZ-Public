package com.example.sadiq.test.WeeklyorRecurringList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadiq.test.AddWorkout.WorkoutExerciseSetRepTableView;
import com.example.sadiq.test.AddWorkout.addWorkoutListAdapter;
import com.example.sadiq.test.Database.WeeklyorRecurringDayDB;
import com.example.sadiq.test.R;
import com.example.sadiq.test.WeeklyList.WeekDayVariables;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmList;

/**
 * Created by Mugen on 8/12/2017.
 */

public class WeeklyorRecurringListAdapter extends RecyclerView.Adapter<WeeklyorRecurringListAdapter.WeeklyorRecurringListViewHolder> {


        public interface CustomListener{

                void onWorkoutDayPlusButton(int index);

        }

        CustomListener customListener;


        public void setCustomListener(CustomListener customListener){
                this.customListener = customListener;

        }

        RealmList<WeeklyorRecurringDayDB> weeklyorRecurringDayDBRealmList;


        public WeeklyorRecurringListAdapter(RealmList<WeeklyorRecurringDayDB> weeklyorRecurringDayDBRealmList)
        {
                super();
                this.weeklyorRecurringDayDBRealmList = weeklyorRecurringDayDBRealmList;
        }

        @Override
        public WeeklyorRecurringListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View weeklyorRecurringListViewHolderView = LayoutInflater.from(parent.getContext()).inflate(R.layout.weeklyorrecurringlistviewholder,parent,false);

                WeeklyorRecurringListViewHolder weeklyorRecurringListViewHolder = new WeeklyorRecurringListViewHolder(weeklyorRecurringListViewHolderView);

                weeklyorRecurringListViewHolder.setCustomListener(this.customListener);

                return weeklyorRecurringListViewHolder;
        }

        @Override
        public void onBindViewHolder(WeeklyorRecurringListViewHolder holder, int index) {
                //holder.setIsRecyclable(false);
                holder.bind(weeklyorRecurringDayDBRealmList,index);
                holder.itemView.setTag(weeklyorRecurringDayDBRealmList.get(index));

                //holder.textView.setText(Integer.toString(weeklyorRecurringDayDBRealmList.get(position).getOrder()));
        }

        @Override
        public int getItemCount() {
                return weeklyorRecurringDayDBRealmList.size();
        }


        private void setUpObservers(){

                final WeeklyorRecurringListAdapter weeklyorRecurringListAdapter = this;

                this.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                        @Override
                        public void onChanged() {
                                super.onChanged();


                        }

                        @Override
                        public void onItemRangeChanged(int positionStart, int itemCount) {
                                super.onItemRangeChanged(positionStart, itemCount);
                        }

                        @Override
                        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
                                super.onItemRangeChanged(positionStart, itemCount, payload);
                        }

                        @Override
                        public void onItemRangeInserted(int positionStart, int itemCount) {
                                super.onItemRangeInserted(positionStart, itemCount);
                        }

                        @Override
                        public void onItemRangeRemoved(int positionStart, int itemCount) {
                                super.onItemRangeRemoved(positionStart, itemCount);
                        }

                        @Override
                        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
                                super.onItemRangeMoved(fromPosition, toPosition, itemCount);
                        }
                });


        }


        public  static class WeeklyorRecurringListViewHolder extends RecyclerView.ViewHolder {


                CustomListener customListener;
                @Bind(R.id.textview)
                TextView textView;

                @Bind(R.id.workoutexercisesetreptableview)
                WorkoutExerciseSetRepTableView workoutExerciseSetRepTableView;

                @Bind(R.id.addWorkoutPlusButton)
                ImageButton addWorkoutPlusButton;

                public WeeklyorRecurringListViewHolder(View v) {
                        super(v);
                        ButterKnife.bind(this,v);


                        addWorkoutPlusButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        if(customListener != null)
                                                customListener.onWorkoutDayPlusButton(getAdapterPosition());
                                }
                        });
                }

                public void bind( RealmList<WeeklyorRecurringDayDB> weeklyorRecurringDayDBRealmList,int position){
                       WeeklyorRecurringDayDB weeklyorRecurringDayDB = weeklyorRecurringDayDBRealmList.get(position);

                        textView.setText(WeekDayVariables.WeekDayIntToString(weeklyorRecurringDayDB.getOrder() % 7) + " - Week - " + ((weeklyorRecurringDayDB.getOrder()/7) + 1 ));

                        workoutExerciseSetRepTableView.removeAllViews();
                        if(weeklyorRecurringDayDB.getWorkoutTemplates().size() > 0)
                                workoutExerciseSetRepTableView.bind(weeklyorRecurringDayDB.getWorkoutTemplates().first());
                }

                public void setCustomListener(CustomListener customListener){this.customListener = customListener;}


        }




}
