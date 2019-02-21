package com.test.simplebudgetlogic;

import com.test.simplebudgetapi.model.Category;
import com.test.simplebudgetapi.model.Transaction;

import java.util.List;

public interface OveralListViewModel {

    ResultObservable<List<Transaction>> fetchAllTransation();

    ResultObservable<List<Category>> fetchAllCategories();

    void addTransaction(Transaction transaction, Category category);
}
