package com.example.sadiq.test.WeeklyList;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sadiq.test.Database.Database;
import com.example.sadiq.test.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Sadiq on 2/22/2016.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.WeekDayInfoViewHolder> {

    private ArrayList<WeekDayInfo> weekDayInfoList;
    private static Context context;
    private static PopupWindow popupWindow=null;
    private static ListView workOutList;
    private static ViewGroup root;
    private static WorkOutPopUpAdapter<String> popUpListAdapter;
    private  static LinearLayout popUpLinearlayout;

    public Adapter(ArrayList<WeekDayInfo> weekDayInfoList,Context context) {
        this.weekDayInfoList=weekDayInfoList;
        this.context=context;
        popUpListAdapter= new WorkOutPopUpAdapter(context,R.layout.weeklyschedule_workout_rowlayout,R.id.listText, Database.getDatabaseInstance(context).getAllWorkouts());
    }


    @Override
    public int getItemCount() {
        return weekDayInfoList.size();
    }

    @Override
    public void onBindViewHolder(WeekDayInfoViewHolder weekDayInfoViewHolder, int i) {
        WeekDayInfo ci = weekDayInfoList.get(i);
        //weekDayInfoViewHolder.vId.setText(Long.toString(ci.Id));
        weekDayInfoViewHolder.vDayOfTheWeek.setText(WeekDayVariables.WeekDayIntToString(ci.dayOfTheWeek));
        weekDayInfoViewHolder.setId(ci.Id);

        //weekDayInfoViewHolder.WorkOutName.setText();

        weekDayInfoViewHolder.layoutList.populateLayoutWithExersices();


        //probably what im going to end up doing
        /*
        LinearLayout list = (LinearLayout)findViewById(R.id.list_recycled_parts);
        for (int i=0; i<products.size(); i++) {
            Product product = products.get(i);
            View vi = inflater.inflate(R.layout.product_item, null);
            list.addView(vi);
        }
        */
        //would insert values here
        //ArrayList<String> e = new ArrayList<>();
        //e.add("qwer");e.add("qwer");e.add("qwer");e.add("qwer");e.add("qwer");e.add("qwer");e.add("qwer");e.add("qwer");
        //ArrayAdapter<String> f= new ArrayAdapter<String>(context,R.layout.muscle_list_row_layout,R.id.listText,e);

        //weekDayInfoViewHolder.list.setAdapter(f);

        //weekDayInfoViewHolder.list.setF
    }

    @Override
    public WeekDayInfoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.cardview, viewGroup, false);


        WorkOutExercisesWeeklyView listtest = new WorkOutExercisesWeeklyView(context);

        //listtest.setBackgroundColor(Color.BLUE);
        //Typeface custom_font = Typeface.createFromAsset(context.getAssets(), "fonts/font name.ttf");
        //listtest.setTypeface(custom_font);

        /*itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Clicked plus button",Toast.LENGTH_SHORT).show();
            }
        });*/

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

            layoutList= (WorkOutExercisesWeeklyView)itemView.findViewById(R.id.linearlayoutlist);

            changeWorkout = (ImageView)itemView.findViewById(R.id.changeworkoutforweek);

            WorkOutName = (TextView) itemView.findViewById(R.id.WorkOutNameWeeklyCardView);

            changeWorkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(popupWindow==null){
                        popupWindow=new PopupWindow(context);
                        popUpLinearlayout = new LinearLayout(context);
                        root = (ViewGroup)View.inflate(context, R.layout.popuplayoutworkoutlist, popUpLinearlayout);


                        workOutList= (ListView) root.findViewById(R.id.popUpWorkoutList);


                    }
                    else{
                        popupWindow.dismiss();
                    }


                    if(popupWindow!=null){



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
                                //TODO should set up a dialog box to make sure the user wants to change the work out I can get both Ids right now

                                Database.getDatabaseInstance(context).addWeeklyExersice(Id, popUpListAdapter.getItemId(position));
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
        public void setId(long Id){

            this.Id=Id;
            layoutList.dayOftheWeekId=Id;

        }


    }
}
