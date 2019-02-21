package com.test.simplebudgetapi.impl;

import android.content.Context;

import com.test.simplebudgetapi.SimpleBudgetAPI;
import com.test.simplebudgetapi.exchange.ExchangeService;
import com.test.simplebudgetapi.exchange.ExchangeServiceImpl;
import com.test.simplebudgetapi.model.Category;
import com.test.simplebudgetapi.model.Currency;
import com.test.simplebudgetapi.model.Transaction;
import com.test.simplebudgetapi.model.db.CategoryDao;
import com.test.simplebudgetapi.model.db.TransactionDao;
import com.test.simplebudgetapi.model.db.TransactionDataBase;

import java.util.Date;
import java.util.List;

public class SimpleBudgetAPIImpl implements SimpleBudgetAPI {

    private Context context;
    private TransactionDataBase mTransactionDataBase;

    public SimpleBudgetAPIImpl(Context context) {
        this.context = context;
        mTransactionDataBase = TransactionDataBase.getInstance(context);
    }

    @Override
    public void addTransaction(Transaction transaction, Category category) {
        transaction.setCategoryID(category.getId());
        TransactionDao transactionDao = mTransactionDataBase.getTransactionDao();
        transactionDao.insert(transaction);
        mTransactionDataBase.close();
    }

    @Override
    public void updateTransaction(Transaction transaction) {
        TransactionDao transactionDao = mTransactionDataBase.getTransactionDao();
        transactionDao.update(transaction);
        mTransactionDataBase.close();
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        TransactionDao transactionDao = mTransactionDataBase.getTransactionDao();
        transactionDao.delete(transaction);
        mTransactionDataBase.close();
    }

    @Override
    public List<Transaction> fetchAll() {
        TransactionDao transactionDao = mTransactionDataBase.getTransactionDao();
        List<Transaction> list = transactionDao.getAll();
        mTransactionDataBase.close();
        return list;
    }

    @Override
    public List<Transaction> fetchTransactionByCategory(Category category) {
        TransactionDao transactionDao = mTransactionDataBase.getTransactionDao();
        List<Transaction> list = transactionDao.findTransactionByCategory(category.getId());
        mTransactionDataBase.close();
        return list;
    }

    @Override
    public List<Transaction> fetchTransactionByDate(Date start, Date end) {
        TransactionDao transactionDao = mTransactionDataBase.getTransactionDao();
        List<Transaction> list = transactionDao.findTransactionBetweenDates(start, end);
        mTransactionDataBase.close();
        return list;
    }

    @Override
    public double convert(Transaction transaction, Currency currency) {
        ExchangeService exchangeService = new ExchangeServiceImpl();
        double value = transaction.getValue();
        if (transaction.getCurrency() != currency) {
            try {
                exchangeService.exchangeOnDate(transaction.getCurrency(), currency, transaction.getTimeStamp(), transaction.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    @Override
    public void addCategory(Category category) {
        CategoryDao categoryDao = mTransactionDataBase.getCategoryDao();
        long id = categoryDao.insert(category);
        category.setId(id);
        mTransactionDataBase.close();
    }

    @Override
    public void deleteCategory(Category category) {
        CategoryDao categoryDao = mTransactionDataBase.getCategoryDao();
        categoryDao.delete(category);
        mTransactionDataBase.close();
    }

    @Override
    public void updateCategory(Category category) {
        CategoryDao categoryDao = mTransactionDataBase.getCategoryDao();
        categoryDao.update(category);
        mTransactionDataBase.close();
    }

    @Override
    public List<Category> getAllCategory() {
        CategoryDao categoryDao = mTransactionDataBase.getCategoryDao();
        return categoryDao.getAll();
    }
}
