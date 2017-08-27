package com.example.sadiq.test.Exercise;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sadiq.test.CustomDataTypes.BodyPartHolder;
import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by Mugen on 8/18/2017.
 */

public class PrimaryAndSecondaryMovers extends LinearLayout {

        ViewGroup root;

        @Bind(R.id.primarymoverlist)
        LinearLayout primaryMovers;
        BodyPartHolder primaryBodyPartHolder[];

        @Bind(R.id.secondarymoverlist)
        LinearLayout secondaryMovers;
        BodyPartHolder secondaryBodyPartHolder[];

        public PrimaryAndSecondaryMovers(Context context) {
                super(context);
                init();
        }

        public PrimaryAndSecondaryMovers(Context context, @Nullable AttributeSet attrs) {
                super(context, attrs);
                init();
        }

        public PrimaryAndSecondaryMovers(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init();
        }

        private void init() {

                root = (ViewGroup) this.inflate(getContext(), R.layout.primarysecondarymover, this);

                ButterKnife.bind(this, root);

        }


        public void bind(String exerciseName) {


                RealmDB realmDB = new RealmDB();

                RealmQuery<Exercise> exerciseRealmQuery = RealmQuery.createQuery(realmDB.getRealm(), Exercise.class);
                exerciseRealmQuery.equalTo("name", exerciseName);
                RealmResults<Exercise> exerciseRealmResults = realmDB.getWhereAllExercises(exerciseRealmQuery);


                primaryBodyPartHolder = new BodyPartHolder[exerciseRealmResults.get(0).getPrimaryMoversDBObject().size()];

                primaryMovers.removeAllViews();

                for (int i = 0; i < exerciseRealmResults.get(0).getPrimaryMoversDBObject().size(); i++) {
                        TextView textView = new TextView(getContext());
                        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        textView.setTextColor(getResources().getColor(R.color.Black));
                        textView.setText(exerciseRealmResults.get(0).getPrimaryMoversDBObject().get(i).getName());

                        primaryMovers.addView(textView);
                }

                secondaryBodyPartHolder = new BodyPartHolder[exerciseRealmResults.get(0).getSecondaryMoversDBObject().size()];

                secondaryMovers.removeAllViews();

                for (int i = 0; i < exerciseRealmResults.get(0).getSecondaryMoversDBObject().size(); i++) {

                        TextView textView = new TextView(getContext());
                        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        textView.setTextColor(getResources().getColor(R.color.Black));
                        textView.setText(exerciseRealmResults.get(0).getSecondaryMoversDBObject().get(i).getName());

                        secondaryMovers.addView(textView);

                }
        }


}
