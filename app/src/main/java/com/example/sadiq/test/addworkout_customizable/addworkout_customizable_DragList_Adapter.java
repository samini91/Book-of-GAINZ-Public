package com.example.sadiq.test.addworkout_customizable;


import android.graphics.Color;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.sadiq.test.R;
import com.woxthebox.draglistview.DragItemAdapter;

import java.util.ArrayList;

/**
 * Created by Sadiq on 3/11/2016.
 */
public class addworkout_customizable_DragList_Adapter extends  DragItemAdapter<Pair<Long, ExerciseInfoHolder>, addworkout_customizable_DragList_Adapter.ViewHolder> {

    private int mGrabHandleId;

    public addworkout_customizable_DragList_Adapter(ArrayList<Pair<Long, ExerciseInfoHolder>> list, boolean dragOnLongPress) {
        super(dragOnLongPress);
        setHasStableIds(true);
        setItemList(list);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View instance = LayoutInflater.from(parent.getContext()).inflate(R.layout.addworkout_customizable_draglist_cardview, parent, false);

        return new ViewHolder(instance,viewType);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        String text = mItemList.get(position).second.name;
        //holder.setRepWeightList = mItemList.get(position).second.getSetRepWeightLinearLayout();
        holder.setRepWeightList.removeAllViews();
        //holder.setRepWeightList.addView(mItemList.get(position).second.getSetRepWeightLinearLayout());
        holder.setRepWeightList.addView(mItemList.get(position).second.getSetRepWeightTableLayout());
       // holder.setRepWeightList.setBackgroundColor(Color.CYAN);
//        holder.setRepWeightList.addView(mItemList.get(position).second.getSetRepWeightLinearLayout());
        holder.exerciseName.setText(text);
        holder.itemView.setTag(text);
    }


    @Override
    public long getItemId(int position) {
        return mItemList.get(position).first;
    }

    public class ViewHolder extends DragItemAdapter<Pair<Long, ExerciseInfoHolder>, addworkout_customizable_DragList_Adapter.ViewHolder>.ViewHolder {
        //create customized view
        TextView exerciseName;
        //LinearLayout setRepWeightList;
        //RelativeLayout setRepWeightList;
        TableLayout setRepWeightList;
        public ViewHolder(View itemView, int handleResId) {
            super(itemView,itemView.getId());
            exerciseName = (TextView) itemView.findViewById(R.id.ExerciseName);
            setRepWeightList = (TableLayout) itemView.findViewById(R.id.SetRepWeightLinearLayoutList);

        }
    }
}
