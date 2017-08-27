package com.example.sadiq.test.WeeklyList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadiq.test.R;

import java.util.ArrayList;

/**
 * Created by Sadiq on 2/22/2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.WeekDayInfoViewHolder> {

        private ArrayList<WeekDayInfo> weekDayInfoList;
        private static Context context;
        private static PopupWindow popupWindow = null;
        private static ListView workOutList;
        private static ViewGroup root;
        private static WorkOutPopUpAdapter<String> popUpListAdapter;
        private static LinearLayout popUpLinearlayout;

        public Adapter(ArrayList<WeekDayInfo> weekDayInfoList, Context context) {
                this.weekDayInfoList = weekDayInfoList;
                this.context = context;
        }


        @Override
        public int getItemCount() {
                return weekDayInfoList.size();
        }

        @Override
        public void onBindViewHolder(WeekDayInfoViewHolder weekDayInfoViewHolder, int i) {
                WeekDayInfo ci = weekDayInfoList.get(i);
                weekDayInfoViewHolder.vDayOfTheWeek.setText(WeekDayVariables.WeekDayIntToString(ci.dayOfTheWeek));
                weekDayInfoViewHolder.setId(ci.Id);

                weekDayInfoViewHolder.layoutList.populateLayoutWithExersices();
        }

        @Override
        public WeekDayInfoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View itemView = LayoutInflater.
                        from(viewGroup.getContext()).
                        inflate(R.layout.cardview, viewGroup, false);


                WorkOutExercisesWeeklyView listtest = new WorkOutExercisesWeeklyView(context);
                return new WeekDayInfoViewHolder(itemView);
        }


        public static class WeekDayInfoViewHolder extends RecyclerView.ViewHolder {

                protected long Id;
                protected TextView vDayOfTheWeek;
                protected WorkOutExercisesWeeklyView layoutList;
                protected ImageView changeWorkout;
                protected TextView WorkOutName;


                public WeekDayInfoViewHolder(View v) {
                        super(v);

                        vDayOfTheWeek = (TextView) v.findViewById(R.id.DayOfTheWeek);

                        layoutList = (WorkOutExercisesWeeklyView) itemView.findViewById(R.id.linearlayoutlist);

                        changeWorkout = (ImageView) itemView.findViewById(R.id.changeworkoutforweek);

                        WorkOutName = (TextView) itemView.findViewById(R.id.WorkOutNameWeeklyCardView);

                        changeWorkout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                        if (popupWindow == null) {
                                                popupWindow = new PopupWindow(context);
                                                popUpLinearlayout = new LinearLayout(context);
                                                root = (ViewGroup) View.inflate(context, R.layout.popuplayoutworkoutlist, popUpLinearlayout);


                                                workOutList = (ListView) root.findViewById(R.id.popUpWorkoutList);


                                        } else {
                                                popupWindow.dismiss();
                                        }


                                        if (popupWindow != null) {


                                                workOutList.setAdapter(popUpListAdapter);
                                                popupWindow.setHeight(context.getResources().getDisplayMetrics().heightPixels * 3 / 4);
                                                popupWindow.setWidth(context.getResources().getDisplayMetrics().widthPixels * 3 / 4);
                                                popupWindow.setContentView(popUpLinearlayout);
                                                popupWindow.setAnimationStyle(R.anim.fadein);
                                                popupWindow.showAtLocation(root, Gravity.BOTTOM, 10, 10);
                                                popupWindow.setFocusable(true);


                                                workOutList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                        @Override
                                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                                popupWindow.dismiss();
                                                                popUpListAdapter.notifyDataSetChanged();
                                                                Toast.makeText(context, Long.toString(Id), Toast.LENGTH_SHORT).show();
                                                                //Toast.makeText(context,Long.toString(popUpListAdapter.getItemId(position)),Toast.LENGTH_SHORT).show();
                                                        }
                                                });

                                        }
                                        popupWindow.update();

                                        Toast.makeText(context, "Clicked plus button" + Long.toString(Id), Toast.LENGTH_SHORT).show();
                                }
                        });


                }

                public void setId(long Id) {

                        this.Id = Id;
                        layoutList.dayOftheWeekId = Id;

                }


        }
}
