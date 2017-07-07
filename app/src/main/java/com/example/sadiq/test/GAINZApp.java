package com.example.sadiq.test;

import android.Manifest;
import android.app.Application;
import android.content.Intent;

import com.example.sadiq.test.Initilization.Initilization;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
//import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
//import com.facebook.stetho.Stetho;
//import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.regex.Pattern;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Mugen on 3/27/2017.
 */

public class GAINZApp extends Application {
        @Override
        public void onCreate() {
try {
        super.onCreate();

//                Stetho.initialize(
//                        Stetho.newInitializerBuilder(this)
//                                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                                .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
//                                .build());
        Initilization.Init(this);

        //     Stetho.initializeWithDefaults(this);

/*                Stetho.initialize(Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this)
                                .withDescendingOrder()
                                .withLimit(1000)
                                .databaseNamePattern(Pattern.compile("GAINZ"))
                                .build())

                        .build());
*/


        //------ init the db ----------------
        //RealmConfiguration config = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().name("TheDb.realm").build();
        //Realm.setDefaultConfiguration(config);


        //RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Intent startIntent = new Intent(this, MainActivity.class);
        startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startIntent);
        //RealmInspectorModulesProvider.ProviderBuilder a = new RealmInspectorModulesProvider.ProviderBuilder(this);
}
     catch (Exception ex)
     {
         ex.printStackTrace();
     }

        }
}
