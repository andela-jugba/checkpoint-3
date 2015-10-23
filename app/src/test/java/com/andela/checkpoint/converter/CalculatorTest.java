package com.andela.checkpoint.converter;

import com.andela.checkpoint.converter.calculator.Calculator;
import com.andela.checkpoint.converter.calculator.Constants;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by andela-jugba on 10/22/15.
 */
public class CalculatorTest {
    Calculator testCalculator;

    @Before
    public void setUp() throws Exception {
        testCalculator = new Calculator();

    }

    @After
    public void tearDown() throws Exception {
        testCalculator = null;

    }

    private  double roundTo2Decimals(double val) {
        DecimalFormat df2 = new DecimalFormat("###.##");
        return Double.valueOf(df2.format(val));
    }

    @Test
    public void testAddition() throws Exception{
        testCalculator.calculate(Constants.ADD,1,1);
        assertEquals(testCalculator.getResult(), 2.0);

        testCalculator.calculate(Constants.ADD, 0, 1);
        assertEquals(testCalculator.getResult(), 1.0);

        testCalculator.calculate(Constants.ADD,45342345,24543);
        assertEquals(testCalculator.getResult(), 45342345+24543.0);
    }

    @Test
    public void testSubtraction() throws Exception{
        testCalculator.calculate(Constants.SUBTRACT,90,10);
        assertEquals(testCalculator.getResult(), 90 - 10.0);

        testCalculator.calculate(Constants.SUBTRACT, 10, 90);
        assertEquals(testCalculator.getResult(), 10 - 90.0);

        testCalculator.calculate(Constants.SUBTRACT, 0, 10);
        assertEquals(testCalculator.getResult(), 0-10.0);
    }

    @Test
    public void testDivision() throws Exception{
        testCalculator.calculate(Constants.DIVIDE, 1, 1);
        assertEquals(testCalculator.getResult(), 1 / 1.0);

        testCalculator.calculate(Constants.DIVIDE, 3549, 456);
        assertEquals(testCalculator.getResult(), roundTo2Decimals(3549 / 456.0));

    }

    @Test
    public void testDivisionByZero() throws Exception{
        testCalculator.calculate(Constants.DIVIDE, 45, 0);
        assertEquals(testCalculator.getResult(), 0.0);
    }

    @Test
    public void testMultiplication() throws Exception{
        testCalculator.calculate(Constants.MULTIPLY, 45, 68.55);
        assertEquals(testCalculator.getResult(), roundTo2Decimals(45 * 68.55));

        testCalculator.calculate(Constants.MULTIPLY, 45, 33123);
        assertEquals(testCalculator.getResult(), roundTo2Decimals(45 * 33123));

    }


}
