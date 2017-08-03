package com.example.sadiq.test;

import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Database.RealmString;
import com.example.sadiq.test.Database.SetRepWeightDBObject;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import io.realm.ExerciseSetRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.WorkoutTemplateRealmProxy;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Mugen on 7/17/2017.
 */

@Parcel(implementations = { ExerciseSetRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { ExerciseSet.class })
public class ExerciseSet extends RealmObject {

        @PrimaryKey
        public int ExerciseOrder;


        public RealmList<SetRepWeightDBObject> setRepWeightDBObjectRealmList;

        public int getExerciseOrder() {
                return ExerciseOrder;
        }

        public void setExerciseOrder(int exerciseOrder) {
                ExerciseOrder = exerciseOrder;
        }

        @ParcelPropertyConverter(ListParcel.class)
        public void setSetRepWeightDBObjectRealmList(RealmList<SetRepWeightDBObject> setRepWeightDBObjectRealmList) {
                this.setRepWeightDBObjectRealmList = setRepWeightDBObjectRealmList;
        }

        public RealmList<SetRepWeightDBObject> getSetRepWeightDBObjectRealmList() {
                return setRepWeightDBObjectRealmList;
        }
}
