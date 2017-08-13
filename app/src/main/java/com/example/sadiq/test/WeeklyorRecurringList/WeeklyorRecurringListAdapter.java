package com.example.sadiq.test.WeeklyorRecurringList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadiq.test.Database.WeeklyorRecurringDayDB;
import com.example.sadiq.test.R;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmList;

/**
 * Created by Mugen on 8/12/2017.
 */

public class WeeklyorRecurringListAdapter extends RecyclerView.Adapter<WeeklyorRecurringListAdapter.WeeklyorRecurringListViewHolder> {

        RealmList<WeeklyorRecurringDayDB> weeklyorRecurringDayDBRealmList;


        public WeeklyorRecurringListAdapter(RealmList<WeeklyorRecurringDayDB> weeklyorRecurringDayDBRealmList)
        {
                super();
                this.weeklyorRecurringDayDBRealmList = weeklyorRecurringDayDBRealmList;
        }



        @Override
        public WeeklyorRecurringListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View weeklyorRecurringListViewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.weeklyorrecurringlistviewholder,parent,false);

                return new WeeklyorRecurringListViewHolder(weeklyorRecurringListViewHolder);
        }

        @Override
        public void onBindViewHolder(WeeklyorRecurringListViewHolder holder, int position) {
                holder.textView.setText(Integer.toString(weeklyorRecurringDayDBRealmList.get(position).getOrder()));
        }

        @Override
        public int getItemCount() {
                return weeklyorRecurringDayDBRealmList.size();
        }


        public static class WeeklyorRecurringListViewHolder extends RecyclerView.ViewHolder {

                @Bind(R.id.textview)
                TextView textView;
                public WeeklyorRecurringListViewHolder(View v) {
                        super(v);

                        ButterKnife.bind(this,v);

                }


        }

}
