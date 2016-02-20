package com.example.sadiq.test;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadiq.test.CustomDataTypes.addWorkoutListAdapter;
import com.example.sadiq.test.Database.Database;
import com.woxthebox.draglistview.BoardView;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;

/**
 * Created by Sadiq on 2/16/2016.
 */
public class addWorkout extends Fragment {

    private BoardView mBoardView;

    private addWorkoutListAdapter allExersiceAdapter;
    private addWorkoutListAdapter workOutexersicesAdapter;

    private ArrayList<Pair<Long, String>> allExersice;
    private ArrayList<Pair<Long, String>> workOutExersices;



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle
                                     savedInstanceState){

        final Database database = Database.getDatabaseInstance(getActivity());
        ViewGroup root;
        root= (ViewGroup) inflater.inflate(R.layout.addworkout,container,false);

        allExersice = new ArrayList<>();
        workOutExersices = new ArrayList<>();

        Cursor cursor= database.getAllExersice();
        for (int i =0; i<cursor.getCount();i++){
            allExersice.add(new Pair<>(Long.parseLong(cursor.getString(0)), cursor.getString(1)));
            cursor.moveToNext();
        }

        mBoardView = (BoardView) root.findViewById(R.id.addWorkoutBoardView);
        mBoardView.setSnapToColumnsWhenScrolling(true);
        mBoardView.setSnapToColumnWhenDragging(true);
        mBoardView.setSnapDragItemToTouch(true);

        //mBoardView.setCustomDragItem(new MyDragItem(getActivity(), R.layout.column_item));

        allExersiceAdapter = new addWorkoutListAdapter(getActivity(),allExersice,R.layout.column_item,R.id.item_layout,true);
        workOutexersicesAdapter = new addWorkoutListAdapter(getActivity(),workOutExersices,R.layout.column_item,R.id.item_layout,true);
        //mBoardView.

        View leftColumnHeader = View.inflate(getActivity(),R.layout.boardviewcolumnheader,null);
        ((TextView) leftColumnHeader.findViewById(R.id.columnTextHeader)).setText("Exercises");

        View rightColumnHeader = View.inflate(getActivity(),R.layout.boardviewcolumnheader,null);
        ((TextView) rightColumnHeader.findViewById(R.id.columnTextHeader)).setText("Workout");


        mBoardView.addColumnList(allExersiceAdapter, leftColumnHeader, true);
        mBoardView.addColumnList(workOutexersicesAdapter, rightColumnHeader, true);

        EditText nameOfWorkout= (EditText)root.findViewById(R.id.workoutname);


        nameOfWorkout.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    System.out.println(v.getText());
                    return false;
                }
                return false;
            }
        });

        ViewPager vp=(ViewPager) getActivity().findViewById(R.id.pager);

        //vp.clearFocus();

        final EditText workOutNameView= (EditText)root.findViewById(R.id.workoutname);


        Button clear = (Button)root.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        Button submit = (Button) root.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workOutName = workOutNameView.getText().toString();

                if(!(workOutName.length() ==0) && workOutexersicesAdapter.getItemCount()>0){
                    Database.getDatabaseInstance(getActivity()).addWorkout(workOutName,workOutExersices);
                    Toast.makeText(getActivity(),"Workout Created",Toast.LENGTH_SHORT).show();

                    reset();
                }
                else{
                    Toast.makeText(getActivity(),"You are missing Information",Toast.LENGTH_SHORT).show();
                }
            }
        });




        return root;
    }

    public void reset(){
        allExersice.clear();
        Cursor cursor= Database.getDatabaseInstance(getActivity()).getAllExersice();
        for (int i =0; i<cursor.getCount();i++){
            allExersice.add(new Pair<>(Long.parseLong(cursor.getString(0)), cursor.getString(1)));
            cursor.moveToNext();
        }
        workOutExersices.clear();
        allExersiceAdapter.notifyDataSetChanged();
        workOutexersicesAdapter.notifyDataSetChanged();

    }


    public ArrayList<WorkOutAdapter> getExersices(){


        return new ArrayList<WorkOutAdapter>();
    }
}
