package com.andela.checkpoint.converter.ConverterCalculator;

import com.andela.checkpoint.converter.calculator.Calculator;
import com.andela.checkpoint.converter.calculator.Constants;

/**
 * Created by andela on 10/16/15.
 */
public class CurrencyCalculator extends Calculator{
    private final String TAG = "CurrencyCalculator";




    private static double baseCurrency;
    private static double targetCurrencyOne;
    private static double targetCurrencyTwo;

    public CurrencyCalculator(){
        this.baseCurrency = CurrencySymbols.currencyRates[0];
        this.targetCurrencyOne = CurrencySymbols.currencyRates[0];
        this.targetCurrencyTwo = CurrencySymbols.currencyRates[0];
    }

    public static final String[] operators = new String[]{Constants.ADD, Constants.SUBTRACT, Constants.DIVIDE, Constants.MULTIPLY};

    public double convert(double currencyOne, double currencyTwo, String operator){

        if(targetCurrencyOne == targetCurrencyTwo && baseCurrency == targetCurrencyOne && baseCurrency == targetCurrencyTwo){
            calculate(operator,currencyOne,currencyTwo);
            return getResult();
        }else if(targetCurrencyOne == targetCurrencyTwo){
            calculate(operator,currencyOne,currencyTwo);
             setResult(getResult() * baseCurrency);
            return getResult();
        } else {
            calculate(operator, convert(currencyOne,targetCurrencyOne, baseCurrency),convert(currencyTwo,targetCurrencyTwo, baseCurrency));
            return getResult();
        }
    }
    private double convert(double currency, double targetCurrency, double baseCurrency){
        return currency /targetCurrency * baseCurrency;
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
}
