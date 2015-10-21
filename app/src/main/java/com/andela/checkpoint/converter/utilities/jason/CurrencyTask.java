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

    @Override
    protected JSONObject doInBackground(String... url) {
        return getRatesJson(url[0]);
    }

    private JSONObject getRatesJson(String address) {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader reader = null;
        JSONObject jsonObject = null;

        try {
            URL url = new URL(address);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";

            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
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

}

