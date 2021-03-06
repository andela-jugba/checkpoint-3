package com.andela.checkpoint.converter.ui_helpers.WheelSelector;


import android.view.View;
import android.widget.NumberPicker;

/**
 * Created by andela on 10/16/15.
 */
public class Selector {
    private NumberPicker picker;
    private String[] values;
    private int numberViewId;
    private View v;

    CallBack callBacked;

    public Selector(View v, String[] values, int numberViewId, CallBack callBack) {
        this.values = values;
        this.numberViewId = numberViewId;
        this.callBacked = callBack;
        this.v = v;
        init();
    }

    public void init() {
        picker = (NumberPicker) v.findViewById(numberViewId);
        picker.setMaxValue(values.length - 1);
        picker.setMinValue(0);
        picker.setDisplayedValues(values);
        picker.setWrapSelectorWheel(true);
        picker.setOnValueChangedListener(new NumberPicker.
                OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int
                    oldVal, int newVal) {
                callBacked.onChange(newVal);
            }
        });

    }

    public interface CallBack {
        void onChange(int newVal);
    }
}

