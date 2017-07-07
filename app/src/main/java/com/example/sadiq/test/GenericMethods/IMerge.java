package com.example.sadiq.test.GenericMethods;

import com.example.sadiq.test.Database.RealmString;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;

/**
 * Created by Mugen on 5/22/2017.
 */

public interface IMerge <T,F> {

       //void MergeEqualTo(T a, List<F> AddList, List<F>DeleteList);
       //void MergeEqualTo(T a, RealmList<F> AddList, RealmList<F> DeleteList);
       void MergeEqualTo(T a, RealmList AddList, RealmList DeleteList);
}
