package com.test.simplebudgetlogic.impl;

import com.test.simplebudgetapi.SimpleBudgetAPI;
import com.test.simplebudgetapi.model.Category;
import com.test.simplebudgetapi.model.Transaction;
import com.test.simplebudgetlogic.ResultObservable;
import com.test.simplebudgetlogic.OveralListViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class OveralListViewModelImpl implements OveralListViewModel {

    private SimpleBudgetAPI mAPI;

    public OveralListViewModelImpl(SimpleBudgetAPI api) {
        mAPI = api;
    }

    @Override
    public ResultObservable<List<Transaction>> fetchAllTransation() {
        final ResultObservable<List<Transaction>> resultObservable = new ResultObservable<>();
        new RxRunner<List<Transaction>>().run(new RxRunner.RxRunnerExecutor<List<Transaction>>() {
            @Override
            public List<Transaction> execute() {
                return mAPI.fetchAll();
            }
        }, resultObservable);
        return resultObservable;
    }

    @Override
    public ResultObservable<List<Category>> fetchAllCategories() {
        final ResultObservable<List<Category>> resultObservable = new ResultObservable<>();
        new RxRunner<List<Category>>().run(new RxRunner.RxRunnerExecutor<List<Category>>() {
            @Override
            public List<Category> execute() {
                return mAPI.getAllCategory();
            }
        }, resultObservable);
        return resultObservable;
    }

    @Override
    public void addTransaction(final Transaction transaction, final Category category) {
        new RxRunner<Transaction>().run(new RxRunner.RxRunnerExecutor<Transaction>() {
            @Override
            public Transaction execute() {
                mAPI.addTransaction(transaction, category);
                return null;
            }
        }, null);
    }

}
