package com.test.simplebudgetlogic;

import android.support.test.runner.AndroidJUnit4;

import com.test.simplebudgetapi.SimpleBudgetAPI;
import com.test.simplebudgetapi.model.Currency;
import com.test.simplebudgetapi.model.Transaction;
import com.test.simplebudgetlogic.impl.OveralListViewModelImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class OverallListViewModelUniTest {

    private static final int TIMEOUT = 10000;

    private SimpleBudgetAPI mockAPI;
    private OveralListViewModel overalListViewModel;

    @Before
    public void init() {
        mockAPI = Mockito.mock(SimpleBudgetAPI.class);
        overalListViewModel = new OveralListViewModelImpl(mockAPI);
    }

    @After
    public void clear() {
        overalListViewModel = null;
        mockAPI = null;
    }

    @Test
    public void testSimple() throws Exception {
        overalListViewModel.fetchAllTransation();
        Mockito.verify(mockAPI, Mockito.timeout(TIMEOUT)).fetchAll();
    }

    @Test
    public void testReturnResult() throws Exception {
        ResultObserver<List<Transaction>> mockResultObserver = Mockito.mock(ResultObserver.class);
        List<Transaction> list = new ArrayList<>();
        list.add(new Transaction(new Date(), 100, Currency.NZD));
        list.add(new Transaction(new Date(), 200, Currency.NZD));
        Mockito.when(mockAPI.fetchAll()).thenReturn(list);
        ResultObservable<List<Transaction>> resultObservable = overalListViewModel.fetchAllTransation();
        resultObservable.setResultObserver(mockResultObserver);

        ArgumentCaptor<List> listArgumentCaptorCaptor = ArgumentCaptor.forClass(List.class);
        Mockito.verify(mockResultObserver, Mockito.timeout(TIMEOUT)).notifyUpdate(listArgumentCaptorCaptor.capture());
        List<Transaction> returnList = listArgumentCaptorCaptor.getValue();
        assertTrue(returnList.size() == 2);
    }
}
