package com.example.sadiq.test;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadiq.test.Database.ExerciseSetRep;
import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.Database.SetRepWeightDBObject;
import com.example.sadiq.test.ExerciseSetRep.ExerciseSetRepView;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class fragmentTest extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle
                                     savedInstanceState) {

      //  TableLayout a = new TableLayout(getContext());


        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.selectexerciseconfigurationmain, container, false);

//       Regenerate(context);




        //TextView as= new TextView(getActivity());
        //as.setText("asl;dfkj;asldfjk");

        //view.addView(as);

        //ViewGroup view = new LinearLayout(getActivity());





        //RecyclerView a =(RecyclerView) view.findViewById(R.id.fiddle);

        //SetRepWeightListAdapter e = new SetRepWeightListAdapter(getActivity());
        //a.setLayoutManager(new LinearLayoutManager(getActivity()));
        //a.setAdapter(e);
        //e.initNewSetRepWeightDBObject();



       // RecyclerView SetRepWeightList = new RecyclerView(getActivity());

        //SetRepWeightListAdapter adapter= new SetRepWeightListAdapter(getActivity());
        //adapter.initNewSetRepWeigthDBObject();


        //SetRepWeightList.setAdapter(adapter);
        //adapter.initNewSetRepWeightDBObject();
        //view.addView(SetRepWeightList);



  //asdf
        // Inflate the layout for this fragment
        //ViewGroup view = (ViewGroup)inflater.inflate(R.layout.calandar,container,false);
        //TextView a = new TextView(getActivity());

//        a.setText("a;sdkjf;alskdjf");

//        Counter a = new Counter(getActivity());

  //      view.addView(a);

                //=  (ViewGroup)inflater.inflate(R.layout.fragment_blank, container, false);


/*
        TableLayout tbl=new TableLayout(getActivity());
        tbl.setEnabled(true);
        TableRow tr = new TableRow(getActivity());

        EditText test123 = new EditText(getActivity());
        test123.setText("asdfdasdfasdfasdffasdfasdf234");


        tr.addView(test123);

        TableLayout.LayoutParams params=new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        tbl.setLayoutParams(params);


        tbl.addView(tr);
        tbl.setEnabled(true);
        tbl.setActivated(true);
        //tbl.addView(tr.findFocus());
*/
        //ListView list = new ListView(getActivity());

    //    ListActivity list=new ListActivity();


/*
        RelativeLayout test123 = new RelativeLayout(getActivity());

        TableLayout table = new TableLayout(getActivity());
        table.addView(row1());

RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
        RelativeLayout.LayoutParams.MATCH_PARENT);

        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        params.addRule(Gravity.CENTER);
        table.setGravity(Gravity.CENTER);
        test123.addView(table, params);
        test123.setGravity(Gravity.CENTER);
        
        view = test123;



        FloatingActionButton a = ((FloatingActionButton) view.findViewById(R.id.fabe))!=null?((FloatingActionButton) view.findViewById(R.id.fabe)):new FloatingActionButton(getActivity());
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(getView(), "fragementtest", Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });*/

        return root;
    }


    public TableRow row1()
    {
    final TableRow tablerow = new TableRow(getActivity());
        TextView text = new TextView(getActivity());
        text.setText("#This is a test123");
        tablerow.addView(text);
        tablerow.setClickable(true);
        //getActivity().setContentView();


        tablerow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ColorDrawable buttonColor = (ColorDrawable) v.getBackground();

                if (buttonColor != null && buttonColor.getColor() == Color.GREEN) {
                    v.setBackgroundColor(Color.rgb(225, 225, 225));

                } else {
                    v.setBackgroundColor(Color.GREEN);
                }

/*                TableRow tablerow = (TableRow)v.getParent();
                TextView sample = (TextView) tablerow.getChildAt(2);
                String result=sample.getText().toString();*/
                Toast.makeText(getActivity(), "hokaymay", Toast.LENGTH_SHORT).show();

            }
        });


return tablerow;

    }

}
