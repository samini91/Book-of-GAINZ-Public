package com.example.sadiq.test.CustomDataTypes;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mugen on 7/8/2017.
 */

public class TextViewTimer extends android.support.v7.widget.AppCompatTextView {

        int time;

        public TextViewTimer(Context context) {
                super(context);
                init();
        }

        public TextViewTimer(Context context, @Nullable AttributeSet attrs) {
                super(context, attrs);
                init();
        }

        public TextViewTimer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init();
        }

        public void init() {
                this.setText("swag");
                final TextViewTimer textViewTimer = this;
                time = 0;
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                                //        textViewTimer.setText(Integer.toString(time));
                                time++;
                        }
                }, 1);


                HandlerThread thread = new HandlerThread("thread");
                Handler handler = new Handler();
                handler.post(new TimerTask() {
                        @Override
                        public void run() {
                                textViewTimer.setText(Double.toString(Math.random()));
                                time++;

                        }
                });
        }


}
