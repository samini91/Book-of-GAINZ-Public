package com.example.sadiq.test.AddWorkout;

import com.example.sadiq.test.Database.ExerciseSetRep;

import java.util.List;

/**
 * Created by Mugen on 8/3/2017.
 */

public class WorkOutExercisesSingleton {

        private static WorkOutExercisesSingleton workOutExercisesSingleton;

        public static List<ExerciseSetRep> listSingleton;

        private WorkOutExercisesSingleton() {

        }

        public static void setWorkOutExercisesSingleton(List<ExerciseSetRep> workOutExercises) {
                if (workOutExercisesSingleton == null) {
                        workOutExercisesSingleton = new WorkOutExercisesSingleton();
                }
                workOutExercisesSingleton.setList(workOutExercises);
        }

        public static List<ExerciseSetRep> getWorkOutExercisesSingleton() {
                if (workOutExercisesSingleton == null) {
                        workOutExercisesSingleton = new WorkOutExercisesSingleton();
                }
                return workOutExercisesSingleton.getList();
        }

        public static void clear() {
                workOutExercisesSingleton = null;
        }

        private void setList(List<ExerciseSetRep> list) {
                listSingleton = list;
        }

        private List<ExerciseSetRep> getList() {
                return listSingleton;
        }

}
