package com.example.sadiq.test.Database;

import com.example.sadiq.test.ListParcel;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.WeeklyorRecurringListDBRealmProxy;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Mugen on 8/12/2017.
 */

@Parcel(implementations = {WeeklyorRecurringListDBRealmProxy.class}, value = Parcel.Serialization.BEAN, analyze = {SetRepWeightDBObject.class})

public class WeeklyorRecurringListDB extends RealmObject {

        @Ignore
        public static int Weekly = 1;
        @Ignore
        public static int Recurring = 2;

        @PrimaryKey
        String name;
        int frequencyType;

        RealmList<WeeklyorRecurringDayDB> weeklyorRecurringDayDB = new RealmList<>();

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setFrequencyType(int frequencyType) {
                this.frequencyType = frequencyType;
        }

        public int getFrequencyType() {
                return frequencyType;
        }

        @ParcelPropertyConverter(ListParcel.class)
        public void setWeeklyorRecurringDayDB(RealmList<WeeklyorRecurringDayDB> weeklyorRecurringDayDB) {
                this.weeklyorRecurringDayDB = weeklyorRecurringDayDB;
        }

        public RealmList<WeeklyorRecurringDayDB> getWeeklyorRecurringDayDB() {
                return weeklyorRecurringDayDB;
        }
}
