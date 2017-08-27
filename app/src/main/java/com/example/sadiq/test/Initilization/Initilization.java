package com.example.sadiq.test.Initilization;

import android.content.Context;

import com.example.sadiq.test.Database.OptionDB;
import com.example.sadiq.test.Database.RealmDB;
import com.example.sadiq.test.Database.RealmString;
import com.example.sadiq.test.Database.UserOption;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

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

                Stetho.initialize(Stetho.newInitializerBuilder(context).enableDumpapp(Stetho.defaultDumperPluginsProvider(context)).enableWebKitInspector(RealmInspectorModulesProvider.builder(context).build()).build());
                final RealmDB realmDB = new RealmDB();
                try {
                        realmDB.realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                        Regenerate(context);
                                }
                        });
                } finally {
                        realmDB.realm.close();
                }
        }

        public static void Regenerate(Context context) {

                try {
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
                        for (int k = 0; k < allValidOptions.length(); k++) {

                                JSONObject optionjson = allValidOptions.getJSONObject(k);

                                RealmList<RealmString> RealmValues = new RealmList<>();
                                //RealmValues
                                Iterator<String> JsonIterator = optionjson.keys();
                                key = JsonIterator.next();
                                optionDB = realmDB.getOption(key);
                                values = optionjson.getJSONArray(key);

                                OptionDB op = new OptionDB(key, values);
                                allValidOptionslist.add(op);

                                defaultOptions.add(new OptionDB(key, values));
                        }

                        Collections.sort(allValidOptionslist);

                        RealmList<OptionDB> optionDBList = new RealmList<OptionDB>();
                        optionDBList.addAll(optionDBResults);
                        Collections.sort(optionDBList);

                        MergeLists(allValidOptionslist, optionDBList, AddList, DeleteList);

                        for (OptionDB a : AddList) {
                                realmDB.addorUpdateOptionDB(a);
                        }

                        for (OptionDB b : DeleteList) {
                                realmDB.deleteUserOption(new UserOption(b.getOption(), null));
                                b.deleteFromRealm();
                        }

                        for (int l = 0; l < defaultOptions.size(); l++) {
                                if (realmDB.getUserOption(defaultOptions.get(l).getOption()) == null) {
                                        realmDB.addOrUpdateUserOption(new UserOption(defaultOptions.get(l).getOption(), defaultOptions.get(l).getValues().get(0).getValue()));
                                }
                        }

                } catch (java.io.IOException e) {
                        e.printStackTrace();
                } catch (Exception ex) {
                        ex.printStackTrace();
                }

        }


}