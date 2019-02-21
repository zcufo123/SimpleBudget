package com.test.simplebudgetapi.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "Transactions")
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    @Getter @Setter
    @NonNull
    private long id;

    @ColumnInfo(name = "timeStamp")
    @Getter @Setter
    private Date timeStamp;

    @ColumnInfo(name = "value")
    @Getter @Setter
    private double value;

    @ColumnInfo(name = "currency")
    @Getter @Setter
    private Currency currency;

    @ColumnInfo(name = "categoryID")
    @Getter @Setter
    private long categoryID;

    public Transaction(Date timeStamp, double value, Currency currency) {
        this.timeStamp = timeStamp;
        this.value = value;
        this.currency = currency;
    }
}
