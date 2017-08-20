package com.example.sadiq.test.FilterableList;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.sadiq.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mugen on 8/18/2017.
 */

public class FiltersWeeklyorRecurringList extends FiltersBase {
        ViewGroup root;

        @Bind(R.id.NameFilter)
        EditText editTextView;


        public FiltersWeeklyorRecurringList(Context context) {
                super(context);
                init();
        }

        public FiltersWeeklyorRecurringList(Context context, @Nullable AttributeSet attrs) {
                super(context, attrs);
                init();
        }

        public FiltersWeeklyorRecurringList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init();
        }

        private void init(){


                root = (ViewGroup) inflate(getContext(), R.layout.filtersweeklyorreccuringlist, this);

                ButterKnife.bind(this,root);

                editTextView.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                                iUpdateFilter.UpdateFilter("name",s.toString());
                        }
                });



        }
}
