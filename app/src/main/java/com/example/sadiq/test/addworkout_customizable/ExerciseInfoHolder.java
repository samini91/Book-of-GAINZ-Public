package com.example.sadiq.test.addworkout_customizable;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

import com.example.sadiq.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sadiq on 3/12/2016.
 */

//make this a linear layout and input the information here so you dont have to go the the db every time you bind a new value
public class ExerciseInfoHolder {

    protected String name;

    protected ArrayList<SetRepWeightHolder> information;

    protected LinearLayout informationLinearLayout;

    protected RelativeLayout informationRelativeLayout;

    protected TableLayout informationTableLayout;


    public ExerciseInfoHolder(Context context,String name, ArrayList<SetRepWeightHolder> information) {
        this.name = name;
        this.information = information;
        TableRow row = new TableRow(context);

        informationRelativeLayout = new RelativeLayout(context);

        informationTableLayout = new TableLayout(context);

        informationTableLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));

        informationTableLayout.setStretchAllColumns(true);

        informationTableLayout.setBackgroundColor(Color.CYAN);

        informationLinearLayout = new LinearLayout(context);
        informationLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
       // informationLinearLayout.setBackgroundColor(Color.BLUE);
        informationLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        //informationLinearLayout.setOrientation(LinearLayout.VERTICAL);
        informationLinearLayout.setWeightSum(1);

        for (SetRepWeightHolder val : information) {

            TableRow valRow = new TableRow(context);
            valRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));

            TextView set = new TextView(context);
            TextView rep = new TextView(context);
            TextView weight = new TextView(context);

            set.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            rep.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            weight.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

            set.setGravity(Gravity.CENTER);
            rep.setGravity(Gravity.CENTER);
            weight.setGravity(Gravity.CENTER);

            //set.setBackgroundResource(R.drawable.cell_bg);
            //rep.setBackgroundResource(R.drawable.cell_bg);
            //weight.setBackgroundResource(R.drawable.cell_bg);

            set.setPadding(5, 5, 5, 5);
            rep.setPadding(5, 5, 5, 5);
            weight.setPadding(5, 5, 5, 5);

            set.setText(Integer.toString(val.set));
            rep.setText(Integer.toString(val.rep));
            weight.setText(Float.toString(val.weight));

            valRow.setBackgroundResource(R.drawable.rowrectangle);


            valRow.addView(set);
            valRow.addView(rep);
            valRow.addView(weight);

            informationTableLayout.addView(valRow);
/*
            set.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .33f));
            rep.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .33f));
            weight.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT, .33f));



            informationLinearLayout.addView(set);
            informationLinearLayout.addView(rep);
            informationLinearLayout.addView(weight);

*/
            /*
            set.setGravity(Gravity.LEFT);
            rep.setGravity(Gravity.CENTER);
            weight.setGravity(Gravity.RIGHT);
                  */

        }


        /*
        informationLinearLayout.setOrientation(LinearLayout.VERTICAL);

        for (SetRepWeightHolder val : information) {

            TextView add = new TextView(context);

            add.setText(val.set + "  " + val.rep + "  " + val.weight );
            informationLinearLayout.addView(add);


        }

        */
    }

    public LinearLayout getSetRepWeightLinearLayout(){
        return informationLinearLayout;
    }

    public TableLayout getSetRepWeightTableLayout(){return informationTableLayout;}

    public RelativeLayout getSetRepWeightRelativeLayout(){

        return this.informationRelativeLayout;
    }

}
