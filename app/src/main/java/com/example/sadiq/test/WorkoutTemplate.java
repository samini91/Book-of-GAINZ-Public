package com.example.sadiq.test;

import com.example.sadiq.test.Database.ExerciseSetRep;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.WorkoutTemplateRealmProxy;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sadiq on 2/10/2016.
 */

@Parcel(implementations = { WorkoutTemplateRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { WorkoutTemplate.class })
public class WorkoutTemplate extends RealmObject {
    @PrimaryKey
    public String Name;

    public RealmList<ExerciseSetRep> ExerciseSetRep;
    //@Ignore
    //HashMap<ExerciseSetRep,  >

    //public RealmList<Exercise> workoutExercises;
    //ArrayList<String >Exersice;

    public  WorkoutTemplate(){}
    public  WorkoutTemplate(String Name){
        this.Name = Name;
    }
    public WorkoutTemplate(String Name, RealmList<com.example.sadiq.test.Database.ExerciseSetRep> ExerciseSetRep){
        this.Name = Name;
        this.ExerciseSetRep = ExerciseSetRep;
    }

    public WorkoutTemplate(String Name,ArrayList<String> Exersice){
        this.Name=Name;
      //  this.Exersice=Exersice;
}

    public void setName(String name) {
        Name = name;
    }
    
    public String getName() {
        return Name;
    }

    public void setExerciseSetRep(RealmList<ExerciseSetRep> exerciseSetRep) {
        ExerciseSetRep = exerciseSetRep;
    }

    @ParcelPropertyConverter(ListParcel.class)
    public RealmList<ExerciseSetRep> getExerciseSetRep() {
        return ExerciseSetRep;
    }
}
