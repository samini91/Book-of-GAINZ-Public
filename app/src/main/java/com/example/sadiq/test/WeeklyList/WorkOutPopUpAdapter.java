package com.example.sadiq.test.WeeklyList;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sadiq.test.R;

import java.util.ArrayList;
import java.util.List;

public class WorkOutPopUpAdapter<T> extends BaseAdapter {
        LayoutInflater mInflater;
        public ArrayList<Pair<Long, String>> values;

        private static class WorkoutViewHolder {
                TextView workOutName;
                long workOutId;
        }


        public WorkOutPopUpAdapter(Context context, @LayoutRes int resource, @IdRes int textViewResourceId, Cursor cursor) {
                this.createWorkOutPopUpAdapter(cursor);
                mInflater = LayoutInflater.from(context);
        }

        public List<Pair<Long, String>> createWorkOutPopUpAdapter(Cursor cursor) {
                cursor.moveToFirst();
                values = new ArrayList<>();
                for (int i = 0; i < cursor.getCount(); i++) {
                        values.add(new Pair<>(Long.parseLong(cursor.getString(0)), cursor.getString(1)));
                        cursor.moveToNext();
                }
                return values;
        }

        @Override
        public int getCount() {
                return values.size();
        }

        @Override
        public Object getItem(int position) {
                return values.get(position);
        }

        @Override
        public long getItemId(int position) {
                return values.get(position).first;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                WorkoutViewHolder workoutViewHolder;
                if (convertView == null) {
                        convertView = mInflater.inflate(R.layout.weeklyschedule_workout_rowlayout, parent, false);
                        workoutViewHolder = new WorkoutViewHolder();
                        workoutViewHolder.workOutName = (TextView) convertView.findViewById(R.id.listText);
                        convertView.setTag(workoutViewHolder);
                } else {
                        workoutViewHolder = (WorkoutViewHolder) convertView.getTag();
                }

                String data = ((Pair<Long, String>) getItem(position)).second;

                workoutViewHolder.workOutName.setText(data);

                return convertView;

        }
}
