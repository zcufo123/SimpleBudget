package com.test.simplebudgetapi.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.test.simplebudgetapi.model.Category;
import com.test.simplebudgetapi.model.Transaction;

@Database(entities = {Transaction.class, Category.class}, version = 1)
@TypeConverters({TransactionConverters.class})
public abstract class TransactionDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "TransactionDataBase";
    private static TransactionDataBase instance = null;

    public abstract TransactionDao getTransactionDao();
    public abstract CategoryDao getCategoryDao();

    public static TransactionDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), TransactionDataBase.class, DATABASE_NAME).
                    allowMainThreadQueries().build();
        }
        return instance;
    }

    public static void destoryInstance() {
        if (instance.isOpen()) {
            instance.close();
        }
        instance = null;
    }
}
