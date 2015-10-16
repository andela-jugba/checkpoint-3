package com.andela.checkpoint.converter.model;

import com.andela.checkpoint.converter.ConverterCalculator.CurrencySymbols;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andela on 10/16/15.
 */
public class Currency {
    private String symbol;
    private double rate;
    private static String[] countries = CurrencySymbols.CURRENCY_SYMBOLS;
    private static double[] rates = CurrencySymbols.currencyRates;
    private static HashMap<String, Double> countryMap = new HashMap<>();
    private static List<Currency> currencies = new ArrayList<>();

    public Currency(String symbol, double rate) {
        this.symbol = symbol;
        this.rate = rate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public static String[] getCountries() {
        return countries;
    }

    public static void setCountries(String[] countries) {
        Currency.countries = countries;
    }

    public static double[] getRates() {
        return rates;
    }

    public static void setRates(double[] rates) {
        Currency.rates = rates;
    }

    public static HashMap<String, Double> getCountryMap() {
        for (int i = 0; i < countries.length; i++) {
            currencies.add(new Currency(countries[i], rates[i]));
        }
        for (int i = 0; i < currencies.size(); i++) {
            Currency temp = currencies.get(i);
            countryMap.put(temp.getSymbol(), temp.getRate());
        }
        return countryMap;
    }

    public static void setCountryMap() {
        for (int i = 0; i < countries.length; i++) {
            currencies.add(new Currency(countries[i], rates[i]));
        }
        for (int i = 0; i < currencies.size(); i++) {
            Currency temp = currencies.get(i);
            countryMap.put(temp.getSymbol(), temp.getRate());
        }
    }
}
