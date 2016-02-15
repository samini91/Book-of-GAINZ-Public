package com.example.sadiq.test;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Sadiq on 1/4/2016.
 */
public class addWorkoutActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        //getWindow().setWindowAnimations();
        setContentView(R.layout.addworkout);
        View view = findViewById(R.id.entire_view);

        final EditText editText1 = (EditText) findViewById(R.id.editText1);
        editText1.setText("asdffdassadff");
       // Toast.makeText(addWorkoutActivity.this,"thesindienadf",Toast.LENGTH_LONG).show();
        editText1.showContextMenu();


//View view= new View(addWorkoutActivity.this);
        ImageView imageView= new ImageView(addWorkoutActivity.this);
        view.setActivated(true);
        view.setEnabled(true);
        imageView.setEnabled(true);
        imageView.setActivated(true);




        String te= Boolean.toString(view.isActivated());
        Toast.makeText(addWorkoutActivity.this,te,Toast.LENGTH_LONG).show();
        View vie = new View(addWorkoutActivity.this);

        view.setOnTouchListener(new OnSwipeTouchListener(addWorkoutActivity.this) {

            public void onSwipeTop() {
                Toast.makeText(addWorkoutActivity.this, "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                Toast.makeText(addWorkoutActivity.this, "right", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeLeft() {
                Toast.makeText(addWorkoutActivity.this, "left", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeBottom() {
                Toast.makeText(addWorkoutActivity.this, "bottom", Toast.LENGTH_SHORT).show();
            }


        });


    }

}
