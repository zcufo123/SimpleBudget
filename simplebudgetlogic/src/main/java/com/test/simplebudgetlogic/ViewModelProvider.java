package com.test.simplebudgetlogic;

import android.content.Context;

import com.test.simplebudgetapi.SimpleBudgetAPI;
import com.test.simplebudgetlogic.impl.CategoryListViewModelImpl;
import com.test.simplebudgetlogic.impl.DateListViewModelImpl;
import com.test.simplebudgetlogic.impl.OveralListViewModelImpl;

public class ViewModelProvider {

    public static OveralListViewModel provideOverallListViewModel(Context context) {
        return new OveralListViewModelImpl(SimpleBudgetAPI.Builder.build(context));
    }

    public static DateListViewModel provideDateListViewModel(Context context) {
        return new DateListViewModelImpl((SimpleBudgetAPI.Builder.build(context)));
    }

    public static CategoryListViewModel provideCategoryListViewModel(Context context) {
        return new CategoryListViewModelImpl(SimpleBudgetAPI.Builder.build(context));
    }
}
