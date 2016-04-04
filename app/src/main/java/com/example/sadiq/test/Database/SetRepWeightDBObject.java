package com.example.sadiq.test.Database;

import io.realm.RealmObject;

/**
 * Created by Sadiq on 3/30/2016.
 */
public class SetRepWeightDBObject extends RealmObject {


    private Exercise ExerciseInstance;
    private int Rep;
    private int Set;
    private float Weight;

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


    public void setExerciseInstance(Exercise ExerciseInstance){this.ExerciseInstance=ExerciseInstance;}
    public Exercise getExerciseInstance(){return this.ExerciseInstance;}



}
