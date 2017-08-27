package com.example.sadiq.test.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Mugen on 5/25/2017.
 */

public class UserOption extends RealmObject {
        @PrimaryKey
        String Key;

        String Value;


        public UserOption() {
        }

        public UserOption(String Key, String Value) {
                this.Key = Key;
                this.Value = Value;
        }

        public String getKey() {
                return Key;
        }

        public void setKey(String key) {
                Key = key;
        }

        public String getValue() {
                return Value;
        }

        public void setValue(String value) {
                Value = value;
        }
}
