package com.example.sadiq.test.FilterableList;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sadiq.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Filters extends LinearLayout {

        ViewGroup root;


        @Bind(R.id.NameFilter)
        EditText editTextView;

        IUpdateFilter iUpdateFilter;

        public Filters(Context context) {
                super(context);
                init();
        }

        public Filters(Context context, @Nullable AttributeSet attrs) {
                super(context, attrs);
                init();
        }

        public Filters(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init();
        }

        public void init() {

                root = (ViewGroup) inflate(getContext(), R.layout.filters,this);

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
                                iUpdateFilter.UpdateFilter("Name",s.toString());
                        }
                });

        }

        public void setUpdateFilter(IUpdateFilter iUpdateFilter){this.iUpdateFilter=iUpdateFilter;}
}
