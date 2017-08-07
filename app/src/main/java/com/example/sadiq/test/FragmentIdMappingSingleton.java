package com.example.sadiq.test;

import java.util.HashMap;

/**
 * Created by Mugen on 8/6/2017.
 */

public class FragmentIdMappingSingleton {

        private static HashMap<String,String> FragmentIdMappingHashMap;

        private void FragmentIdMappingSingleton()
        {

        }

        public static HashMap<String, String> getFragmentIdMappingSingleton()
        {
                if(FragmentIdMappingHashMap == null)
                {
                        FragmentIdMappingHashMap = new HashMap<>();
                }
                return FragmentIdMappingHashMap;
        }

        public static String FindFragmentId(String s)
        {
                if(FragmentIdMappingHashMap == null)
                        return null;

                return FragmentIdMappingHashMap.get(s);
        }

        public static void PutFragmentId(String key, String val)
        {
                if(FragmentIdMappingHashMap == null)
                {
                        FragmentIdMappingHashMap = new HashMap<>();
                }

                FragmentIdMappingHashMap.put(key,val);
        }


}
