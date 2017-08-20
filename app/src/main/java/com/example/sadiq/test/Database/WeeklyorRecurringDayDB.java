package com.example.sadiq.test.Database;

import com.example.sadiq.test.ListParcel;
import com.example.sadiq.test.WorkoutTemplate;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.WeeklyorRecurringDayDBRealmProxy;
import io.realm.annotations.PrimaryKey;

@Parcel(implementations = {WeeklyorRecurringDayDBRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { SetRepWeightDBObject.class })
public class WeeklyorRecurringDayDB extends RealmObject {

        @PrimaryKey
        String primaryKey;

        RealmList<WorkoutTemplate> workoutTemplates = new RealmList<>();

        int order;

        public void setCompositePrimaryKey(String weeklyorRecurringListDBName)
        {
                this.setPrimaryKey(weeklyorRecurringListDBName+"|"+this.getOrder());
        }

        public WeeklyorRecurringDayDB(){}

        public WeeklyorRecurringDayDB(int order){
                this.setOrder(order);
        }

        public void setPrimaryKey(String primaryKey) {
                this.primaryKey = primaryKey;
        }

        public String getPrimaryKey() {
                return primaryKey;
        }

        public int getOrder() {
                return order;
        }

        public void setOrder(int order) {
                this.order = order;
        }

        @ParcelPropertyConverter(ListParcel.class)
        public void setWorkoutTemplates(RealmList<WorkoutTemplate> workoutTemplates) {
                this.workoutTemplates = workoutTemplates;
        }
        public RealmList<WorkoutTemplate> getWorkoutTemplates() {
                return workoutTemplates;
        }
}
