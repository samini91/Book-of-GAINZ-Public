package com.example.sadiq.test.Options;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sadiq.test.Database.OptionDB;
import com.example.sadiq.test.Database.RealmString;
import com.example.sadiq.test.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mugen on 11/24/2016.
 */

public class Option_List_Container extends RelativeLayout{

        @Bind(R.id.option_recyclerview_option_textview)
        TextView OptionKey;

        List<RealmString> OptionValues;
        //@Bind(R.id.)

        public Option_List_Container(Context context) {
                super(context);
                init(context);
        }

        public Option_List_Container(Context context, AttributeSet attrs) {
                super(context, attrs);
                init(context);
        }

        public Option_List_Container(Context context, AttributeSet attrs, int defStyleAttr) {
                super(context, attrs, defStyleAttr);
                init(context);
        }

        private void init(Context context)
        {
                inflate(context, R.layout.options_options_list_container, this);
                ButterKnife.bind(this);
        }

        public List<RealmString> getOptionValues(){return OptionValues;}
        public String getOptionKey(){return OptionKey.getText().toString();}

        public void bind(final OptionDB OptionDB_Object){
               OptionKey.setText(OptionDB_Object.getOption());
               OptionValues = OptionDB_Object.getValues();
           //     Value_Number.setText(OptionDB_Object.getValues().first().toString());
        }

}
