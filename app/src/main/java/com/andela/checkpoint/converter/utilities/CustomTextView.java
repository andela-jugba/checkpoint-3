package com.andela.checkpoint.converter.utilities;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * Created by andela on 10/16/15.
 */
public class CustomTextView {
    private Callback callback;
    private EditText editText;
    private int displayView;
    private View v;


    public CustomTextView(View v, int numberView, Callback callback){
        this.callback = callback;
        this.displayView = numberView;
        this.v = v;
        init();

    }
    private void init(){
        editText = (EditText) v.findViewById(displayView);
        editText.addTextChangedListener(getTextWatcher());
    }
    private TextWatcher getTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                callback.onTextChange(editable.toString());
            }
        };
    }

    public interface Callback{
        void onTextChange(String s);
    }
}
