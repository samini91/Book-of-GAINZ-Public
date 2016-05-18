package com.example.sadiq.test.Database;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class OptionDB extends RealmObject {


    @PrimaryKey
    private long Id;

    private String weightMetric;

    public OptionDB(){}

    public OptionDB(String weightMetric){
        //So that there can only be one set of options
        this.Id=1;
        this.weightMetric=weightMetric;

    }

    public String getWeightMetric() {
        return weightMetric;
    }
    public void setWeightMetric(String weightMetric){
        this.weightMetric=weightMetric;
    }

    public void setId(long Id){
        this.Id=Id;
    }

    public long getId(){
        return this.Id;
    }

}
