package com.example.sadiq.test.SetRepWeightConfigurationView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sadiq.test.CustomDataTypes.RepCounter;
import com.example.sadiq.test.CustomDataTypes.WeightCounter;
import com.example.sadiq.test.Database.SetRepWeightDBObject;
import com.example.sadiq.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Sadiq on 4/1/2016.
 */
public class SetRepWeightList_ItemView extends RelativeLayout {
        @Bind(R.id.textviewtestsetrepweight)
        TextView textView;
        private boolean hasBinded = false;

        @Bind(R.id.setrepweightlist_cardview_repCounter)
        RepCounter repCounter;

        @Bind(R.id.setrepweightlist_cardview_weightCounter)
        WeightCounter weightCounter;

        public SetRepWeightList_ItemView(Context context) {
                super(context);
                init(context);
        }

        public SetRepWeightList_ItemView(Context context, AttributeSet attrs) {
                super(context, attrs);
                init(context);
        }

        public SetRepWeightList_ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init(context);
        }

        private void init(Context context) {
                inflate(context, R.layout.setrepweightlist_cardview, this);

                ButterKnife.bind(this);
                repCounter.setLabel("REPS");
                repCounter.addNewValueChanger(0, "Failure");
                repCounter.setMax(999);


                weightCounter.setLabel("Weight");
                weightCounter.setMax(1500);
                weightCounter.setMin(0);
                weightCounter.setCrementValue(5.0f);
        }

        public void bind(final SetRepWeightDBObject setRepWeightDBObject) {

                hasBinded = true;
                textView.setText(Integer.toString(setRepWeightDBObject.getSet()));

                repCounter.setValue(setRepWeightDBObject.getRep());
                repCounter.updateSetRepWeightDBObject(setRepWeightDBObject);


                weightCounter.setValue((int) setRepWeightDBObject.getWeight());
                weightCounter.updateSetRepWeightDBObject(setRepWeightDBObject);


                hasBinded = false;
        }

}
