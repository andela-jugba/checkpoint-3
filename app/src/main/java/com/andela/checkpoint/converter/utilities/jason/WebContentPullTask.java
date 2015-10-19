package com.andela.checkpoint.converter.utilities.jason;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.NumberPicker;

import com.andela.checkpoint.converter.ConverterCalculator.CurrencySymbols;
import com.andela.checkpoint.converter.R;
import com.andela.checkpoint.converter.model.Currency;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by andela on 10/14/15.
 */

public class WebContentPullTask extends AsyncTask<String, Void, JSONObject> {
    private final String TAG = "WebContentPullTask";
    private httpsCall cP = new httpsCall();
    private Activity activity;

    public WebContentPullTask(Activity activity){
        this.activity = activity;
    }
    @Override
    protected JSONObject doInBackground(String... url) {
        return cP.getJSONFromUrl(url[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(final JSONObject s) {
        super.onPostExecute(s);
        //Log.i(TAG, s.toString());
        if(s != null) {
            try {
                double[] rates = new double[CurrencySymbols.NUMBER_OF_COUNTRIES];
                String[] countries = new String[CurrencySymbols.NUMBER_OF_COUNTRIES];


                JSONObject ratesObj = s.getJSONObject(CurrencySymbols.RATES);
                if (ratesObj == null) {
                    for (int i = 0; i < CurrencySymbols.CURRENCY_SYMBOLS.length; i++) {
                        countries[i] = s.getString(CurrencySymbols.CURRENCY_SYMBOLS[i]);
                    }
                    Log.i("Country 1 is ", countries[0]);
                    //Currency.setCountries(countries);

                } else {
                    rates[0] = 1;
                    for (int i = 1; i < CurrencySymbols.CURRENCY_SYMBOLS.length; i++) {
                        rates[i] = ratesObj.getDouble(CurrencySymbols.CURRENCY_SYMBOLS[i]);
                        Log.i("Dollar value", rates[i] + "");
                    }
                    Log.i("Dollar value", rates[2] + "");
//
//                    NumberPicker pickerC1 = (NumberPicker) activity.findViewById(R.id.numberpicker);
//                    NumberPicker pickerC2 = (NumberPicker) activity.findViewById(R.id.numberpickerSecondCurrency);
//                    NumberPicker pickerC3 = (NumberPicker) activity.findViewById(R.id.numberpickerResult);
//                    pickerC2.setDisplayedValues(CurrencySymbols.CURRENCY_SYMBOLS);
//                    pickerC3.setDisplayedValues(CurrencySymbols.CURRENCY_SYMBOLS);
//                    pickerC1.setDisplayedValues(CurrencySymbols.CURRENCY_SYMBOLS);

                    Currency.setRates(rates);
                    //Currency.setCountries(CurrencySymbols.CURRENCY_SYMBOLS);
                    Currency.setCountryMap();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}

