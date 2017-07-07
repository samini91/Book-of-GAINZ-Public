package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;



import com.easyandroidanimations.library.ExplodeAnimation;
import com.easyandroidanimations.library.FlipHorizontalAnimation;
import com.easyandroidanimations.library.ScaleInAnimation;
import com.easyandroidanimations.library.SlideInAnimation;
import com.easyandroidanimations.library.UnfoldAnimation;
import com.example.sadiq.test.Database.Database;
import com.example.sadiq.test.PopUpWindow.PopUpListView;
import com.example.sadiq.test.R;
import com.woxthebox.draglistview.DragItemAdapter;


import java.util.ArrayList;

/**
 * Created by Sadiq on 2/16/2016.
 */
public class addWorkoutListAdapter extends DragItemAdapter<Pair<Long,String>,addWorkoutListAdapter.ViewHolder> {


    private int mLayoutId;
    private int mGrabHandleId;
    private Context context;
    private PopUpListView popUpListView;
    private PopupWindow popupWindow=null;
    private ViewGroup root;
    private LinearLayout popUpLayout;
    private muscleGroupList leftList;
    private muscleGroupList rightList;



    public addWorkoutListAdapter(Context context,ArrayList<Pair<Long, String>> list, int layoutId, int grabHandleId, boolean dragOnLongPress) {
        super(dragOnLongPress);
        mLayoutId = layoutId;
        mGrabHandleId = grabHandleId;
        setHasStableIds(true);
        setItemList(list);
        this.context=context;
        popUpListView=new PopUpListView(context);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String text = mItemList.get(position).second;
        holder.mText.setText(text);
        holder.itemView.setTag(text);
    }

    @Override
    public long getItemId(int position) {
        return mItemList.get(position).first;
    }

    public class ViewHolder extends DragItemAdapter<Pair<Long, String>, addWorkoutListAdapter.ViewHolder>.ViewHolder {
        public TextView mText;

        public ViewHolder(final View itemView) {
            super(itemView, mGrabHandleId);
            mText = (TextView) itemView.findViewById(R.id.text);
        }

        @Override
        public void onItemClicked(View view) {

            if(popupWindow==null){
                popupWindow=new PopupWindow(context);
                LinearLayout layout = new LinearLayout(context);
                ViewGroup root= (ViewGroup)layout;
                popUpLayout = (LinearLayout) View.inflate(context, R.layout.popuplayout, root);
                leftList = (muscleGroupList) popUpLayout.findViewById(R.id.leftListView);
                rightList =(muscleGroupList) popUpLayout.findViewById(R.id.rightListView);
                popUpLayout.setBackgroundColor(Color.rgb(115,147,158));
            }
            else{
                popupWindow.dismiss();
            }
            //listView=(ListView)listView.findViewById(R.id.leftListView);

            Cursor cursor=Database.getDatabaseInstance(context).getPrimaryMoverForExersice((int) getItemId());
            String data ="";
            BodyPartHolder primaryBodyPartHolder[] = new BodyPartHolder[cursor.getCount()];

            for (int i=0;i<cursor.getCount();i++){
                primaryBodyPartHolder[i]=new BodyPartHolder();
                primaryBodyPartHolder[i].name=cursor.getString(2);
                cursor.moveToNext();
            }

            System.out.println(data);
            cursor=Database.getDatabaseInstance(context).getSecondaryMoverForExersice((int) getItemId());

            BodyPartHolder secondaryBodyPartHolder [] = new BodyPartHolder[cursor.getCount()];

            for (int i=0;i<cursor.getCount();i++){
                secondaryBodyPartHolder[i]=new BodyPartHolder();
                secondaryBodyPartHolder[i].name=cursor.getString(2);
                cursor.moveToNext();
            }

/*
            BodyPartHolder bodyPartHolder[] = new BodyPartHolder[2];
            bodyPartHolder[0]=new BodyPartHolder();
            bodyPartHolder[0].name="Quads";
            bodyPartHolder[1]=new BodyPartHolder();
            bodyPartHolder[1].name="Lats";
*/
            BodyPartHolder bodyPartHolder1[] = new BodyPartHolder[2];
            bodyPartHolder1[0]=new BodyPartHolder();
            bodyPartHolder1[0].name="Quadasdfs";
            bodyPartHolder1[1]=new BodyPartHolder();
            bodyPartHolder1[1].name="Latsasdf";


            muscleGroupListAdpater<BodyPartHolder> leftListAdapter=new muscleGroupListAdpater<>(context,R.layout.muscle_list_row_layout,R.id.listText,primaryBodyPartHolder);

            muscleGroupListAdpater<BodyPartHolder> rightListAdapter=new muscleGroupListAdpater<>(context,R.layout.muscle_list_row_layout,R.id.listText,secondaryBodyPartHolder);

            leftList.setAdapter(leftListAdapter);
            rightList.setAdapter(rightListAdapter);
            /*
            Button dismissButton = (Button)popUpLayout.findViewById(R.id.dismisspopupwindow);
            dismissButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                }
            });*/
            //layout.setOrientation(LinearLayout.HORIZONTAL);
            //popupWindow.setContentView(popUpLayout);
            //popupWindow.setHeight(((View) view.getParent()).getHeight());
            //popupWindow.setWidth(((View) view.getParent()).getWidth());
            //popupWindow.setHeight(popUpLayout.getHeight());
            //popupWindow.setWidth(popUpLayout.getWidth());
            //popUpLayout.setClickable(true);
            //root.setClickable(true);
            /*
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("askdfjal;skdjf;laskjdfl;aksjdfl;kajs;df");
                    popupWindow.dismiss();
                }
            });
*/
            //popupWindow.setHeight(1500);
            popupWindow.setHeight(context.getResources().getDisplayMetrics().heightPixels*3/4);
            popupWindow.setWidth(context.getResources().getDisplayMetrics().widthPixels*3/4);
            //layout.addView(listViewleft);

            //layout.addView(listViewright);

//            popupWindow.setContentView(layout);
            popupWindow.setContentView(popUpLayout);

            popupWindow.showAtLocation(popUpLayout, Gravity.BOTTOM, 10, 10);

            popupWindow.setFocusable(true);

            popupWindow.update();
            new ScaleInAnimation(popUpLayout).animate();

            //new UnfoldAnimation(leftList).animate();
        }

        @Override
        public boolean onItemLongClicked(View view) {
            Toast.makeText(view.getContext(), "Item long clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

}
