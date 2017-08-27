package com.example.sadiq.test.Options;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadiq.test.R;

/**
 * Created by Mugen on 7/15/2016.
 */


public class Options extends Fragment {

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

                final ViewGroup root = (ViewGroup) inflater.inflate(R.layout.option_option_main, container, false);

                RecyclerView OptionList = (RecyclerView) root.findViewById(R.id.Option_RecyclerView);

                OptionList.setHasFixedSize(true);

                LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                OptionList.setLayoutManager(mLayoutManager);


                Option_RecyclerView_Adapter adapter = new Option_RecyclerView_Adapter(getActivity());

                OptionList.setAdapter(adapter);

                return root;

        }

        @Override
        public void onCreate(Bundle bundle) {
                super.onCreate(bundle);
                setHasOptionsMenu(true);

        }
}


