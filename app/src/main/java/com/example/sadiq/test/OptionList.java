package com.example.sadiq.test;

//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.os.Bundle;
        import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
