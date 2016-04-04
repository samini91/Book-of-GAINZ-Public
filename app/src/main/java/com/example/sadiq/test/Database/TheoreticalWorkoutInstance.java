package com.example.sadiq.test.Database;

/**
 * Created by Sadiq on 3/31/2016.
 */

//This class is used to pass this data between the fragments although I should look into parceable
//// TODO: 3/31/2016 needtofix 
public class TheoreticalWorkoutInstance {

    TheoreticalWorkoutInstance theoreticalWorkoutInstance;

    private TheoreticalWorkoutInstance(){

    }

    public TheoreticalWorkoutInstance getTheoreticalWorkoutInstance(){
        if(theoreticalWorkoutInstance==null){
            theoreticalWorkoutInstance=new TheoreticalWorkoutInstance();
        }
        return theoreticalWorkoutInstance;
    }


}
