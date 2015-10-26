package com.andela.checkpoint.converter.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import com.andela.checkpoint.converter.ConverterCalculator.CurrencyCalculator;
import com.andela.checkpoint.converter.ConverterCalculator.CurrencySymbols;
import com.andela.checkpoint.converter.R;
import com.andela.checkpoint.converter.utilities.WheelSelector.Selector;
import com.andela.checkpoint.converter.calculator.Calculator;
import com.andela.checkpoint.converter.calculator.Constants;
import com.andela.checkpoint.converter.model.Currency;
import com.andela.checkpoint.converter.utilities.CustomTextView;

import java.util.Map;


public class ConvertFragment extends Fragment {

    private static String TAG = "ConvertFragment";
    private String[] countries = CurrencySymbols.CURRENCY_SYMBOLS;
    private double[] rates = Currency.getRates();
    private String[] operators = CurrencyCalculator.operators;
    private String operator;

    private Map<String, Double> map = Currency.getCountryMap();

    private TextView mResultTextView;

    private CurrencyCalculator currencyCalculator;

    public ConvertFragment() {
        currencyCalculator = new CurrencyCalculator();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CurrencyCalculator.updateRates();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_convert, container, false);
        mResultTextView = (TextView) v.findViewById(R.id.textViewResult);
        initWheels(v);
        initEditText(v);
        return v;
    }

    private void initEditText(final View v) {
        CustomTextView firstCurrencyEditText = new CustomTextView(v, R.id.editTextFirstCurrency, new CustomTextView.Callback() {
            @Override
            public void onTextChange(String s) {
                compute(v);

            }
        });
        CustomTextView secondCurrencyEditText = new CustomTextView(v, R.id.editTextSecondCurrency, new CustomTextView.Callback() {
            @Override
            public void onTextChange(String s) {
                compute(v);
            }
        });
    }

    private void initWheels(final View v) {
        Selector firstCurrencyWheel = new Selector(v, CurrencySymbols.CURRENCY_SYMBOLS, R.id.numberpicker, new Selector.CallBack() {
            @Override
            public void onChange(int newVal) {
                TextView textView = (TextView) v.findViewById(R.id.textViewFirstCurrencyLogo);
                textView.setText(countries[newVal]);
                CurrencyCalculator.setTargetCurrencyOne(map.get(countries[newVal]));
                compute(v);
            }
        });
        Selector operatorWheel = new Selector(v, Calculator.operators, R.id.numberpickerOperator, new Selector.CallBack() {
            @Override
            public void onChange(int newVal) {
                TextView textView = (TextView) v.findViewById(R.id.textViewOperator);
                textView.setText(operators[newVal]);
                operator = operators[newVal];
                compute(v);
            }
        });
        Selector secondCurrencyWheel = new Selector(v, CurrencySymbols.CURRENCY_SYMBOLS, R.id.numberpickerSecondCurrency, new Selector.CallBack() {
            @Override
            public void onChange(int newVal) {
                TextView textView = (TextView) v.findViewById(R.id.textViewSecondCurrencyLogo);
                textView.setText(countries[newVal]);
                CurrencyCalculator.setTargetCurrencyTwo(map.get(countries[newVal]));
                compute(v);

            }
        });
        Selector resultWheel = new Selector(v, CurrencySymbols.CURRENCY_SYMBOLS, R.id.numberpickerResult, new Selector.CallBack() {
            @Override
            public void onChange(int newVal) {
                TextView textView = (TextView) v.findViewById(R.id.textViewResultLogo);
                textView.setText(countries[newVal]);
                CurrencyCalculator.setBaseCurrency(map.get(countries[newVal]));
                compute(v);
            }
        });

    }

    public void compute(View v) {
        final double textCurrencyEditTextOne = getTextCurrencyEditText(v, R.id.editTextFirstCurrency);
        final double textCurrencyEditTextTwo = getTextCurrencyEditText(v, R.id.editTextSecondCurrency);
        double result = currencyCalculator.convert(textCurrencyEditTextOne, textCurrencyEditTextTwo,
                getOperatorText(v));
        mResultTextView.setText(String.valueOf(result));
    }

    private double getTextCurrencyEditText(View v, int viewId) {
        double d = 0;
        EditText editText = (EditText) v.findViewById(viewId);
        double length = editText.getText().length();
        if (length > 0) {
            String string = String.valueOf(editText.getText());
            if (!(length == 1 && string.startsWith("."))) {
                String s = String.valueOf(editText.getText());
                d = Double.parseDouble(s);
            }
        }
        return d;
    }

    public String getOperatorText(View v) {
        TextView textView = (TextView) v.findViewById(R.id.textViewOperator);
        String string = String.valueOf(textView.getText());
        if (string == "Operator") {
            return Constants.ADD;
        } else return string;
    }


}
