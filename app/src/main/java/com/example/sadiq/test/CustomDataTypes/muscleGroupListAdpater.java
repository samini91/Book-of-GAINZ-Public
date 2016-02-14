package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ToggleButton;

import com.example.sadiq.test.R;

import java.util.List;

/**
 * Created by Sadiq on 2/13/2016.
 */
public class muscleGroupListAdpater <T> extends ArrayAdapter {

    public muscleGroupListAdpater(Context context, @LayoutRes int resource, @IdRes int textViewResourceId,
                                  @NonNull T [] objects){
        super(context, resource, textViewResourceId, objects);

        }

    //TODO shuold try and recyle views instead using a bandage right now with the getViewTypecount and getItemViewType Methods

    @Override

    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }
}
