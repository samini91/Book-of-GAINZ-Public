package com.example.sadiq.test;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by Mugen on 8/6/2017.
 */

public interface IActivityDataFactory {
        int newInstance = 0;
        int detail = 1;

        void ActivityDataFactory(Fragment from, String to, int requestCode,int type ,Bundle bundle);

}
