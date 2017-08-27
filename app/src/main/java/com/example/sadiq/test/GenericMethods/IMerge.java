package com.example.sadiq.test.GenericMethods;

import io.realm.RealmList;

/**
 * Created by Mugen on 5/22/2017.
 */

public interface IMerge<T, F> {

        void MergeEqualTo(T a, RealmList AddList, RealmList DeleteList);
}
