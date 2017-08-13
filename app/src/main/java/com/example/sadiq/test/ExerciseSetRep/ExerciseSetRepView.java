package com.example.sadiq.test.ExerciseSetRep;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.sadiq.test.Database.ExerciseSetRep;
import com.example.sadiq.test.R;
import com.facebook.stetho.inspector.database.ContentProviderSchema;

/**
 * Created by Mugen on 8/4/2017.
 */

public class ExerciseSetRepView extends LinearLayout {

        ViewGroup root;
        TextView exerciseName;
        TableLayout setRepLayout;

        public ExerciseSetRepView(@NonNull Context context) {
                super(context);
                init(context);
        }

        public ExerciseSetRepView(@NonNull Context context, @Nullable AttributeSet attrs) {
                super(context, attrs);
                init(context);
        }

        public ExerciseSetRepView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init(context);
        }

        void init(Context context){

                //we need a textview and listview of textviews
                this.setOrientation(LinearLayout.VERTICAL);
                this.setWeightSum(1.0f);
                root = (ViewGroup) inflate(context, R.layout.exercisesetrepview, this);

                exerciseName = (TextView)root.findViewById(R.id.exercisesetrepview_exercisename);
                setRepLayout = (TableLayout) root.findViewById(R.id.exercisesetrepview_setrep);

                exerciseName.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, .05f));
                setRepLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, .95f));

        }

        public String getExercisename(){return exerciseName.getText().toString();}

        public void Bindexercisesetrep(ExerciseSetRep exerciseSetRep)
        {
                if(exerciseSetRep == null)
                        return;
                exerciseName.setText(exerciseSetRep.getExerciseName());
                setRepLayout.removeAllViews();
                for(int i = 0 ; exerciseSetRep.getSetRepWeightDBObjectRealmList() != null&& i < exerciseSetRep.getSetRepWeightDBObjectRealmList().size() ; i++)
                {
                        setRepLayout.addView(CreateRow(getContext(),Integer.toString(exerciseSetRep.getSetRepWeightDBObjectRealmList().get(i).getRep()) + " X " + Float.toString(exerciseSetRep.getSetRepWeightDBObjectRealmList().get(i).getWeight()) ));
                }
        }

        private TableRow CreateRow(Context context,String val)
        {
                TextView textView = new TextView(context);
                textView.setText(val);

                TableRow tableRow = new TableRow(context);
                tableRow.addView(textView);

                return tableRow;
        }

}
