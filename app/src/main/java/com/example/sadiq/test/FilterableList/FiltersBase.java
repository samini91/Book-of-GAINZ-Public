package com.example.sadiq.test.FilterableList;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Mugen on 8/18/2017.
 */

public abstract class FiltersBase extends FrameLayout {


        IUpdateFilter iUpdateFilter;

        public FiltersBase(Context context) {
                super(context);
        }

        public FiltersBase(Context context, @Nullable AttributeSet attrs) {
                super(context, attrs);
        }

        public FiltersBase(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
        }


        public void setUpdateFilter(IUpdateFilter iUpdateFilter) {
                this.iUpdateFilter = iUpdateFilter;
        }

}
