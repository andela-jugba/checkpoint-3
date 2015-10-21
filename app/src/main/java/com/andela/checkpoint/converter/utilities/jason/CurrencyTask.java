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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by andela on 10/14/15.
 */

public class CurrencyTask extends AsyncTask<String, Void, JSONObject> {
    private final String TAG = "CurrencyTask";

    public CurrencyTask(Activity activity){
    }
    @Override
    protected JSONObject doInBackground(String... url) {
        return getRatesJson(url[0]);
    }
    private JSONObject getRatesJson(String address) {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader reader = null;
        JSONObject jsonObject = null;

        try{
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";

            while ((line  = reader.readLine()) != null){
                stringBuffer.append(line);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(reader != null){
                try{
                    reader.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        try {
            jsonObject = new JSONObject(stringBuffer.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

//    @Override
//    protected void onPostExecute(final JSONObject s) {
//        super.onPostExecute(s);
//        //Log.i(TAG, s.toString());
//        if(s != null) {
//            try {
//                double[] rates = new double[CurrencySymbols.NUMBER_OF_COUNTRIES];
//                String[] countries = new String[CurrencySymbols.NUMBER_OF_COUNTRIES];
//
//
//                JSONObject ratesObj = s.getJSONObject(CurrencySymbols.RATES);
//                if (ratesObj == null) {
//                    for (int i = 0; i < CurrencySymbols.CURRENCY_SYMBOLS.length; i++) {
//                        countries[i] = s.getString(CurrencySymbols.CURRENCY_SYMBOLS[i]);
//                    }
//                    Log.i("Country 1 is ", countries[0]);
//                    //Currency.setCountries(countries);
//
//                } else {
//                    rates[0] = 1;
//                    for (int i = 1; i < CurrencySymbols.CURRENCY_SYMBOLS.length; i++) {
//                        rates[i] = ratesObj.getDouble(CurrencySymbols.CURRENCY_SYMBOLS[i]);
//                        Log.i("Dollar value", rates[i] + "");
//                    }
//                    Log.i("Dollar value", rates[2] + "");
//
//                    Currency.setRates(rates);
//                    Currency.setCountryMap();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }


}

