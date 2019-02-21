package com.test.simplebudgetlogic.impl;

import com.test.simplebudgetapi.SimpleBudgetAPI;
import com.test.simplebudgetapi.model.Transaction;
import com.test.simplebudgetlogic.DateListViewModel;
import com.test.simplebudgetlogic.ResultObservable;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DateListViewModelImpl implements DateListViewModel {

    private SimpleBudgetAPI mAPI;

    public DateListViewModelImpl(SimpleBudgetAPI api) {
        mAPI = api;
    }

    @Override
    public ResultObservable<List<Transaction>> fetchTransactionByDay(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, 0, 0, 0);
        final Date start = new Date(cal.getTimeInMillis());
        cal.set(year, month, day, 23, 59, 59);
        final Date end = new Date(cal.getTimeInMillis());
        final ResultObservable<List<Transaction>> resultObservable = new ResultObservable<>();
        new RxRunner<List<Transaction>>().run(new RxRunner.RxRunnerExecutor<List<Transaction>>() {
            @Override
            public List<Transaction> execute() {
                return mAPI.fetchTransactionByDate(start, end);
            }
        }, resultObservable);
        return resultObservable;
    }

    @Override
    public ResultObservable<List<Transaction>> fetchTransactionByMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1, 0, 0, 0);
        final Date start = new Date(cal.getTimeInMillis());
        cal.set(year, month, cal.getActualMaximum(Calendar.DAY_OF_MONTH), 23, 59, 59);
        final Date end = new Date(cal.getTimeInMillis());
        final ResultObservable<List<Transaction>> resultObservable = new ResultObservable<>();
        new RxRunner<List<Transaction>>().run(new RxRunner.RxRunnerExecutor<List<Transaction>>() {
            @Override
            public List<Transaction> execute() {
                return mAPI.fetchTransactionByDate(start, end);
            }
        }, resultObservable);
        return resultObservable;
    }
}
