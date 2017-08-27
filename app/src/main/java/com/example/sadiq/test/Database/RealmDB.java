package com.example.sadiq.test.Database;

import com.example.sadiq.test.CustomDataTypes.BodyPartHolder;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Sadiq on 3/15/2016.
 */
public class RealmDB {

        public Realm realm;

        public RealmDB() {
                realm = Realm.getDefaultInstance();

        }

        public Realm getRealm() {
                return Realm.getDefaultInstance();
        }

        public void StartTransaction() {
                realm = Realm.getDefaultInstance();
                realm.beginTransaction();

        }

        public void CommitTransaction() {
                realm.commitTransaction();
        }

        public void addorUpdateExersice(String exersiceName, BodyPartHolder[] primaryBodyPartHolders, BodyPartHolder[] secondaryBodyPartHolders) {

                realm = Realm.getDefaultInstance();

                Exercise exercise = new Exercise(exersiceName, primaryBodyPartHolders, secondaryBodyPartHolders);
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(exercise);
                realm.commitTransaction();
        }

        public RealmResults<Exercise> getAllExercise() {
                realm = Realm.getDefaultInstance();
                return realm.where(Exercise.class).findAll();
        }

        public RealmResults<Exercise> getWhereAllExercises(RealmQuery<Exercise> exerciseRealmQuery) {

                realm = Realm.getDefaultInstance();

                return exerciseRealmQuery.findAll();
        }

        public void addOrUpdateWorkout(String workoutName, WorkoutTemplate workoutTemplate) {
                workoutTemplate.setName(workoutName);
                addOrUpdateWorkout(workoutTemplate);
        }

        public void addOrUpdateWorkout(WorkoutTemplate workoutTemplate) {
                int i = 0;
                for (ExerciseSetRep exerciseSetRep : workoutTemplate.getExerciseSetRep()) {
                        exerciseSetRep.setCompositePrimaryKey(workoutTemplate.getName());
                        exerciseSetRep.setExerciseOrder(i);
                        i++;
                        for (SetRepWeightDBObject setRepWeightDBObject : exerciseSetRep.getSetRepWeightDBObjectRealmList()) {
                                setRepWeightDBObject.setCompositePrimaryKey(workoutTemplate.getName(), exerciseSetRep.getExerciseName());
                        }
                }
                realm.copyToRealmOrUpdate(workoutTemplate);

        }

        public WorkoutTemplate getWorkoutTemplateFromName(String workoutName) {

                return realm.copyFromRealm(realm.where(WorkoutTemplate.class).equalTo("Name", workoutName).findFirst());
        }


        public void addorUpdateOptionDB(OptionDB optionsDB) {

                realm = Realm.getDefaultInstance();

                realm.copyToRealmOrUpdate(optionsDB);


        }

        public void deleteOptionDB(OptionDB optionsDB) {
                realm = Realm.getDefaultInstance();

        }

        public OptionDB getOption(String optionKey) {
                realm = Realm.getDefaultInstance();
                OptionDB rv = realm.where(OptionDB.class).equalTo("Option", optionKey).findFirst();
                return rv;
        }

        public RealmResults<OptionDB> getAllOptions() {
                realm = Realm.getDefaultInstance();
                RealmResults<OptionDB> returnval = realm.where(OptionDB.class).findAllSorted("Option", Sort.ASCENDING);

                return returnval;
        }

        public void deleteOptionDB(String optionKey) {

                RealmResults<OptionDB> realmResults = realm.where(OptionDB.class).equalTo("Option", optionKey).findAll();
                OptionDB optionDB = realm.where(OptionDB.class).equalTo("option", optionKey).findFirst();

                optionDB.getValues().deleteAllFromRealm();
                optionDB.deleteFromRealm();

        }

        public void addOrUpdateUserOption(UserOption userOption) {
                realm = Realm.getDefaultInstance();
                realm.copyToRealmOrUpdate(userOption);
        }

        public boolean deleteUserOption(UserOption userOption) {
                realm = Realm.getDefaultInstance();
                return realm.where(UserOption.class).equalTo("Key", userOption.getKey()).findAll().deleteAllFromRealm();
        }

        public UserOption getUserOption(String key) {
                realm = Realm.getDefaultInstance();

                return realm.where(UserOption.class).equalTo("Key", key).findFirst();
        }


        public void addOrUpdateExerciseSetRep(ExerciseSetRep exerciseSetRep) {
                realm = Realm.getDefaultInstance();

                realm.copyToRealmOrUpdate(exerciseSetRep);
        }

        public RealmResults<ExerciseSetRep> getExerciseSetRep(RealmQuery<ExerciseSetRep> exerciseSetRepRealmQuery) {
                realm = Realm.getDefaultInstance();
                return exerciseSetRepRealmQuery.findAll();
        }

        public void addOrUpdateWeeklyorReccuringList(String weeklyScheduleName, RealmList<WeeklyorRecurringDayDB> weeklyorRecurringDayDB) {

                WeeklyorRecurringListDB weeklyorRecurringListDB = new WeeklyorRecurringListDB();

                weeklyorRecurringListDB.setName(weeklyScheduleName);

                weeklyorRecurringListDB.setWeeklyorRecurringDayDB(weeklyorRecurringDayDB);
                addOrUpdateWeeklyorReccuringList(weeklyorRecurringListDB);
        }

        public void addOrUpdateWeeklyorReccuringList(WeeklyorRecurringListDB weeklyorRecurringListDB) {

                for (WeeklyorRecurringDayDB weeklyorRecurringDayDB : weeklyorRecurringListDB.getWeeklyorRecurringDayDB()) {
                        weeklyorRecurringDayDB.setCompositePrimaryKey(weeklyorRecurringListDB.getName());

                }
                realm.copyToRealmOrUpdate(weeklyorRecurringListDB);

        }


        public WeeklyorRecurringListDB getWeeklyorRecurringListDB(String name) {

                WeeklyorRecurringListDB weeklyorRecurringListDB = realm.where(WeeklyorRecurringListDB.class).equalTo("name", name).findFirst();
                return realm.copyFromRealm(weeklyorRecurringListDB);
        }

}

