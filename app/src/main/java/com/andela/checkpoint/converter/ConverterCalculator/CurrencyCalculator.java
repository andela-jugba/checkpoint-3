package com.andela.checkpoint.converter.ConverterCalculator;

import android.util.Log;

import com.andela.checkpoint.converter.calculator.Calculator;
import com.andela.checkpoint.converter.calculator.Constants;
import com.andela.checkpoint.converter.model.Currency;
import com.andela.checkpoint.converter.utilities.jason.CurrencyTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by andela on 10/16/15.
 */
public class CurrencyCalculator extends Calculator {

    private static double baseCurrency;
    private static double targetCurrencyOne;
    private static double targetCurrencyTwo;

    public CurrencyCalculator() {
        this.baseCurrency = CurrencySymbols.currencyRates[0];
        this.targetCurrencyOne = CurrencySymbols.currencyRates[0];
        this.targetCurrencyTwo = CurrencySymbols.currencyRates[0];
    }

    public static final String[] operators = new String[]{Constants.ADD, Constants.SUBTRACT, Constants.DIVIDE, Constants.MULTIPLY};

    public double convert(double currencyOne, double currencyTwo, String operator) {
        calculate(operator, convert(currencyOne, targetCurrencyOne, baseCurrency), convert(currencyTwo, targetCurrencyTwo, baseCurrency));
        return getResult();
    }

    private double convert(double currency, double targetCurrency, double baseCurrency) {
        return currency / targetCurrency * baseCurrency;
    }

    public static void setBaseCurrency(double baseCurrency) {
        CurrencyCalculator.baseCurrency = baseCurrency;
    }

    public static void setTargetCurrencyTwo(double targetCurrencyTwo) {
        CurrencyCalculator.targetCurrencyTwo = targetCurrencyTwo;
    }

    public static void setTargetCurrencyOne(double targetCurrencyOne) {
        CurrencyCalculator.targetCurrencyOne = targetCurrencyOne;
    }

    public static void updateRates() {
        CurrencyTask wp = new CurrencyTask();
        try {
            JSONObject jason = wp.execute(CurrencySymbols.API_RATES_URL).get();
            update(jason);

        } catch (InterruptedException | JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void update(JSONObject object) throws JSONException {
        if (object != null) {

            double[] rates = new double[CurrencySymbols.NUMBER_OF_COUNTRIES];
            String[] countries = new String[CurrencySymbols.NUMBER_OF_COUNTRIES];

            JSONObject ratesObj = object.getJSONObject(CurrencySymbols.RATES);
            rates[0] = 1;
            for (int i = 1; i < CurrencySymbols.CURRENCY_SYMBOLS.length; i++) {
                rates[i] = ratesObj.getDouble(CurrencySymbols.CURRENCY_SYMBOLS[i]);
            }

            Currency.setRates(rates);
            Currency.setCountryMap();

        }
    }
}
