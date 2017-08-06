package com.example.sadiq.test.Database;

import android.app.VoiceInteractor;
import android.content.Context;

import com.example.sadiq.test.CustomDataTypes.BodyPartHolder;
import com.example.sadiq.test.ExerciseSetRep.ExerciseSetRepView;
import com.example.sadiq.test.Initilization.Initilization;


import java.io.File;
import java.util.Collections;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Sadiq on 3/15/2016.
 */
public class RealmDB {

    public Realm realm;

    public RealmDB ()
    {
        realm = Realm.getDefaultInstance();


    }

    public Realm getRealm(){
        return Realm.getDefaultInstance();
    }

    public void StartTransaction ()
    {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();

    }

    public void CommitTransaction ()
    {
        realm.commitTransaction();
    }
    public void addorUpdateExersice(String exersiceName,BodyPartHolder[] primaryBodyPartHolders,BodyPartHolder[] secondaryBodyPartHolders){

        realm = Realm.getDefaultInstance();
        //long nextId;
        //try {
//            nextId = (realm.where(Exercise.class).max("Id").longValue() + 1);
//        }
//        catch(NullPointerException e){
//            nextId=0;
//        }


        Exercise exercise = new Exercise(exersiceName,primaryBodyPartHolders,secondaryBodyPartHolders);
        // todo remove all begin and commits let the methjods handle it
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(exercise);
        realm.commitTransaction();
    }
    public RealmResults<Exercise> getAllExercise()
    {
        realm = Realm.getDefaultInstance();
        return realm.where(Exercise.class).findAll();
    }
    public RealmResults<Exercise> getWhereAllExercises(RealmQuery<Exercise> exerciseRealmQuery)
    {

        realm = Realm.getDefaultInstance();

        return exerciseRealmQuery.findAll();
        //realm.where(Exercise.class).

    }

    //user should pass name and exercises listx is probably going to be a RealmList
    public void addorUpdateWorkout(String ExerciseName, List<Exercise> exercises){


    }

    public void addorUpdateOptionDB(OptionDB optionsDB){

    realm = Realm.getDefaultInstance();
//        realm.beginTransaction();
    realm.copyToRealmOrUpdate(optionsDB);
  //  realm.commitTransaction();

}

    public void deleteOptionDB(OptionDB optionsDB)
    {
        realm = Realm.getDefaultInstance();
        //realm.
        //realm.copyToRealmOrUpdate(optionsDB);
    }
    public OptionDB getOption(String optionKey)
    {
        realm = Realm.getDefaultInstance();
        OptionDB rv = realm.where(OptionDB.class).equalTo("Option",optionKey).findFirst();
        return rv;
    }

    public RealmResults<OptionDB> getAllOptions(){
       realm =  Realm.getDefaultInstance();
        RealmResults<OptionDB> returnval  = realm.where(OptionDB.class).findAllSorted("Option", Sort.ASCENDING);

        // TODO The realm sort it being incredily dumbs so just for now use collections sort

        return returnval ;
    }

    //Work on this
    public void deleteOptionDB(String optionKey)
    {
     //   realm.beginTransaction();

       // realm = Realm.getDefaultInstance();
        RealmResults<OptionDB> realmResults = realm.where(OptionDB.class).equalTo("Option",optionKey).findAll();
        OptionDB optionDB = realm.where(OptionDB.class).equalTo("option",optionKey).findFirst();

        optionDB.getValues().deleteAllFromRealm();
        optionDB.deleteFromRealm();
        //realm.commitTransaction();
    }

    public void addOrUpdateUserOption(UserOption userOption)
    {
        realm = Realm.getDefaultInstance();
        realm.copyToRealmOrUpdate(userOption);
    }

    public boolean deleteUserOption(UserOption userOption)
    {
        realm = Realm.getDefaultInstance();
        return realm.where(UserOption.class).equalTo("Key",userOption.getKey()).findAll().deleteAllFromRealm();
        //realm.delete(userOption.getClass());

    }
    public UserOption getUserOption(String key)
    {
        realm = Realm.getDefaultInstance();

        return realm.where(UserOption.class).equalTo("Key",key).findFirst();
    }


    public void addOrUpdateExerciseSetRep( ExerciseSetRep exerciseSetRep)
    {
        realm = Realm.getDefaultInstance();

        realm.copyToRealmOrUpdate(exerciseSetRep);

    }

    public RealmResults<ExerciseSetRep> getExerciseSetRep(RealmQuery<ExerciseSetRep> exerciseSetRepRealmQuery)
    {
        realm = Realm.getDefaultInstance();
        return exerciseSetRepRealmQuery.findAll();
    }





}

