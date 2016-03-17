package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sadiq.test.R;

/**
 * Created by Sadiq on 2/13/2016.
 */
public class muscleGroupListAdpater <T> extends ArrayAdapter {
    LayoutInflater mInflater=LayoutInflater.from(getContext());
    static class ViewHolder{
        TextView bodyPart;
        int Color;
    }

    public muscleGroupListAdpater(Context context, @LayoutRes int resource, @IdRes int textViewResourceId,
                                  @NonNull T [] objects){
        super(context, resource, textViewResourceId, objects);

        }



    @Override
    public View getView(int position, View convertView,ViewGroup parent){
        ViewHolder holder;

        if(convertView==null) {
            convertView = mInflater.inflate(R.layout.muscle_list_row_layout, parent, false);
            holder = new ViewHolder();
            holder.bodyPart=(TextView)convertView.findViewById(R.id.listText);
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }

        BodyPartHolder data = (BodyPartHolder) getItem(position);

        holder.bodyPart.setText(data.name);
        holder.bodyPart.setBackgroundColor(data.backGroundColor);

        return convertView;
    }

    public void clear(){



    }
/*
    @Override

    public int getViewTypeCount() {
        System.out.println(getCount());
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        System.out.println(position);
        return position;
    }
*/
}
