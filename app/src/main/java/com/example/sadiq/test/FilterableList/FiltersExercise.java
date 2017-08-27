package com.example.sadiq.test.FilterableList;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.sadiq.test.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mugen on 8/17/2017.
 */

public class FiltersExercise extends FiltersBase {

        ViewGroup root;

        @Bind(R.id.NameFilter)
        EditText editTextView;

        @Bind(R.id.primaryMoverSpinner)
        Spinner primaryMoverSpinner;

        @Bind(R.id.secondaryMoverSpinner)
        Spinner secondaryMoverSpinner;

        public FiltersExercise(Context context) {
                super(context);
                init();
        }

        public FiltersExercise(Context context, @Nullable AttributeSet attrs) {
                super(context, attrs);
                init();
        }

        public FiltersExercise(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init();
        }

        public void init() {
                root = (ViewGroup) inflate(getContext(), R.layout.filtersexercise, this);

                ButterKnife.bind(this, root);

                String[] bodyParts = getResources().getStringArray(R.array.bodyParts);
                String bodyPartsWithDefault[] = new String[bodyParts.length + 1];

                bodyPartsWithDefault[0] = "";
                for (int i = 0; i < bodyParts.length; i++) {
                        bodyPartsWithDefault[i + 1] = bodyParts[i];
                }


                final SpinnerAdapter spinnerAdapter = new ArrayAdapter<String>(getContext(), R.layout.musclelistspinner, R.id.textView, bodyPartsWithDefault);

                primaryMoverSpinner.setAdapter(spinnerAdapter);
                secondaryMoverSpinner.setAdapter(spinnerAdapter);

                primaryMoverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                iUpdateFilter.UpdateFilter("primaryMoversDBObject.Name", (String) spinnerAdapter.getItem(position).toString());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                });


                secondaryMoverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                iUpdateFilter.UpdateFilter("secondaryMoversDBObject.Name", (String) spinnerAdapter.getItem(position).toString());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                });

                editTextView.setHint("Exercise Name");
                editTextView.setGravity(Gravity.CENTER);

                editTextView.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                                iUpdateFilter.UpdateFilter("name", s.toString());
                        }
                });

        }
}
