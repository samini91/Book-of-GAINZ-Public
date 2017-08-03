package com.example.sadiq.test;

import android.os.Parcelable;

import com.example.sadiq.test.Database.Exercise;
import com.example.sadiq.test.Database.RealmString;

import org.parceler.Parcel;
import org.parceler.ParcelPropertyConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.WorkoutTemplateRealmProxy;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sadiq on 2/10/2016.
 */


@Parcel(implementations = { WorkoutTemplateRealmProxy.class },
        value = Parcel.Serialization.BEAN,
        analyze = { WorkoutTemplate.class })
public class WorkoutTemplate extends RealmObject {
    //@PrimaryKey
    public String Name;

    public RealmList<ExerciseSet> ExerciseSet;
    //@Ignore
    //HashMap<ExerciseSet,  >

    //public RealmList<Exercise> workoutExercises;
    //ArrayList<String >Exersice;

    public  WorkoutTemplate(){}
    public  WorkoutTemplate(String Name){
        this.Name = Name;
    }
    public WorkoutTemplate(String Name, RealmList<ExerciseSet> ExerciseSet){
        this.Name = Name;
        this.ExerciseSet = ExerciseSet;
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

    public void setExerciseSet(RealmList<ExerciseSet> exerciseSet) {
        ExerciseSet = exerciseSet;
    }

    @ParcelPropertyConverter(ListParcel.class)
    public RealmList<ExerciseSet> getExerciseSet() {
        return ExerciseSet;
    }
}
