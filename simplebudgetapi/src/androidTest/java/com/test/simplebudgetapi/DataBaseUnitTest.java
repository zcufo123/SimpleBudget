package com.test.simplebudgetapi;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.test.simplebudgetapi.model.Category;
import com.test.simplebudgetapi.model.Currency;
import com.test.simplebudgetapi.model.Transaction;
import com.test.simplebudgetapi.model.db.CategoryDao;
import com.test.simplebudgetapi.model.db.TransactionDao;
import com.test.simplebudgetapi.model.db.TransactionDataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DataBaseUnitTest {
    private TransactionDao mTransactionDao;
    private CategoryDao mCategoryDao;
    private TransactionDataBase mTransactionDataBase;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mTransactionDataBase = Room.inMemoryDatabaseBuilder(context, TransactionDataBase.class).build();
        mTransactionDao = mTransactionDataBase.getTransactionDao();
        mCategoryDao =mTransactionDataBase.getCategoryDao();
    }

    @After
    public void closeDb() throws IOException {
        mTransactionDataBase.close();
    }

    @Test
    public void simpleTestForTransaction() throws Exception {
        Date date = new Date();
        Transaction transaction = new Transaction(date, 20.01, Currency.NZD);
        transaction.setCategoryID(0);
        Transaction anotherTransaction = new Transaction(date, 50.45, Currency.NZD);
        anotherTransaction.setCategoryID(0);
        mTransactionDao.insert(transaction, anotherTransaction);
        List<Transaction> transactionList = mTransactionDao.findTransactionByCategory(0);
        assertTrue(transactionList.size() == 2);
        Transaction transactionFromDataBase = transactionList.get(0);
        assertEquals(transactionFromDataBase.getCurrency(), Currency.NZD);
        assertEquals(transactionFromDataBase.getCategoryID(), 0);
        assertEquals(transactionFromDataBase.getTimeStamp(), date);
        assertEquals(transactionFromDataBase.getValue(), 20.01, 0.0);
    }

    @Test
    public void simpleTestForCategory() throws Exception {
        Category category = new Category("test", 0xFF00FF, 100);
        long id = mCategoryDao.insert(category);
        List<Category> list = mCategoryDao.getAll();
        assertTrue(list.size() == 1);
        Category categoryFromDatabase = list.get(0);
        assertEquals(categoryFromDatabase.getTitle(), "test");
        assertTrue(categoryFromDatabase.getId() == id);
        assertTrue(categoryFromDatabase.getColor() == 0xFF00FF);
        assertTrue(categoryFromDatabase.getMaxAmount() == 100);
    }
}
