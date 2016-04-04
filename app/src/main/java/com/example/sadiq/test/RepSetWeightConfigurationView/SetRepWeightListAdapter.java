package com.example.sadiq.test.RepSetWeightConfigurationView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadiq.test.Database.SetRepWeightDBObject;

import io.realm.RealmList;

/**
 * Created by Sadiq on 4/1/2016.
 */
public class SetRepWeightListAdapter extends RecyclerView.Adapter<SetRepWeightListAdapter.ViewHolder> {
    private RealmList<SetRepWeightDBObject> SetRepWeightDBObjectList;
    private int currentSet = 1;


    public SetRepWeightListAdapter(Context context){
        super();
        SetRepWeightDBObjectList= new RealmList<>();
        notifyDataSetChanged();
    }

    public RealmList<SetRepWeightDBObject> getAdapterList(){
        return this.SetRepWeightDBObjectList;

    }



    public void initNewSetRepWeightDBObject(){
        SetRepWeightDBObjectList.add(new SetRepWeightDBObject(currentSet));



        notifyDataSetChanged();
        currentSet++;
    }

    public void removeLastAddedSet(){
        if(!SetRepWeightDBObjectList.isEmpty()) {
            SetRepWeightDBObjectList.remove(SetRepWeightDBObjectList.size() - 1);
            currentSet--;
            notifyDataSetChanged();
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder= new ViewHolder(new SetRepWeightList_ItemView(parent.getContext()));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final SetRepWeightDBObject setRepWeightDBObject = SetRepWeightDBObjectList.get(position);
        holder.setRepWeightDBObject_ItemViewInstance.bind(setRepWeightDBObject);

    }

    @Override
    public int getItemCount() {
        return SetRepWeightDBObjectList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public SetRepWeightList_ItemView setRepWeightDBObject_ItemViewInstance;

        public ViewHolder(SetRepWeightList_ItemView itemView) {
            super(itemView);
            setRepWeightDBObject_ItemViewInstance=itemView;

        }

    }

}