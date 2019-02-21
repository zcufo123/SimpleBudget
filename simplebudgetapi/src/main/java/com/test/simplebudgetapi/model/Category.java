package com.test.simplebudgetapi.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;

@Entity(tableName = "Category")
public class Category {

    @PrimaryKey(autoGenerate = true)
    @Getter @Setter
    @NonNull
    private long id;

    @ColumnInfo(name = "title")
    @Getter @Setter
    private String title;

    @ColumnInfo(name = "color")
    @Getter @Setter
    private int color;

    @ColumnInfo(name = "maxAmount")
    @Getter @Setter
    private double maxAmount;

    public Category(String title, int color, double maxAmount) {
        this.title = title;
        this.color = color;
        this.maxAmount = maxAmount;
    }
}
