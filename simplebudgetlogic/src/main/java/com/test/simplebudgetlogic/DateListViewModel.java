package com.test.simplebudgetlogic;

import com.test.simplebudgetapi.model.Transaction;

import java.util.List;

public interface DateListViewModel {

    ResultObservable<List<Transaction>> fetchTransactionByDay(int year, int month, int day);

    ResultObservable<List<Transaction>> fetchTransactionByMonth(int year, int month);

}
