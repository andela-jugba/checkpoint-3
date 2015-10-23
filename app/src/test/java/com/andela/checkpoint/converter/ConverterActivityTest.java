package com.andela.checkpoint.converter;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.andela.checkpoint.converter.ConverterCalculator.CurrencyCalculator;
import com.andela.checkpoint.converter.ConverterCalculator.CurrencySymbols;
import com.andela.checkpoint.converter.calculator.Constants;
import com.andela.checkpoint.converter.fragments.ConvertFragment;
import com.andela.checkpoint.converter.fragments.TopTenFragment;
import com.andela.checkpoint.converter.model.Currency;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;


import java.text.DecimalFormat;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.robolectric.shadows.ShadowView.clickOn;

/**
 * Created by andela on 10/15/15.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class,  sdk = 21)
public class ConverterActivityTest {
    private ConverterActivity converterActivity;
    private ConvertFragment convertFragment;
    private TopTenFragment topTenFragment;
    private TabLayout tabLayout;
    private FragmentManager testFragmentManager;
    private ViewPager viewPager;
    private ConverterActivity.ViewPagerAdapter viewPagerAdapter;
    private View v;

    @Before
    public void setUp() throws Exception {
        converterActivity = Robolectric.setupActivity(ConverterActivity.class);
        testFragmentManager = converterActivity.getSupportFragmentManager();
        viewPager = (ViewPager) converterActivity.findViewById(R.id.viewpager);
        viewPagerAdapter = (ConverterActivity.ViewPagerAdapter) viewPager.getAdapter();
        convertFragment = (ConvertFragment) viewPagerAdapter.getItem(0);
        v = convertFragment.getView();
    }

    @After
    public void tearDown() throws Exception {
        converterActivity = null;

    }

    @Test
    public void testCovertWithAllCurrenciesDifferent() throws Exception {
        // Given that I a user,
        // And I am on the converter screen
        assertNotNull(convertFragment);
        // when I enter $10 plus 10 pound to get get my result in Naira,
        // then I should get a result of 10 * dollarToNairaRate + 10 * PoundToNairaRate
        whenIEnterTheTwoDifferentTargetCurrencies(10, 10);
        assertEquals(thenIShouldGetAResultAOf(),roundTo2Decimals(convert(10,getCurrencyRate("USD"),
                getCurrencyRate("NGN")) + convert(10,getCurrencyRate("GBP"),getCurrencyRate("NGN"))),0);

        //When I change the operation to Minus
        assertEquals(whenIChangeTheOperatorToMinus(), roundTo2Decimals(convert(10,getCurrencyRate("USD"),
                getCurrencyRate("NGN")) - convert(10,getCurrencyRate("GBP"),getCurrencyRate("NGN"))),0);

        //when I change the operation to Divide
        assertEquals(whenIChangeTheOperatorToDivide(), roundTo2Decimals(convert(10, getCurrencyRate("USD"),
                getCurrencyRate("NGN")) / convert(10, getCurrencyRate("GBP"), getCurrencyRate("NGN"))),0);

        //when I change the operation to Multiple
        assertEquals(whenIChangeTheOperatorToMultiply(), roundTo2Decimals(convert(10,getCurrencyRate("USD"),
                getCurrencyRate("NGN")) * convert(10,getCurrencyRate("GBP"),getCurrencyRate("NGN"))),0);


    }

    private void whenIEnterTheTwoDifferentTargetCurrencies(int i, int i1) {
        EditText firstCurrency = (EditText)v.findViewById(R.id.editTextFirstCurrency);
        firstCurrency.setText(String.valueOf(i));
        setCurrencyOne(0);
        TextView textView = (TextView) v.findViewById(R.id.textViewOperator);
        textView.setText(Constants.ADD);
        setCurrencyTwo(4);


        EditText secondCurrency = (EditText) v.findViewById(R.id.editTextSecondCurrency);
        secondCurrency.setText(String.valueOf(i1));

        setBaseCurrency(2);

        convertFragment.compute(v);

    }

    @Test
    public void testConverterWithSameTargetCurrenciesAndDifferentBase() throws Exception {
        // Given that I a user,
        // And I am on the converter screen
        assertNotNull(convertFragment);
        // when I enter $10 plus $10  to get get my result in Naira,
        // then I should get a result of 10 * dollarToNairaRate + 10 * PoundToNairaRate
        whenIEnterAValueTOConvertSameBaseDollar(10, 10);
        assertEquals(thenIShouldGetAResultAOf(), roundTo2Decimals((10 + 10) * getCurrencyRate("NGN")), 0);

        //when I change the operation to minus, then
        assertEquals(whenIChangeTheOperatorToMinus(), 0, 0);

        //when I change the operation to multipy
        assertEquals(whenIChangeTheOperatorToMultiply(), roundTo2Decimals(10 * getCurrencyRate("NGN") * 10 * getCurrencyRate("NGN")), 0);

        //when I change the operation to divide
        assertEquals(whenIChangeTheOperatorToDivide(), roundTo2Decimals((10*getCurrencyRate("NGN")) / (10*getCurrencyRate("NGN"))),0);

    }
    
    private double getCurrencyRate(String dollar){
        Map<String,Double> map =  Currency.getCountryMap();
        return map.get(dollar);
    }
    private void setCurrencyOne(int i) {
         Map<String,Double> map =  Currency.getCountryMap();
        String[] countries = CurrencySymbols.CURRENCY_SYMBOLS;
        CurrencyCalculator.setTargetCurrencyOne(map.get(countries[i]));
    }
    private void setCurrencyTwo(int i){
        Map<String,Double> map =  Currency.getCountryMap();
        String[] countries = CurrencySymbols.CURRENCY_SYMBOLS;
        CurrencyCalculator.setTargetCurrencyTwo(map.get(countries[i]));
    }
    private void setBaseCurrency(int i){
        Map<String,Double> map =  Currency.getCountryMap();
        String[] countries = CurrencySymbols.CURRENCY_SYMBOLS;
        CurrencyCalculator.setBaseCurrency(map.get(countries[i]));
    }

    private void whenIEnterAValueTOConvertSameBaseDollar(int i, int i1) {
        EditText firstCurrency = (EditText)v.findViewById(R.id.editTextFirstCurrency);
        firstCurrency.setText(String.valueOf(i));

        TextView textView = (TextView) v.findViewById(R.id.textViewOperator);
        textView.setText(Constants.ADD);


        EditText secondCurrency = (EditText) v.findViewById(R.id.editTextSecondCurrency);
        secondCurrency.setText(String.valueOf(i1));

        setBaseCurrency(2);

        convertFragment.compute(v);
    }

    @Test
    public void testConvertFragmentWithSameCurrency() throws Exception {
        // Given that I am User
        // And I am on the Converter screen,
        assertNotNull(convertFragment);

        // when I enter a Value to convert ($50) plus ($50)
        // then I Should get a result of (100)
        assertEquals(whenIEnterAValueTOConvert(50, 50), 100.0, 0);

        // when I change the operation to minus()
        // then I should get a result of $0
        assertEquals(whenIChangeTheOperatorToMinus(), 0, 0);

        // when I change the operation to multiplication
        // then I should get a result of $2500
        assertEquals(whenIChangeTheOperatorToMultiply(), 2500.0, 0);

        //when I change the operation to division
        // then I should  get a result of $1
        assertEquals(whenIChangeTheOperatorToDivide(), 1.0, 0);


    }

    private double whenIChangeTheOperatorToDivide() {
        TextView textView = (TextView) v.findViewById(R.id.textViewOperator);
        textView.setText(Constants.DIVIDE);
        convertFragment.compute(v);

        TextView result = (TextView) convertFragment.getView().findViewById(R.id.textViewResult);
        return Double.valueOf((String) result.getText());
    }

    private double whenIChangeTheOperatorToMultiply() {
        TextView textView = (TextView) v.findViewById(R.id.textViewOperator);
        textView.setText(Constants.MULTIPLY);
        convertFragment.compute(v);

        TextView result = (TextView) convertFragment.getView().findViewById(R.id.textViewResult);
        return Double.valueOf((String) result.getText());
    }

    private double whenIChangeTheOperatorToMinus() {
        TextView textView = (TextView) v.findViewById(R.id.textViewOperator);
        textView.setText(Constants.SUBTRACT);
        convertFragment.compute(v);

        TextView result = (TextView) convertFragment.getView().findViewById(R.id.textViewResult);
        return Double.valueOf((String) result.getText());
    }

    private double thenIShouldGetAResultAOf() {
        TextView result = (TextView) convertFragment.getView().findViewById(R.id.textViewResult);
        return Double.valueOf((String) result.getText());
    }

    private double whenIEnterAValueTOConvert(double i, double i1) {


        EditText firstCurrency = (EditText)convertFragment.getView().findViewById(R.id.editTextFirstCurrency);
        firstCurrency.setText(String.valueOf(i));

        TextView textView = (TextView) v.findViewById(R.id.textViewOperator);
        textView.setText(Constants.ADD);


        EditText secondCurrency = (EditText)convertFragment.getView().findViewById(R.id.editTextSecondCurrency);
        secondCurrency.setText(String.valueOf(i1));

        convertFragment.compute(v);

        TextView result = (TextView) convertFragment.getView().findViewById(R.id.textViewResult);
        return Double.valueOf((String) result.getText());
    }

    @Test
    public void testTopTenFragment() throws Exception {
        //Given I am a User
        //When I am on the Home screen
        //And I tap the Top Ten tab]
        tabLayout = (TabLayout)converterActivity.findViewById(R.id.sliding_tabs);
        clickOn(tabLayout.getChildAt(0));

        //clickOn();
        topTenFragment = (TopTenFragment) converterActivity.getSupportFragmentManager().findFragmentById(R.id.viewpager);
        assertNotNull(topTenFragment);
        // Then I see a list of the top ten currencies
        FrameLayout layout = (FrameLayout)topTenFragment.getView();
        RecyclerView recyclerView = (RecyclerView) layout.findViewById(R.id.recyclerView);
        assertEquals(recyclerView.getAdapter().getItemCount(),10);


    }

    @Test
    public void testNumberWheel() throws Exception {
        NumberPicker operator = (NumberPicker)convertFragment.getView().findViewById(R.id.numberpickerOperator);
        operator.setValue(0);
        assertEquals(operator.getDisplayedValues()[0],"+");
    }

    private  double roundTo2Decimals(double val) {
        DecimalFormat df2 = new DecimalFormat("###.##");
        return Double.valueOf(df2.format(val));
    }
    private double convert(double currency, double targetCurrency, double baseCurrency) {
        return currency / targetCurrency * baseCurrency;
    }

}
