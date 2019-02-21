package com.test.simplebudgetapi.exchange;

import com.test.simplebudgetapi.model.Currency;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExchangeServiceImpl implements ExchangeService {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private static final String BASE_URL = "http://www.apilayer.net/api/historical?access_key=7c5d8b193d7218941d429ef563e8346a";
    private static final String DATE = "&date=";
    private static final String FROM = "&source=";
    private static final String TO = "&currencies=";

    private static final String SUCCESS = "success";
    private static final String QUOTES = "quotes";
    private static final String ERROR_STRING_INVALID_RESPONSE = "Invalid Response";
    private static final String ERROR_STRING_QUERY_FAILED = "Query Failed";

    @Override
    public double exchangeOnDate(Currency from, Currency to, Date date, double amount) throws Exception{
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat(DATE_FORMAT);
        String url = generatesURL(from.toString(), to.toString(), simpleDateFormat.format(date));
        double rate = queryRate(from.toString(), to.toString(), url);
        return amount * rate;
    }

    private String generatesURL(String from, String to, String date) {
        StringBuilder stringBuilder = new StringBuilder(BASE_URL);
        stringBuilder.append(DATE);
        stringBuilder.append(date);
        stringBuilder.append(FROM);
        stringBuilder.append(from);
        stringBuilder.append(TO);
        stringBuilder.append(to);
        return stringBuilder.toString();
    }

    private double queryRate(String from, String to, String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream is = connection.getInputStream();
        StringBuilder response = new StringBuilder();
        readResponseFromInputStream(response, is);
        is.close();
        String jsonString = response.toString();
        JSONObject object = new JSONObject(jsonString);
        if (object.has(SUCCESS)) {
            boolean isSuccess = object.getBoolean(SUCCESS);
            if (isSuccess) {
                if (object.has(QUOTES)) {
                    JSONObject quotes = object.getJSONObject(QUOTES);
                    String currencyKey = from + to;
                    if (quotes.has(currencyKey)) {
                        return quotes.getDouble(currencyKey);
                    } else {
                        throw new Exception(ERROR_STRING_INVALID_RESPONSE);
                    }
                } else {
                    throw new Exception(ERROR_STRING_INVALID_RESPONSE);
                }
            } else {
                throw new Exception(ERROR_STRING_QUERY_FAILED);
            }
        } else {
            throw new Exception(ERROR_STRING_INVALID_RESPONSE);
        }
    }

    private void readResponseFromInputStream(StringBuilder response, InputStream is) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead = -1;
        while((bytesRead = is.read(buffer, 0, 1024)) != -1) {
            response.append(new String(buffer, 0, bytesRead));
        }
    }
}
