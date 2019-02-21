package com.test.simplebudgetapi.model.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.test.simplebudgetapi.model.Category;

import java.util.List;

@Dao
public interface CategoryDao {
    @Insert
    long insert(Category category);

    @Delete
    void delete(Category... categories);

    @Update
    void update(Category... categories);

    @Query("SELECT * FROM Category")
    List<Category> getAll();
}
