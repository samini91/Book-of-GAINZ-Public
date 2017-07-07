package com.example.sadiq.test.Options;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Debug;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sadiq.test.Database.OptionDB;
import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.Database.RealmString;
import com.example.sadiq.test.Database.UserOption;
import com.example.sadiq.test.R;
import com.example.sadiq.test.RepSetWeightConfigurationView.SetRepWeightList_ItemView;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Mugen on 11/24/2016.
 */

public class Option_RecyclerView_Adapter extends RecyclerView.Adapter<Option_RecyclerView_Adapter.ViewHolder>{
RealmResults<OptionDB> OptionList;
        LinkedList<OptionDB> test = new LinkedList<>();
RealmDB realmDB = new RealmDB();

public Option_RecyclerView_Adapter(Context context){
        super();
        OptionList = realmDB.getAllOptions();
        OptionDB a = new OptionDB();
        a.setOption("asdf");


        test.add(a);
        //test.add("qer");

        //OptionList.
        notifyDataSetChanged();
        }

public RealmResults<OptionDB> getAdapterList(){
        return this.OptionList;
        }

@Override
public Option_RecyclerView_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // viewHolder = LayoutInflater.from(parent.getContext()).inflate(new Option_List_Container(parent.getContext()),parent,false);
        //ViewHolder viewHolder = LayoutInflater.from(parent.getContext()).inflate( ,parent,false);
        //ViewHolder viewHolder = LayoutInflater.from(parent.getContext()).inflate()
        Option_List_Container vh = new Option_List_Container(parent.getContext());
        //ViewHolder viewHolder=new ViewHolder(new Option_List_Container(parent.getContext()));
        ViewHolder v = new ViewHolder(vh);
        //ViewHolder viewHolder= (ViewHolder) new Option_List_Container(parent.getContext());
        return v;
        }

@Override
public void onBindViewHolder(ViewHolder holder,int position){

        final OptionDB OptionDB_Object = OptionList.get(position);
   //     final OptionDB OptionDB_Object = test.get(position);
        holder.Option_List_Container_ItemViewInstance.bind(OptionDB_Object);

        }

@Override
public int getItemCount(){return OptionList.size();}
//        public int getItemCount(){return test.size();}

public static class ViewHolder extends RecyclerView.ViewHolder {
        public Option_List_Container Option_List_Container_ItemViewInstance;
        AlertDialog optionSelectDialog;
        public ViewHolder(final Option_List_Container itemView) {
                super(itemView);
                Option_List_Container_ItemViewInstance = itemView;

                Option_List_Container_ItemViewInstance.setOnLongClickListener(new View.OnLongClickListener(){


                        @Override
                        public boolean onLongClick(View v) {


                                int radioIndex = -1;
                                final AlertDialog.Builder radioPopup = new AlertDialog.Builder(v.getContext());

                                //Build Character Array

                                final CharSequence[] radioOptions = new CharSequence[itemView.getOptionValues().size()];

                                RealmDB realmDB = new RealmDB();
                                final UserOption userOption = realmDB.getUserOption(itemView.getOptionKey());


                                for (int i = 0; i < itemView.getOptionValues().size(); i++) {

                                        radioOptions[i] = itemView.getOptionValues().get(i).getValue();

                                        if(radioOptions[i].toString().equals(userOption.getValue()))
                                                radioIndex = i;

                                }
                                radioPopup.setSingleChoiceItems(radioOptions, radioIndex, new AlertDialog.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, final int which) {

                                                        final RealmDB realmDB = new RealmDB();
                                                        try {
                                                                realmDB.realm.executeTransaction(new Realm.Transaction() {
                                                                        @Override
                                                                        public void execute(Realm realm) {
                                                                    userOption.setValue(radioOptions[which].toString());
                                                                        }
                                                                });
                                                        }
                                                        catch (Exception e )
                                                        {
                                                                e.printStackTrace();
                                                                throw e;
                                                        }
                                                        finally {
                                                                realmDB.realm.close();
                                                        }
                                                        //optionSelectDialog.dismiss();
                                                        //update the record in the db
                                                        optionSelectDialog.hide();
                                                }
                                        });

                                if (optionSelectDialog != null)
                                        optionSelectDialog.hide();

                                optionSelectDialog = radioPopup.create();

                                optionSelectDialog.show();

                                return false;
                        }
                });
                /*
                Option_List_Container_ItemViewInstance.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                                //, "", Toast.LENGTH_SHORT).show();
                                //if(event.getPressure() == MotionEvent.ACTION_BUTTON_RELEASE)
                                if(event.getActionMasked() == MotionEvent.ACTION_DOWN && event.getDownTime() >400000000) {


                                        //Toast.makeText(v.getContext(),itemView.getOptionValues().get(0).getValue(),Toast.LENGTH_LONG);
                                        final AlertDialog.Builder radioPopup = new AlertDialog.Builder(v.getContext());

                                        //Build Character Array

                                        final CharSequence[] radioOptions = new CharSequence[itemView.getOptionValues().size()];

                                        for (int i = 0; i < itemView.getOptionValues().size(); i++) {
                                                radioOptions[i] = itemView.getOptionValues().get(i).getValue();
                                        }

                                        radioPopup.setSingleChoiceItems(radioOptions, -1, new AlertDialog.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                        int i = 0;
                                                        //optionSelectDialog.dismiss();
                                                        optionSelectDialog.hide();
                                                }
                                        });

                                        if (optionSelectDialog != null)
                                                optionSelectDialog.hide();

                                        optionSelectDialog = radioPopup.create();

                                        optionSelectDialog.show();
                                }

                                return true;
                        }
                });
*/
                Option_List_Container_ItemViewInstance.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                Toast.makeText(v.getContext(),Option_List_Container_ItemViewInstance.getOptionValues().get(0).getValue(),Toast.LENGTH_SHORT).show();

                        }
                });

        }

}

}

