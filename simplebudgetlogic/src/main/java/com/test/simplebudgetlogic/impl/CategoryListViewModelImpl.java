package com.test.simplebudgetlogic.impl;

import com.test.simplebudgetapi.SimpleBudgetAPI;
import com.test.simplebudgetapi.model.Category;
import com.test.simplebudgetapi.model.Transaction;
import com.test.simplebudgetlogic.CategoryListViewModel;
import com.test.simplebudgetlogic.ResultObservable;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CategoryListViewModelImpl implements CategoryListViewModel {

    private SimpleBudgetAPI mAPI;

    public CategoryListViewModelImpl(SimpleBudgetAPI api) {
        mAPI = api;
    }

    @Override
    public void addCategory(final Category category) {
        new RxRunner<Category>().run(new RxRunner.RxRunnerExecutor<Category>() {
            @Override
            public Category execute() {
                mAPI.addCategory(category);
                return null;
            }
        }, null);
    }

    @Override
    public void updateCategory(final Category category) {
        new RxRunner<Category>().run(new RxRunner.RxRunnerExecutor<Category>() {
            @Override
            public Category execute() {
                mAPI.updateCategory(category);
                return null;
            }
        }, null);
    }

    @Override
    public ResultObservable<List<Transaction>> fetchTransactionByCategory(final Category category) {
        final ResultObservable<List<Transaction>> resultObservable = new ResultObservable<>();
        new RxRunner<List<Transaction>>().run(new RxRunner.RxRunnerExecutor<List<Transaction>>() {
            @Override
            public List<Transaction> execute() {
                return mAPI.fetchTransactionByCategory(category);
            }
        }, resultObservable);
        return resultObservable;
    }
}
