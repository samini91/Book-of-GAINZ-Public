package com.example.sadiq.test.GenericMethods;

import com.example.sadiq.test.Database.RealmString;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmModel;

/**
 * Created by Mugen on 5/22/2017.
 */

public class MergeList {

        public static <T extends Comparable<T> & RealmModel & IMerge> void MergeLists(List<T> ActualList, List<T> DatabaseList, RealmList<T> AddList , RealmList<T> DeleteList)
        //public static <T extends RealmObject,Comparable<T>> void MergeLists(List<T> ActualList, List<T> DatabaseList, RealmList<T> AddList , RealmList<T> DeleteList)
        {
                int ActualListIndex = 0;
                int DatabaseListIndex = 0;


                //while(ActualListIndex >= DatabaseListIndex)
                while(ActualList.size() > ActualListIndex && DatabaseList.size() > DatabaseListIndex)
                {
                        //Less Than
                        if(ActualList.get(ActualListIndex).compareTo(DatabaseList.get(DatabaseListIndex)) < 0)
                        {
                                AddList.add(ActualList.get(ActualListIndex));
                                ActualListIndex++;
                        }
                        //Greater Than
                        else if(ActualList.get(ActualListIndex).compareTo(DatabaseList.get(DatabaseListIndex)) > 0)
                        {
                                DeleteList.add(DatabaseList.get(DatabaseListIndex));
                                DatabaseListIndex++;
                        }
                        // Equal to
                        else
                        {
                                RealmList<T> AddListValues = new RealmList<>();
                                RealmList<T> DeleteListValues = new RealmList<>();

                                ActualList.get(ActualListIndex).MergeEqualTo(DatabaseList.get(DatabaseListIndex),AddListValues,DeleteListValues);


                                //Do a merge between them and add it to the add list
                                //get the value list and do the exact same thing above
                                // add new thing but for now just re add the list val
                                //use whatever is in addlistvalues 0
                                //AddList.add(ActualList.get(ActualListIndex));
                                if(AddListValues.size() > 0)
                                        AddList.add(AddListValues.get(0));

                                ActualListIndex++;
                                DatabaseListIndex++;

                        }
                }

                //which on won the race
                if(ActualList.size() != ActualListIndex)
                {
                        //mark everything for cretion in actual list
                        for(ActualListIndex = ActualListIndex ;ActualListIndex<ActualList.size();ActualListIndex++)
                        {
                                AddList.add(ActualList.get(ActualListIndex));
                        }
                }
                else if(DatabaseList.size() != DatabaseListIndex)
                {
                        //mark for everything in db list for deletion
                        for(DatabaseListIndex = DatabaseListIndex ;DatabaseListIndex<DatabaseList.size();DatabaseListIndex++)
                        {
                                DeleteList.add(DatabaseList.get(DatabaseListIndex));
                        }
                }

        }

}
