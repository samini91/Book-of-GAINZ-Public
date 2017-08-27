package com.example.sadiq.test.OneRepMax;

import android.app.DialogFragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sadiq.test.CustomDataTypes.Counter;
import com.example.sadiq.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mugen on 8/26/2017.
 */

public class OneRepMaxFragment extends DialogFragment {

        public ViewGroup root;

        @Bind(R.id.repCounter)
        Counter repCounter;

        @Bind(R.id.weightCounter)
        Counter weightCounter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                root = (ViewGroup) inflater.inflate(R.layout.onerepmax, container, false);
                ButterKnife.bind(this, root);

                repCounter.setValueType(InputType.TYPE_CLASS_NUMBER);
                weightCounter.setValueType(InputType.TYPE_CLASS_NUMBER);


                return root;
        }


}
