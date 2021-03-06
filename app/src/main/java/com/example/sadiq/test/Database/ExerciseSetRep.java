package com.example.sadiq.test.Database;

import com.example.sadiq.test.ListParcel;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import io.realm.ExerciseSetRepRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

@Parcel(implementations = {ExerciseSetRepRealmProxy.class}, value = Parcel.Serialization.BEAN, analyze = {ExerciseSetRep.class})
public class ExerciseSetRep extends RealmObject {

        @PrimaryKey
        public String PrimaryKey;
        public int ExerciseOrder;
        public String ExerciseName;

        public RealmList<SetRepWeightDBObject> setRepWeightDBObjectRealmList;

        public int getExerciseOrder() {
                return ExerciseOrder;
        }

        public void setExerciseOrder(int exerciseOrder) {
                ExerciseOrder = exerciseOrder;
        }

        public String getExerciseName() {
                return ExerciseName;
        }

        public void setExerciseName(String exerciseName) {
                ExerciseName = exerciseName;
        }

        @ParcelPropertyConverter(ListParcel.class)
        public void setSetRepWeightDBObjectRealmList(RealmList<SetRepWeightDBObject> setRepWeightDBObjectRealmList) {
                this.setRepWeightDBObjectRealmList = setRepWeightDBObjectRealmList;
        }

        public RealmList<SetRepWeightDBObject> getSetRepWeightDBObjectRealmList() {
                return setRepWeightDBObjectRealmList;
        }

        public void setCompositePrimaryKey(String workout) {
                setPrimaryKey(workout + "|" + getExerciseName());
        }

        public String getPrimaryKey() {
                return PrimaryKey;
        }

        public void setPrimaryKey(String primaryKey) {
                PrimaryKey = primaryKey;
        }

        @Override
        public boolean equals(Object o) {
                return this.getExerciseName().equals(((ExerciseSetRep) o).getExerciseName());
        }
}
