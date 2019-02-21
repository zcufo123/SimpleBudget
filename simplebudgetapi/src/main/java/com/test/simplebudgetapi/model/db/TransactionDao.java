package com.test.simplebudgetapi.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.test.simplebudgetapi.model.Transaction;

import java.util.Date;
import java.util.List;

@Dao
public interface TransactionDao {
    @Insert
    void insert(Transaction... transaction);

    @Delete
    void delete(Transaction... transaction);

    @Update
    void update(Transaction... transaction);

    @Query("SELECT * FROM Transactions")
    List<Transaction> getAll();

    @Query("SELECT * FROM Transactions WHERE timeStamp BETWEEN :from AND :to")
    List<Transaction> findTransactionBetweenDates(Date from, Date to);

    @Query("SELECT * FROM Transactions WHERE categoryID = :categoryID")
    List<Transaction> findTransactionByCategory(long categoryID);
}
