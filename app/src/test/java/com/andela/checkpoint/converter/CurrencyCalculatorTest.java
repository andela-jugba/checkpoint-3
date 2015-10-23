package com.andela.checkpoint.converter;

import com.andela.checkpoint.converter.ConverterCalculator.CurrencyCalculator;
import com.andela.checkpoint.converter.calculator.Calculator;
import com.andela.checkpoint.converter.calculator.Constants;
import com.andela.checkpoint.converter.model.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by andela-jugba on 10/22/15.
 */
public class CurrencyCalculatorTest {
    private CurrencyCalculator testCurrencyCalculator;
    private double baseCurrency;
    private double targetCurrencyOne;
    private double targetCurrencyTwo;
    @Before
    public void setUp() throws Exception {
        testCurrencyCalculator = new CurrencyCalculator();
        baseCurrency = 1;
        targetCurrencyOne = 100;
        targetCurrencyTwo = 200;
    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void testConvertSameCurrency() throws Exception {
        CurrencyCalculator.setBaseCurrency(baseCurrency);
        CurrencyCalculator.setTargetCurrencyOne(baseCurrency);
        CurrencyCalculator.setTargetCurrencyTwo(baseCurrency);

        assertEquals(testCurrencyCalculator.convert(10, 10, Constants.ADD), 20.0);
        assertEquals(testCurrencyCalculator.convert(10,10, Constants.MULTIPLY), 100.0);
        assertEquals(testCurrencyCalculator.convert(10,10, Constants.DIVIDE), 1.0);
        assertEquals(testCurrencyCalculator.convert(10,10,Constants.SUBTRACT), 0.0);

    }

    @Test
    public void testConvertBaseCurrencyAndOneTargetCurrency() throws Exception {
        CurrencyCalculator.setBaseCurrency(baseCurrency);
        CurrencyCalculator.setTargetCurrencyOne(baseCurrency);
        CurrencyCalculator.setTargetCurrencyTwo(targetCurrencyOne);

        assertEquals(testCurrencyCalculator.convert(1, 100, Constants.ADD), 2.0);
        assertEquals(testCurrencyCalculator.convert(1,100, Constants.SUBTRACT), 0.0);
        assertEquals(testCurrencyCalculator.convert(1,100, Constants.DIVIDE), 1.0);
        assertEquals(testCurrencyCalculator.convert(1,200, Constants.MULTIPLY), 2.0);

    }

    @Test
    public void testCovertSameTargetCurrencyAndDifferentBase() throws Exception {
        CurrencyCalculator.setBaseCurrency(baseCurrency);
        CurrencyCalculator.setTargetCurrencyOne(targetCurrencyOne);
        CurrencyCalculator.setTargetCurrencyTwo(targetCurrencyOne);

        assertEquals(testCurrencyCalculator.convert(1, 100, Constants.ADD), 1.01);
        assertEquals(testCurrencyCalculator.convert(1, 100, Constants.SUBTRACT), -0.99);
        assertEquals(testCurrencyCalculator.convert(1, 100, Constants.DIVIDE), 0.01);
        assertEquals(testCurrencyCalculator.convert(1, 200, Constants.MULTIPLY), 0.02);

    }

    @Test
    public void testConvertAllCurrenciesUnique() throws Exception {
        CurrencyCalculator.setBaseCurrency(baseCurrency);
        CurrencyCalculator.setTargetCurrencyOne(targetCurrencyOne);
        CurrencyCalculator.setTargetCurrencyTwo(targetCurrencyTwo);

        assertEquals(testCurrencyCalculator.convert(1, 100, Constants.ADD), 0.51);
        assertEquals(testCurrencyCalculator.convert(1, 100, Constants.SUBTRACT), -0.49);
        assertEquals(testCurrencyCalculator.convert(1, 100, Constants.DIVIDE), 0.02);
        assertEquals(testCurrencyCalculator.convert(1, 200, Constants.MULTIPLY), 0.01);

    }
}
