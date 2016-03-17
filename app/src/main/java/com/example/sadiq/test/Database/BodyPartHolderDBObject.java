package com.example.sadiq.test.Database;

import com.example.sadiq.test.CustomDataTypes.BodyPartHolder;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Sadiq on 3/15/2016.
 */
public class BodyPartHolderDBObject extends RealmObject {
    String name;

    public BodyPartHolderDBObject(){}

    public BodyPartHolderDBObject(String name){
        this.name=name;
    }
}
