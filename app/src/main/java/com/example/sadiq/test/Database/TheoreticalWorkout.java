package com.example.sadiq.test.Database;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Sadiq on 3/30/2016.
 */

public class TheoreticalWorkout extends RealmObject {

    private String name;
    private RealmList<SetRepWeightDBObject> ExerciseRepSetWeightList;

    public TheoreticalWorkout(){}

    public TheoreticalWorkout(String name,RealmList<SetRepWeightDBObject> ExerciseRepSetWeightList){
        this.name=name;
        this.ExerciseRepSetWeightList=ExerciseRepSetWeightList;
    }

    public String getName(){return this.name;}
    public void setName(String name){this.name=name;}

    public RealmList<SetRepWeightDBObject> getExerciseRepSetWeightList(){return this.ExerciseRepSetWeightList;}

    public void setExerciseRepSetWeightList(RealmList<SetRepWeightDBObject> ExerciseRepSetWeightList){this.ExerciseRepSetWeightList=ExerciseRepSetWeightList;}


}
