package com.example.sadiq.test.Database;

import android.support.annotation.NonNull;

import com.example.sadiq.test.GenericMethods.IMerge;

import org.parceler.Parcel;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmStringRealmProxy;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Mugen on 4/26/2017.
 */


@Parcel(implementations = {RealmStringRealmProxy.class}, value = Parcel.Serialization.BEAN, analyze = {RealmString.class})
public class RealmString extends RealmObject implements Comparable<RealmString>, IMerge<RealmString, RealmString> {
        @PrimaryKey
        private String value;

        public RealmString() {
        }

        public RealmString(String s) {
                value = s;

        }

        public void setValue(String s) {
                this.value = s;
        }

        public String getValue() {
                return value;
        }


        @Override
        public int compareTo(@NonNull RealmString another) {
                return this.getValue().toLowerCase().compareTo(another.getValue().toLowerCase());
        }

        @Override
        public void MergeEqualTo(RealmString a, RealmList AddList, RealmList DeleteList) {
                AddList.add(this);
        }
}
