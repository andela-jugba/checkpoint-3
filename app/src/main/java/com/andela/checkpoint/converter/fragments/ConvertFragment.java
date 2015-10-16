package com.andela.checkpoint.converter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andela.checkpoint.converter.ConverterCalculator.CurrencySymbols;
import com.andela.checkpoint.converter.R;
import com.andela.checkpoint.converter.WheelSelector.Selector;
import com.andela.checkpoint.converter.calculator.Calculator;


public class ConvertFragment extends Fragment {

    private static String TAG = "ConvertFragment";
    private TextView mTextViewOperator;

    public ConvertFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_convert, container, false);
        initWheels(v);
        return v;
    }

    private void initWheels(View v){
        Selector firstCurrencyWheel = new Selector(v, CurrencySymbols.CURRENCY_SYMBOLS, R.id.numberpicker, new Selector.CallBack() {
            @Override
            public void onChange(int newVal) {

            }
        });
        Selector operatorWheel = new Selector(v, Calculator.operators, R.id.numberpickerOperator, new Selector.CallBack() {
            @Override
            public void onChange(int newVal) {
                Log.i(TAG, newVal + "");
            }
        });
        Selector secondCurrencyWheel = new Selector(v, CurrencySymbols.CURRENCY_SYMBOLS, R.id.numberpickerSecondCurrency, new Selector.CallBack() {
            @Override
            public void onChange(int newVal) {

            }
        });
        Selector resultWheel = new Selector(v, CurrencySymbols.CURRENCY_SYMBOLS, R.id.numberpickerResult, new Selector.CallBack() {
            @Override
            public void onChange(int newVal) {

            }
        });

    }


}
