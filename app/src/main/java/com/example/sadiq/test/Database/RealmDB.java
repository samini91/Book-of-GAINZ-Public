package com.example.sadiq.test.Database;

import android.content.Context;

import com.example.sadiq.test.CustomDataTypes.BodyPartHolder;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Sadiq on 3/15/2016.
 */
public class RealmDB{

    private  Realm realm;
    private static RealmDB realmDB;
    public static RealmDB getRealmInstance(Context context){
        if(realmDB == null){
            realmDB=new RealmDB(context);
        }
        return realmDB;
    }

    public Realm getRealm(){
        if(realm==null){
            return null;
        }
        else
        return realm;
    }

    private RealmDB(Context context){
        realm = Realm.getInstance(new RealmConfiguration.Builder(context).name("GAINZ").build());

    }

    public void addorUpdateExersice(String exersiceName,BodyPartHolder[] primaryBodyPartHolders,BodyPartHolder[] secondaryBodyPartHolders){

        long nextId;
        try {
            nextId = (realm.where(Exercise.class).max("Id").longValue() + 1);
        }
        catch(NullPointerException e){
            nextId=0;
        }
        Exercise exercise = new Exercise(nextId,exersiceName,primaryBodyPartHolders,secondaryBodyPartHolders);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(exercise);
        realm.commitTransaction();
    }
    public RealmResults<Exercise> getAllExercise(){
        return realm.where(Exercise.class).findAll();
        }

    //user should pass name and exercises list is probably going to be a RealmList
    public void addorUpdateWorkout(String ExerciseName, List<Exercise> exercises){


    }

//TODO can add more
public void addorUpdateOptions(String weightMetric){

    //Make sure that this is a valid string... would love to use enums but not supported
    //can probably map a value to true or false in the object but will look at later
    OptionDB optionDB= new OptionDB(weightMetric);
    realm.beginTransaction();
    realm.copyToRealmOrUpdate(optionDB);
    realm.commitTransaction();
}


    public OptionDB getOptions(){

        return realm.where(OptionDB.class).findFirst();

    }
}

