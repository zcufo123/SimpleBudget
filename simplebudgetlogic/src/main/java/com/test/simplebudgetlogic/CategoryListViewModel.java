package com.test.simplebudgetlogic;

import com.test.simplebudgetapi.model.Category;
import com.test.simplebudgetapi.model.Transaction;

import java.util.List;

public interface CategoryListViewModel {

    void addCategory(Category category);

    void updateCategory(Category category);

    ResultObservable<List<Transaction>> fetchTransactionByCategory(Category category);
}