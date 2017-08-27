package com.example.sadiq.test.Database;

import io.realm.RealmObject;

public class BodyPartHolderDBObject extends RealmObject {
        private String Name;

        public BodyPartHolderDBObject() {
        }

        public BodyPartHolderDBObject(String name) {
                this.Name = name;
        }

        public String getName() {
                return Name;
        }

        public void setName(String name) {
                this.Name = name;
        }
}
