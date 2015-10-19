package com.andela.checkpoint.converter.model;

import com.andela.checkpoint.converter.ConverterCalculator.CurrencySymbols;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by andela on 10/16/15.
 */
public class Currency implements Comparable<Currency>{
    private String symbol;
    private double rate;
    private static String[] countries = CurrencySymbols.CURRENCY_SYMBOLS;
    private static double[] rates = CurrencySymbols.currencyRates;
    private static HashMap<String, Double> countryMap = new HashMap<>();
    public static List<Currency> currencies = new ArrayList<>();

    public Currency(String symbol, double rate) {
        this.symbol = symbol;
        this.rate = rate;
    }

    public String getSymbol() {
        return symbol;
    }

    public int compareTo(Currency currency){
        if(this.getRate() > currency.getRate())return 1;
        else if(this.getRate() == currency.getRate())return 0;
        else return -1;
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
        List<Currency> tempCurrencies =  new ArrayList<>();
        for (int i = 0; i < countries.length; i++) {
            tempCurrencies.add(new Currency(countries[i], rates[i]));
        }
        currencies = tempCurrencies;
        for (int i = 0; i < currencies.size(); i++) {
            Currency temp = currencies.get(i);
            countryMap.put(temp.getSymbol(), temp.getRate());
        }
        return countryMap;
    }

    public static void setCountryMap() {
        List<Currency> tempCurrencies =  new ArrayList<>();
        for (int i = 0; i < countries.length; i++) {
            tempCurrencies.add(new Currency(countries[i], rates[i]));
        }
        currencies = tempCurrencies;
        for (int i = 0; i < currencies.size(); i++) {
            Currency temp = currencies.get(i);
            countryMap.put(temp.getSymbol(), temp.getRate());
        }
    }
    public static ArrayList<Currency> getTopTen(){
        ArrayList<Currency> topTen = new ArrayList<>();
        PriorityQueue<Currency> temp = new PriorityQueue<>();
        for (int i = 0; i < currencies.size(); i++) {
            temp.offer(currencies.get(i));
        }
        while(!temp.isEmpty()){
            topTen.add(temp.poll());
        }
        return topTen;
    }
    public static HashMap<String, Double> getMap(){
        return countryMap;
    }
}
