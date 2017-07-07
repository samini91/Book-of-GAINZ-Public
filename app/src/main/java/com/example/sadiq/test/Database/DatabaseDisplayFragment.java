package com.example.sadiq.test.Database;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.sadiq.test.Database.MyDatabaseHelper;
import com.example.sadiq.test.Database.Database;
import com.example.sadiq.test.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by Sadiq on 2/17/2016.
 */
public class DatabaseDisplayFragment extends Fragment {

    ViewGroup root;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle
                                     savedInstanceState) {

        final Database database = Database.getDatabaseInstance(getActivity());
        root=(ViewGroup)inflater.inflate(R.layout.emptylinearlayout,container,false);
        Button getDataButton = new Button(getActivity());
        final TextView DatabaseData = new TextView(getActivity());

        //RealmDatabasetest();


        //Realm realm = Realm.getInstance(new RealmConfiguration.Builder(getActivity()).build());


        getDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "";
                RealmDB realm = new RealmDB();
                RealmResults<Exercise> swag = realm.getAllExercise();
                //RealmResults<Exercise> swag = realm.where(Exercise.class).findAll();
                System.out.println(swag.size());
                for(Exercise exercise:swag){
                    data += exercise.getName();
                }
                DatabaseData.setText(data);
            }
        });


        //DatabaseData.setText(swag.get(0).getName());

        getDataButton.setText("getdata");



        Button button= new Button(getActivity());
        button.setText("DeleteDatabase");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.getDatabaseInstance(getActivity()).deleteDatabase(getActivity());

            }
        });

        root.addView(DatabaseData);
        root.addView(button);
        root.addView(getDataButton);

        return root;
    }

    public void RealmDatabasetest(){
        //int nextID = (int) (realm.where(dbObj.class).maximumInt("id") + 1);
        //Exercise instance = new Exercise("jinens",null,null);
        //instance.setId(1);
      //  RealmConfiguration realmConfig = new RealmConfiguration.Builder(getActivity()).build();


        //Realm.setDefaultConfiguration(realmConfig);

        //Realm realm = Realm.getInstance(realmConfig);

        //realm.beginTransaction();
        //realm.copyToRealmOrUpdate(instance);
        //realm.commitTransaction();


    }
/*
    public void sqlitedatabasetest() {
        getDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "";
                //Cursor cursor = database.selectRecords();
                //Cursor cursor = database.getPrimaryMoverForAllExersices();
                //Cursor cursor = database.getPrimaryMoverForExersice(4);
                //Cursor cursor = database.getAllExersice();
                //Cursor cursor = database.getAllWorkouts();


                /*
                do {   //
                    data += cursor.getString(0) + "  " + cursor.getString(1) + "\n";
                    //}
                } while (cursor.moveToNext());
                */
    /*
                for (int j = 1; j <= 7; j++) {
                    Cursor cursor = database.getWeeklyExersice(j);
                    for (int i = 0; i < cursor.getColumnCount(); i++) {
                        data += cursor.getColumnName(i) + "   ";
                    }
                    data += "\n";
                    for (int i = 0; i < cursor.getCount(); i++) {
                        for (int k = 0; k < cursor.getColumnCount(); k++) {
                            data += cursor.getString(k) + "   ";
                        }
                        data += "\n";
                        cursor.moveToNext();
                    }
                }


                data += Integer.toString(database.lastInsertRow());

                DatabaseData.setText(data);
            }
        });







    }
*/
}
