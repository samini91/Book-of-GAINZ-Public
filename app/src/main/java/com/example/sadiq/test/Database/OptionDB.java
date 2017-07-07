package com.example.sadiq.test.Database;

import android.support.annotation.NonNull;

import com.example.sadiq.test.GenericMethods.IMerge;
import com.example.sadiq.test.GenericMethods.MergeList;

import org.json.JSONArray;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.Sort;
import io.realm.annotations.PrimaryKey;

public class OptionDB extends RealmObject implements Comparator<OptionDB>,Comparable<OptionDB>, IMerge<OptionDB,RealmString> {



    //private long Id;
    @PrimaryKey
    private String Option;
    private RealmList<RealmString> Values;

    private String weightMetric;

    public OptionDB(){}

    //public OptionDB(String weightMetric){
        //So that there can only be one set of options
        //this.Id=1;

        //this.weightMetric=weightMetric;

//    }



    public OptionDB(String Option , RealmList<RealmString> Values)
    {
        this.Option = Option;
        this.Values = Values;
    }


    public  OptionDB (String Option, List<String> ListValues)
    {
        this.Option = Option;
        RealmList<RealmString> RealmValues = new RealmList<>();
        for (String s : ListValues)
        {
            RealmValues.add(new RealmString(s));
        }

        this.Values = RealmValues;
        Values.sort("value", Sort.ASCENDING);

    }

    public OptionDB (String Option, JSONArray jsonArray)
    {
        try {
            this.Option = Option;
            RealmList<RealmString> RealmValues = new RealmList<>();
            for (int i = 0; i < jsonArray.length(); i++)
            {
                RealmValues.add(new RealmString(jsonArray.getString(i)));
            }
            //Collections.sort(RealmValues);
            this.Values = RealmValues;
            //Values.sort("value", Sort.ASCENDING);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public String getWeightMetric() {
        return weightMetric;
    }
    public void setWeightMetric(String weightMetric){
        this.weightMetric=weightMetric;
    }

    //public void setId(long Id){
        //this.Id=Id;
    //}

    //public long getId(){
      //  return this.Id;
    //}

    public void setOption(String Option) {this.Option = Option;}
    public String getOption(){return Option;}

    public void setValues(RealmList<RealmString> Values) {this.Values = Values;}
    public RealmList<RealmString> getValues(){return Values;}

    public boolean addToValues (RealmString value)
    {
        if(Values == null)
            Values = new RealmList<RealmString>();
        return Values.add(value);
    }

    @Override

    public int compare(OptionDB lhs, OptionDB rhs) {
        if(lhs.getOption().equals(rhs.getOption()))
            return 0;
        return (lhs.getOption().compareTo(rhs.toString()));
    }

    @Override
    public int compareTo(@NonNull OptionDB another) {
        return this.getOption().toLowerCase().compareTo(another.getOption().toLowerCase());
        //return this.getOption().compareTo(another.getOption());
    }


    @Override
    public void MergeEqualTo(OptionDB a, RealmList AddList, RealmList DeleteList) {
        Collections.sort(this.getValues());
        Collections.sort(a.getValues());
        RealmList AddListRealmString = new RealmList();
        RealmList DeleteListRealmString = new RealmList();
        OptionDB optionDB = new OptionDB();

        MergeList.MergeLists(this.getValues(),a.getValues(),AddListRealmString,DeleteListRealmString);

        //construcnt optoindb and put it in addlist
        optionDB.setOption(this.getOption());
        optionDB.setValues(AddListRealmString);

        if(DeleteListRealmString.size() != 0)
            AddList.add(optionDB);

    }
}
