package com.example.sadiq.test.Options;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sadiq.test.Database.OptionDB;
import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.Database.UserOption;

import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmResults;

public class Option_RecyclerView_Adapter extends RecyclerView.Adapter<Option_RecyclerView_Adapter.ViewHolder> {
        RealmResults<OptionDB> OptionList;
        LinkedList<OptionDB> test = new LinkedList<>();
        RealmDB realmDB = new RealmDB();

        public Option_RecyclerView_Adapter(Context context) {
                super();
                OptionList = realmDB.getAllOptions();
                OptionDB a = new OptionDB();

                notifyDataSetChanged();
        }

        public RealmResults<OptionDB> getAdapterList() {
                return this.OptionList;
        }

        @Override
        public Option_RecyclerView_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                Option_List_Container vh = new Option_List_Container(parent.getContext());

                ViewHolder v = new ViewHolder(vh);

                return v;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

                final OptionDB OptionDB_Object = OptionList.get(position);

                holder.Option_List_Container_ItemViewInstance.bind(OptionDB_Object);

        }

        @Override
        public int getItemCount() {
                return OptionList.size();
        }


        public static class ViewHolder extends RecyclerView.ViewHolder {
                public Option_List_Container Option_List_Container_ItemViewInstance;
                AlertDialog optionSelectDialog;

                public ViewHolder(final Option_List_Container itemView) {
                        super(itemView);
                        Option_List_Container_ItemViewInstance = itemView;

                        Option_List_Container_ItemViewInstance.setOnLongClickListener(new View.OnLongClickListener() {


                                @Override
                                public boolean onLongClick(View v) {


                                        int radioIndex = -1;
                                        final AlertDialog.Builder radioPopup = new AlertDialog.Builder(v.getContext());

                                        final CharSequence[] radioOptions = new CharSequence[itemView.getOptionValues().size()];

                                        RealmDB realmDB = new RealmDB();
                                        final UserOption userOption = realmDB.getUserOption(itemView.getOptionKey());


                                        for (int i = 0; i < itemView.getOptionValues().size(); i++) {

                                                radioOptions[i] = itemView.getOptionValues().get(i).getValue();

                                                if (radioOptions[i].toString().equals(userOption.getValue()))
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
                                                        } catch (Exception e) {
                                                                e.printStackTrace();
                                                                throw e;
                                                        } finally {
                                                                realmDB.realm.close();
                                                        }

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

                        Option_List_Container_ItemViewInstance.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        Toast.makeText(v.getContext(), Option_List_Container_ItemViewInstance.getOptionValues().get(0).getValue(), Toast.LENGTH_SHORT).show();

                                }
                        });

                }

        }

}

