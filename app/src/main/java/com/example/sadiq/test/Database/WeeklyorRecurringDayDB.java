package com.example.sadiq.test.Database;

import com.example.sadiq.test.ListParcel;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.WeeklyorRecurringDayDBRealmProxy;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Mugen on 8/12/2017.
 */



@Parcel(implementations = {WeeklyorRecurringDayDBRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { SetRepWeightDBObject.class })
public class WeeklyorRecurringDayDB extends RealmObject {

        @PrimaryKey
        String primaryKey;

        RealmList<TheoreticalWorkout> theoreticalWorkouts = new RealmList<>();

        int order;

        public static void generatePrimaryKeyWeeklyorRecurringDayDb(String weeklyorRecurringListDBName,WeeklyorRecurringDayDB weeklyorRecurringDayDB)
        {
                weeklyorRecurringDayDB.setPrimaryKey(weeklyorRecurringListDBName+"|"+weeklyorRecurringDayDB.getOrder());
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
        public void setTheoreticalWorkouts(RealmList<TheoreticalWorkout> theoreticalWorkouts) {
                this.theoreticalWorkouts = theoreticalWorkouts;
        }

        public RealmList<TheoreticalWorkout> getTheoreticalWorkouts() {
                return theoreticalWorkouts;
        }
}
