package com.test.simplebudgetapi.model.db;

import android.arch.persistence.room.TypeConverter;

import com.test.simplebudgetapi.model.Currency;

import java.util.Date;

public class TransactionConverters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Currency fromTimestamp(String value) {
        return value == null ? null : Currency.valueOf(value);
    }

    @TypeConverter
    public static String dateToTimestamp(Currency currency) {
        return currency == null ? null : currency.toString();
    }
}
