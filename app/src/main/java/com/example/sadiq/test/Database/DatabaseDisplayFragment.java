package com.example.sadiq.test.Database;

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

/**
 * Created by Sadiq on 2/17/2016.
 */
public class DatabaseDisplayFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle
                                     savedInstanceState) {
        ViewGroup root;

        final Database database = Database.getDatabaseInstance(getActivity());

        database.createRecords("132", "3asdfasdf");
        database.createRecords("13", "3sdf");






        Button getDataButton = new Button(getActivity());
        final TextView DatabaseData = new TextView(getActivity());
        root=(ViewGroup)inflater.inflate(R.layout.emptylinearlayout,container,false);
        getDataButton.setText("getdata");

        getDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = "";
                Cursor cursor = database.selectRecords();
                do {   //
                    data += cursor.getString(0) + "  " + cursor.getString(1) + "\n";
                    //}
                } while (cursor.moveToNext());

                data+=Integer.toString(database.lastInsertRow());

                DatabaseData.setText(data);
            }
        });





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
}
