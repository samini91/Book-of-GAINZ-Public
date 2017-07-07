package com.example.sadiq.test.Initilization;

import android.content.Context;
import android.database.MergeCursor;

import com.example.sadiq.test.Database.OptionDB;
import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.Database.RealmString;
import com.example.sadiq.test.Database.UserOption;
import com.example.sadiq.test.R;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.Sort;
import io.realm.internal.IOException;
import io.realm.internal.RealmCore;

import static com.example.sadiq.test.GenericMethods.MergeList.MergeLists;
//import io.realm.internal.Context;

/**
 * Created by Mugen on 8/14/2016.
 */
public class Initilization {

    public static void Init(final Context context) {
        Realm.init(context);

        RealmConfiguration config = new RealmConfiguration.
              Builder().
            deleteRealmIfMigrationNeeded().
          build();

        Realm.setDefaultConfiguration(config);

        Stetho.initialize(
                Stetho.newInitializerBuilder(context)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(context))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(context).build())
                        .build());
        //Test add or update on optionlist
        final RealmDB realmDB = new RealmDB();
        try {
            realmDB.realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    //realm.where(OptionDB.class).findAll().first().getValues().remove(0);
                    //     realmDB.getAllOptions().get(0).getValues().remove(0);
                    Regenerate(context);
                }
            });
        }
        finally {
            realmDB.realm.close();
        }
//       Regenerate(context);
    }

    public static void Regenerate(Context context)
    {

        try
        {

//use execute realm instead
            //Insert possible options into db
            String options = null;
            String key;
            JSONArray values;
            OptionDB optionDB;
            RealmDB realmDB = new RealmDB();

            ArrayList<OptionDB> defaultOptions = new ArrayList<>();

            RealmResults<OptionDB> optionDBResults = realmDB.getAllOptions();
            OptionDB CreateOption = new OptionDB();

            RealmList<OptionDB> AddList = new RealmList<>();
            RealmList<OptionDB> DeleteList = new RealmList<>();

            InputStream inputStream = context.getAssets().open("Options.Json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            options = new String(buffer, "UTF-8");
            JSONObject json = new JSONObject(options);

            JSONArray allValidOptions = json.getJSONArray("Options");


            RealmList<OptionDB> allValidOptionslist = new RealmList<>();
            OptionDB testa = new OptionDB();
            for(int k = 0; k<allValidOptions.length();k++) {

                JSONObject optionjson = allValidOptions.getJSONObject(k);

                RealmList<RealmString> RealmValues = new RealmList<>();
                //RealmValues
                Iterator<String> JsonIterator = optionjson.keys();
                key = JsonIterator.next();
                optionDB = realmDB.getOption(key);
                values = optionjson.getJSONArray(key);

                OptionDB op = new OptionDB(key,values);
                allValidOptionslist.add(op);


                //Need a copy since we mess with the realm string list and thats a pointer this list cant track
                defaultOptions.add(new OptionDB(key,values));
            }
            //allValidOptionslist.sort("Option");

            Collections.sort(allValidOptionslist);

            // Have to resort because realm is being dumb and not doing it properly
            RealmList<OptionDB> optionDBList = new RealmList<OptionDB>();
            optionDBList.addAll(optionDBResults);
            Collections.sort(optionDBList);

            MergeLists(allValidOptionslist,optionDBList, AddList,DeleteList);

            for (OptionDB a: AddList)
            {
                realmDB.addorUpdateOptionDB(a);
            }

            for (OptionDB b: DeleteList)
            {
                realmDB.deleteUserOption(new UserOption(b.getOption(),null));
                b.deleteFromRealm();
            }



            //loop through if exists do nothing if not then create with positon 0 addlist.get(0).getvalues.get0
            for(int l =0 ; l < defaultOptions.size(); l ++)
            {
                if(realmDB.getUserOption(defaultOptions.get(l).getOption()) == null)
                {
                    realmDB.addOrUpdateUserOption(new UserOption(defaultOptions.get(l).getOption(),defaultOptions.get(l).getValues().get(0).getValue()));
                }
            }


//            AddList.get(0).getValues().remove(AddList.get(0).getValues().size()-1);
  //          AddList.get(0).getValues().remove(AddList.get(0).getValues().size()-1);

//            realmDB.addorUpdateOptions(AddList.get(0));


          //      RealmDB reaB = new RealmDB();

            //allValidOptionslist.sort("Option", Sort.ASCENDING);

            //We have 2 sorted lists now one is the db the other is the json
/*
            for(int k = 0; k<allValidOptions.length();k++)
            {
                JSONObject optionjson = allValidOptions.getJSONObject(k);

                json.names();
                Iterator<String> JsonIterator = optionjson.keys();
                key = JsonIterator.next();
                optionDB = realmDB.getOption(key);
                optionjson.names();
                values = optionjson.getJSONArray(key);


                //Create if it exists in json list and not db
                if(optionDB == null)
                {
                    CreateOption = new OptionDB();
                    CreateOption.setOption(key);
                    for(int z = 0 ; z < values.length() ; z++)
                    {
                        CreateOption.addToValues(new RealmString(values.getString(z)));
                    }
                    realmDB.addorUpdateOptions(CreateOption);
                    continue;
                }



                Iterator<RealmString> iterator = optionDB.getValues().iterator();
                valueExists = false;
               // if(optionDB != null)

                    for (int i = 0 ; i < values.length() ; i++)
                    {
                        value = values.getString(i);

                        //valueExists = false;
                        //for(RealmString RString : optionDB.getValues())
                        //if it exists in the db the remove it from the deletion list

                        if(optionDB != null)
                        {
                            while( iterator.hasNext()){
                                if(iterator.next().getValue().equals(value))
                                //if (optionDB.getValues().get(l).getValue().equals(value))
                                {
                                    //optionDB.getValues().remove(l);
                                    iterator.remove();
                                    valueExists = true;
                                    break;
                                }
                            }
                            if (!valueExists) {

                                //create
                                //    CreateOption.setOption(key);

                                //realmDB.addorUpdateOptions();
                                break;
                            }
                        }
                    }

                // delete lingering options
                // use iterator since we cant remvoe outside a transaction

                if(optionDB != null)
                for(int l = 0 ; l < optionDB.getValues().size(); l++)
                {
                    realmDB.deleteOption(optionDB.getValues().get(l).getValue());
                }



                 //   optionDBList.retainAll(Collection<String>)

                if(!valueExists)
                {
                    //the default option
                    optionDB = new OptionDB();
                    optionDB.setOption(key);
                    optionDB.setValue(values.getString(0));
                    realmDB.addorUpdateOptions(optionDB);
                }

            }
            //realmDB.CommitTransaction();

            //do a read and if it exists dont regen
        */
        }
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }


}