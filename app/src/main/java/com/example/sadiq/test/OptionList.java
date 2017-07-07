package com.example.sadiq.test;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sadiq.test.CustomDataTypes.Counter;
import com.example.sadiq.test.Options.Option_dynamic_values;
import com.example.sadiq.test.RepSetWeightConfigurationView.SetRepWeightConfigurationView;
import com.example.sadiq.test.RepSetWeightConfigurationView.SetRepWeightListAdapter;

import org.w3c.dom.Text;

import java.util.Set;

public class OptionList extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container, Bundle
                                         savedInstanceState) {

                final ViewGroup view = (ViewGroup)inflater.inflate(R.layout.option_option_main, container, false);

                //final Option_dynamic_values option_dynamic_values = (Option_dynamic_values) view.findViewById(R.id.option_option_dynamic_values);

//                for(int i = 0 ; i < 7; i++)
  //                      option_dynamic_values.addOptionValue(getActivity(), Integer.toString(i));

                /*
                view.post(new Runnable() {
                        @Override
                        public void run() {
                               int height_parent = view.getHeight(); //height is ready
                               int width_parent = view.getWidth();
                                //Try to resize if width is bigger than the view
                                int width_dynamiclist = option_dynamic_values.getWidth();
                                int height_dynamiclist = option_dynamic_values.getHeight();

                                if(width_dynamiclist >= width_parent)
                                {
                                        int resizeVal = width_dynamiclist / ((ViewGroup) option_dynamic_values.getChildAt(0)).getChildCount();

                                        for(int index = 0; index<((ViewGroup)option_dynamic_values.getChildAt(0)).getChildCount(); ++index) {
                                                View nextChild = ((ViewGroup)option_dynamic_values.getChildAt(0)).getChildAt(index);

//                        nextChild.setLayoutParams();

                                                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, resizeVal);
                                                nextChild.setLayoutParams(lp);

                                        }

                                }

                        }

                });
                */
                return view;
        }

}
