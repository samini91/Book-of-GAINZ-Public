package com.example.sadiq.test.Database;

import com.example.sadiq.test.CustomDataTypes.BodyPartHolder;
import com.example.sadiq.test.CustomDataTypes.muscleGroupList;

import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sadiq on 3/14/2016.
 */
public class Exercise extends RealmObject
{
    @PrimaryKey private long Id;

    private String name;
    private RealmList<BodyPartHolderDBObject> primaryMoversDBObject;
    private RealmList<BodyPartHolderDBObject> secondaryMoversDBObject;


    public Exercise(){}

    public Exercise(long Id, String name,BodyPartHolder[] PrimaryMovers, BodyPartHolder[] SecondaryMovers){

        setId(Id);
        
        primaryMoversDBObject = new RealmList<>();
        secondaryMoversDBObject = new RealmList<>();
        this.name=name;
        for(int i = 0 ; i< PrimaryMovers.length;i++){
            if(PrimaryMovers[i].activate){
                primaryMoversDBObject.add(new BodyPartHolderDBObject(PrimaryMovers[i].name));
            }
            if(SecondaryMovers[i].activate){
                secondaryMoversDBObject.add(new BodyPartHolderDBObject(SecondaryMovers[i].name));
            }
        }
    }


    public void setId(long Id){
        this.Id=Id;
}
    public long getId(){
        return Id;
    }

    public void setName(String name){
        this.name=name;

    }
    public String getName(){
        return name;
    }
    public RealmList<BodyPartHolderDBObject> getPrimaryMoversDBObject(){
        return this.primaryMoversDBObject;
    }

    public RealmList<BodyPartHolderDBObject> getSecondaryMoversDBObject(){
        return this.secondaryMoversDBObject;
    }

    public void setPrimaryMoversDBObject(RealmList<BodyPartHolderDBObject> list){
        primaryMoversDBObject=list;

    }
    public void setSecondaryMoversDBObject(RealmList<BodyPartHolderDBObject> list ){
        secondaryMoversDBObject=list;

    }

}

