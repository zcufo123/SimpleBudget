package com.test.simplebudgetapi;

import android.content.Context;

import com.test.simplebudgetapi.impl.SimpleBudgetAPIImpl;
import com.test.simplebudgetapi.model.Category;
import com.test.simplebudgetapi.model.Currency;
import com.test.simplebudgetapi.model.Transaction;

import java.util.Date;
import java.util.List;

/**
 * Public interface to access all the functions that provided by the SimpleBudget
 * <p>
 * This API is providing the functions including Transaction Management and Category Management.
 */
public interface SimpleBudgetAPI {

    class Builder {
        public static SimpleBudgetAPI build(Context context) {
            return new SimpleBudgetAPIImpl(context);
        }
    }

    /**
     * Add new Transaction.
     *
     * @param transaction New Transaction need to be added.
     * @param category Which category the transaction belongs to.
     */
    void addTransaction(Transaction transaction, Category category);

    /**
     * Update Transaction.
     *
     * @param transaction New Transaction need to be updated.
     */
    void updateTransaction(Transaction transaction);

    /**
     * Delete Transaction.
     *
     * @param transaction New Transaction need to be deleted.
     */
    void deleteTransaction(Transaction transaction);

    /**
     * Get all transactions.
     *
     * @return transaction list.
     */
    List<Transaction> fetchAll();

    /**
     * Get transactions by specifying one category.
     *
     * @param category The specific category.
     * @return transaction list.
     */
    List<Transaction> fetchTransactionByCategory(Category category);

    /**
     * Get transaction between specified date.
     *
     * @param start The start date.
     * @param end The end date.
     * @return transaction list.
     */
    List<Transaction> fetchTransactionByDate(Date start, Date end);

    /**
     * Get transaction between specified date.
     *
     * @param transaction The transaction need to covert.
     * @param currency The target currency.
     * @return the amount in another currency.
     */
    double convert(Transaction transaction, Currency currency);

    /**
     * Get all categories.
     *
     * @return category list.
     */
    void addCategory(Category category);

    /**
     * Delete one category.
     *
     * @param category The category that need to be deleted.
     */
    void deleteCategory(Category category);

    /**
     * Update one category.
     *
     * @param category The category that need to be edited.
     */
    void updateCategory(Category category);

    /**
     * Get all categories.
     *
     * @return category list.
     */
    List<Category> getAllCategory();
}
