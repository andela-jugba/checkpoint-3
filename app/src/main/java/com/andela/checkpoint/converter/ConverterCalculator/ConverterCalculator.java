package com.andela.checkpoint.converter.ConverterCalculator;

import com.andela.checkpoint.converter.calculator.Calculator;
import com.andela.checkpoint.converter.calculator.Constants;

/**
 * Created by andela on 10/16/15.
 */
public class ConverterCalculator extends Calculator{
    private static double baseCurrency;
    private static double targetCurrencyOne;
    private static double targetCurrencyTwo;

    private static boolean baseCurrencySet;
    private static boolean targetCurrencyOneSet;
    private static boolean targetCurrencyTwoSet;

    public static final String[] operators = new String[]{Constants.ADD, Constants.SUBTRACT, Constants.DIVIDE, Constants.MULTIPLY};

    public double convert(double currencyOne, double currencyTwo, String operator, double baseCurrency){
        if(targetCurrencyOne == targetCurrencyTwo){
            calculate(operator,currencyTwo,currencyOne);
            return getResult();
        }else{
            calculate(operator, convert(currencyOne,targetCurrencyOne, baseCurrency),convert(currencyTwo,targetCurrencyTwo, baseCurrency));
            return getResult();
        }
    }
    private double convert(double currency, double targetCurrency, double baseCurrency){
        return currency * targetCurrency * baseCurrency;
    }

    public static double getBaseCurrency() {
        return baseCurrency;
    }

    public static void setBaseCurrency(double baseCurrency) {
        ConverterCalculator.baseCurrency = baseCurrency;
    }

    public static boolean isTargetCurrencyTwoSet() {
        return targetCurrencyTwoSet;
    }

    public static void setTargetCurrencyTwoSet(boolean targetCurrencyTwoSet) {
        ConverterCalculator.targetCurrencyTwoSet = targetCurrencyTwoSet;
    }

    public static boolean isTargetCurrencyOneSet() {
        return targetCurrencyOneSet;
    }

    public static void setTargetCurrencyOneSet(boolean targetCurrencyOneSet) {
        ConverterCalculator.targetCurrencyOneSet = targetCurrencyOneSet;
    }

    public static boolean isBaseCurrencySet() {
        return baseCurrencySet;
    }

    public static void setBaseCurrencySet(boolean baseCurrencySet) {
        ConverterCalculator.baseCurrencySet = baseCurrencySet;
    }

    public static double getTargetCurrencyTwo() {
        return targetCurrencyTwo;
    }

    public static void setTargetCurrencyTwo(double targetCurrencyTwo) {
        ConverterCalculator.targetCurrencyTwo = targetCurrencyTwo;
    }

    public static double getTargetCurrencyOne() {
        return targetCurrencyOne;
    }

    public static void setTargetCurrencyOne(double targetCurrencyOne) {
        ConverterCalculator.targetCurrencyOne = targetCurrencyOne;
    }
}
