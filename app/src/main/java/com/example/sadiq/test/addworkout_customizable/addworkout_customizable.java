package com.example.sadiq.test.addworkout_customizable;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
//import android.support.v4.app.Fragment;

//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.transition.Explode;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.easyandroidanimations.library.BounceAnimation;
import com.easyandroidanimations.library.ExplodeAnimation;
import com.easyandroidanimations.library.FlipHorizontalAnimation;
import com.easyandroidanimations.library.ScaleInAnimation;
import com.example.sadiq.test.R;
import com.example.sadiq.test.SelectExerciseConfiguration.SelectExerciseConfiguration;
import com.woxthebox.draglistview.DragItem;
import com.woxthebox.draglistview.DragListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Sadiq on 3/11/2016.
 */
public class addworkout_customizable extends Fragment {
    DragListView dragListView;
    FloatingActionButton showPopUpMenuButton;
    PopupWindow popupWindowEnterData;
    ViewGroup root;
    ViewGroup  popUpRoot;
    LinearLayout popUpLinearlayout;

    Fragment SelectExerciseConfiguration;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState){



         root = (ViewGroup)inflater.inflate(R.layout.addworkout_customizable_main, container, false);

         dragListView = (DragListView) root.findViewById(R.id.DragListRecyclerView);

         showPopUpMenuButton = (FloatingActionButton)root.findViewById(R.id.showPopUpButton);

        new BounceAnimation(dragListView).animate();


        ArrayList<Pair<Long,ExerciseInfoHolder>> pairArrayList = new ArrayList<>();
        //test123 value

        SetRepWeightHolder a = new SetRepWeightHolder(1,2,495);
        ArrayList<SetRepWeightHolder> at= new ArrayList<>();
        at.add(a);
        ArrayList<SetRepWeightHolder> at1= new ArrayList<>();
        at1.add(new SetRepWeightHolder(4, 23, 4));
        at1.add(new SetRepWeightHolder(4, 23, 4));

        pairArrayList.add(new Pair<Long, ExerciseInfoHolder>((long) 3, new ExerciseInfoHolder(getActivity(), "Squats", at)));
        pairArrayList.add(new Pair<Long, ExerciseInfoHolder>((long) 5, new ExerciseInfoHolder(getActivity(), "dubuler", at1)));

        addworkout_customizable_DragList_Adapter adapter = new addworkout_customizable_DragList_Adapter(pairArrayList,true);

        dragListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dragListView.setAdapter(adapter, false);




        //SetUpPopUpButton();
        SetUpSelectExerciseConfigurationButton(container);

        return root;
    }

    private void SetUpSelectExerciseConfigurationButton(final ViewGroup container){

            SelectExerciseConfiguration = new SelectExerciseConfiguration();


        showPopUpMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (getFragmentManager().findFragmentByTag("SelectExerciseConfigurationFragmentTag") == null) {
                //getFragmentManager().beginTransaction().replace(((ViewGroup) getView().getParent()).getId(), SelectExerciseConfiguration, "SelectExerciseConfigurationFragmentTag")
                //getFragmentManager().beginTransaction().replace(container.getId(), SelectExerciseConfiguration)
                  //      .commit();
                FragmentManager fragmentManager = getFragmentManager();

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragmentTransaction.add(SelectExerciseConfiguration,SelectExerciseConfiguration.toString());

                fragmentTransaction.setCustomAnimations(R.animator.card_flip_right_in, R.animator.card_flip_right_out, R.animator.card_flip_left_in, R.animator.card_flip_left_out);

                fragmentTransaction.replace( R.id.addworkout_customizable_container, SelectExerciseConfiguration);
                fragmentTransaction.addToBackStack(SelectExerciseConfiguration.toString());
                fragmentTransaction.commit();




                //}
                //else
                {
                    //getFragmentManager().beginTransaction().replace(((ViewGroup)getView().getParent()).getId(),SelectExerciseConfiguration,"SelectExerciseConfigurationFragmentTag")
                    //      .commit();

                }

            }
        });


        //add(new SelectExerciseConfiguration(),"SelectExerciseConfigurationFragment").commit();


    }

    private void SetUpPopUpButton() {

        showPopUpMenuButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                if (popupWindowEnterData == null) {

                    popupWindowEnterData = new PopupWindow(getActivity());
                    popUpLinearlayout = new LinearLayout(getActivity());
                    //popUpLinearlayout.setBackgroundColor(Color.TRANSPARENT);
                    popUpRoot = (ViewGroup) View.inflate(getActivity(), R.layout.popupenterdata, popUpLinearlayout);
                    popUpRoot.setBackgroundColor(Color.GRAY);
                    //popupWindowEnterData.setAnimationStyle(R.style.Popupflip);

                } else {
                    //new ExplodeAnimation(popUpRoot).animate();
                    popupWindowEnterData.dismiss();

                }

                popupWindowEnterData.setHeight(getContext().getResources().getDisplayMetrics().heightPixels * 9 / 10);
                popupWindowEnterData.setWidth(getContext().getResources().getDisplayMetrics().widthPixels * 9 / 10);

                popupWindowEnterData.setContentView(popUpRoot);
                //popupWindowEnterData.setAnimationStyle(R.anim.fadein);


                popupWindowEnterData.showAtLocation(popUpRoot, Gravity.BOTTOM, 10, 10);
                //popupWindowEnterData.showAtLocation();
                popupWindowEnterData.setFocusable(true);
                popupWindowEnterData.update();
                new ScaleInAnimation(popUpRoot).animate();
                //show pop up menu


            }
        });

    }
    private static class MyDragItem extends DragItem {

        public MyDragItem(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public void onBindDragView(View clickedView, View dragView) {
            CharSequence text = ((TextView) clickedView.findViewById(R.id.text)).getText();
            ((TextView) dragView.findViewById(R.id.text)).setText(text);
            //dragView.setBackgroundColor(dragView.getResources().getColor(R.color.list_item_background));
        }
    }

}
