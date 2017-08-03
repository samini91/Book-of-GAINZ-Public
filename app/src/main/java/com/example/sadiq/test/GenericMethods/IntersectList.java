package com.example.sadiq.test.GenericMethods;

import java.util.List;

/**
 * Created by Mugen on 7/10/2017.
 */

public class IntersectList  {

        //need to wrap it in an object since java doesnt support ref
        //Need to do run time checks should never hit in final version
        public static void IntersectList( Object a, Object b){
                try
                {
                        List left;
                        List right;
                        //dont know if instanceof works
                        if(a instanceof List)
                                left = (List) a;
                        else
                                throw new Exception("IntersectLists left list is the wrong instance");
                        if(b instanceof List)
                                right = (List) b;
                        else
                                throw new Exception("IntersectLists right list is the wrong instance");



                }
                catch (Exception e)
                {
                        e.printStackTrace();

                }



        }
}
