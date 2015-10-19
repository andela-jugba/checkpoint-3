package com.andela.checkpoint.converter.calculator;

import java.text.DecimalFormat;

/**
 * Created by andela on 10/16/15.
 */
public class Calculator {

    private double result;
    public static final String[] operators = new String[]{Constants.ADD, Constants.SUBTRACT, Constants.DIVIDE, Constants.MULTIPLY};


    public Calculator() {
    }

    public void calculate(String operator, double firstNumber, double secondNumber) {
        switch (operator) {
            case Constants.ADD:
                setResult( roundTo2Decimals(firstNumber + secondNumber ));
                break;
            case Constants.SUBTRACT:
                setResult( roundTo2Decimals(firstNumber - secondNumber ) );
                break;
            case Constants.DIVIDE:
                if (secondNumber != 0) {
                    setResult(roundTo2Decimals(firstNumber / secondNumber));
                }
            break;
            case Constants.MULTIPLY:
                setResult(roundTo2Decimals(firstNumber * secondNumber));
                break;

        }
    }

    private  double roundTo2Decimals(double val) {
        DecimalFormat df2 = new DecimalFormat("###.##");
        return Double.valueOf(df2.format(val));
    }
    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
