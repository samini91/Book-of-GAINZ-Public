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


public class MuscleGroupListAdpater<T> extends ArrayAdapter {
        LayoutInflater mInflater = LayoutInflater.from(getContext());

        static class ViewHolder {
                TextView bodyPart;
                int Color;
        }

        public MuscleGroupListAdpater(Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @NonNull T[] objects) {
                super(context, resource, textViewResourceId, objects);

        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;

                if (convertView == null) {
                        convertView = mInflater.inflate(R.layout.muscle_list_row_layout, parent, false);
                        holder = new ViewHolder();
                        holder.bodyPart = (TextView) convertView.findViewById(R.id.listText);
                        convertView.setTag(holder);
                } else {
                        holder = (ViewHolder) convertView.getTag();
                }

                BodyPartHolder data = (BodyPartHolder) getItem(position);

                holder.bodyPart.setText(data.name);
                holder.bodyPart.setBackgroundColor(data.backGroundColor);

                return convertView;
        }

        public void clear() {


        }
}
