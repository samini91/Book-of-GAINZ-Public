package com.example.sadiq.test.Database;

import com.example.sadiq.test.CustomDataTypes.BodyPartHolder;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Exercise extends RealmObject {

        @PrimaryKey
        private String name;
        private RealmList<BodyPartHolderDBObject> primaryMoversDBObject;
        private RealmList<BodyPartHolderDBObject> secondaryMoversDBObject;
        private int TheoreticalMax;

        public Exercise() {
        }

        public Exercise(String name, BodyPartHolder[] PrimaryMovers, BodyPartHolder[] SecondaryMovers) {

                primaryMoversDBObject = new RealmList<>();
                secondaryMoversDBObject = new RealmList<>();
                this.name = name;
                for (int i = 0; i < PrimaryMovers.length; i++) {
                        if (PrimaryMovers[i].activate) {
                                primaryMoversDBObject.add(new BodyPartHolderDBObject(PrimaryMovers[i].name));
                        }
                        if (SecondaryMovers[i].activate) {
                                secondaryMoversDBObject.add(new BodyPartHolderDBObject(SecondaryMovers[i].name));
                        }
                }
        }

        public void setName(String name) {
                this.name = name;

        }

        public String getName() {
                return name;
        }

        public RealmList<BodyPartHolderDBObject> getPrimaryMoversDBObject() {
                return this.primaryMoversDBObject;
        }

        public RealmList<BodyPartHolderDBObject> getSecondaryMoversDBObject() {
                return this.secondaryMoversDBObject;
        }

        public void setPrimaryMoversDBObject(RealmList<BodyPartHolderDBObject> list) {
                primaryMoversDBObject = list;

        }

        public void setSecondaryMoversDBObject(RealmList<BodyPartHolderDBObject> list) {
                secondaryMoversDBObject = list;

        }

        public int getTheoreticalMax() {
                return TheoreticalMax;
        }

        public void setTheoreticalMax(int theoreticalMax) {
                this.TheoreticalMax = theoreticalMax;
        }


}

