package com.example.sadiq.test.Database;

import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.SetRepWeightDBObjectRealmProxy;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sadiq on 3/30/2016.
 */

@Parcel(implementations = { SetRepWeightDBObjectRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { SetRepWeightDBObject.class })
public class SetRepWeightDBObject extends RealmObject {
    //This is a workaround until realm allows composite keys
    @PrimaryKey
    public String PrimaryKey;

    public int Set;
    public String ExerciseInstance;


    public int Rep;

    public float Weight;

    public SetRepWeightDBObject(){}

    public SetRepWeightDBObject(int Set){
        this.Set=Set;
        Rep=0;
        Weight=0;
    }


    public SetRepWeightDBObject(int set, int rep, float weight){
        this.Set=set;
        this.Rep=rep;
        this.Weight=weight;
    }
    public void setRep(int Rep){this.Rep=Rep;}
    public int getRep(){return this.Rep;}

    public void setSet(int Set){this.Set=Set;}
    public int getSet(){return this.Set;}

    public void setWeight(float Weight){this.Weight=Weight;}
    public float getWeight(){return this.Weight;}


    public void setExerciseInstance(String ExerciseInstance){this.ExerciseInstance=ExerciseInstance;}
    public String getExerciseInstance(){return this.ExerciseInstance;}

    public void setCompositePrimaryKey()
    {
        setPrimaryKey(getExerciseInstance() +"|"+ getSet());
    }


    public void setPrimaryKey(String primaryKey) {
        PrimaryKey=primaryKey;
    }

    public String getPrimaryKey() {
        return PrimaryKey;
    }
}
