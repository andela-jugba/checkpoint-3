package com.andela.checkpoint.converter.ConverterCalculator;

/**
 * Created by andela on 10/16/15.
 */
public class CurrencySymbols {
    public static final String CURRENCY_SYMBOLS[] = new String[]{"USD","EUR","NGN","NAD","GBP", "HKD", "EGP", "ZAR", "TRY","INR"};
    public static final String API_RATES_URL = "https://openexchangerates.org/api/latest.json?app_id=8102acb517204e3eaf70c752e6de8628";
    public static final String API_NAMES_URL = "https://openexchangerates.org/api/currencies.json?app_id=8102acb517204e3eaf70c752e6de8628";
    public static final String RATES = "rates";
    public static final int NUMBER_OF_COUNTRIES = CURRENCY_SYMBOLS.length;
    public static double currencyRates[] = new double[]{1,1.22,199.43,1.1,2.5,100,24,56,400,12};
}
