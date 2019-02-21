package com.test.simplebudgetapi;

import android.support.test.runner.AndroidJUnit4;

import com.test.simplebudgetapi.model.Currency;
import com.test.simplebudgetapi.exchange.ExchangeServiceImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ExchangeServiceUnitTest {
    private ExchangeServiceImpl exchangeServiceImpl;

    @Before
    public void init() {
        exchangeServiceImpl = new ExchangeServiceImpl();
    }

    @Test
    public void simpleTest() throws Exception {
        Date date = generateDate(2018, 9, 1);
        double valueInNZD = exchangeServiceImpl.exchangeOnDate(Currency.USD, Currency.NZD, date, 100.00);
        assertEquals(valueInNZD, 151.433, 0.0);
    }

    private static Date generateDate(int year, int monthOfYear, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, monthOfYear, dayOfMonth);
        return new Date(cal.getTimeInMillis());
    }
}
