package com.test.simplebudgetapi.exchange;

import com.test.simplebudgetapi.model.Currency;

import java.util.Date;

public interface ExchangeService {

    double exchangeOnDate(Currency from, Currency to, Date date, double amount) throws Exception;

}
