package com.example.sadiq.test.MainMenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadiq.test.WeeklyList.WorkOutPopUpAdapter;

import java.util.ArrayList;

/**
 * Created by Sadiq on 2/28/2016.
 */
public class WorkOutInstanceListAdapter extends RecyclerView.Adapter<WorkOutInstanceListAdapter.WorkOutInstanceHolder> {

    ArrayList<WorkOutInstanceInfo> exersicesOfTheDay;
    Context context;
    public WorkOutInstanceListAdapter(ArrayList<WorkOutInstanceInfo> exersicesOfTheDay,Context context){

        this.exersicesOfTheDay=exersicesOfTheDay;
        this.context=context;

    }

    @Override
    public WorkOutInstanceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(WorkOutInstanceHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class WorkOutInstanceHolder extends RecyclerView.ViewHolder{



        public WorkOutInstanceHolder(View v){
            super(v);


        }
    }
}
