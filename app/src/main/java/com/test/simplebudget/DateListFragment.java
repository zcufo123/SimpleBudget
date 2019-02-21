package com.test.simplebudget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.test.simplebudgetapi.model.Transaction;
import com.test.simplebudgetlogic.DateListViewModel;
import com.test.simplebudgetlogic.ResultObservable;
import com.test.simplebudgetlogic.ResultObserver;
import com.test.simplebudgetlogic.ViewModelProvider;

import java.util.List;

public class DateListFragment extends Fragment {
    private ListView listView;
    private DateListViewModel dateListViewModel;

    public DateListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.overall_list_fragment, container, false);
        listView = rootView.findViewById(R.id.overall_list);
        String[] array = {"DateList"};
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, array);
        listView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onResume() {
        //@TODO: Need to create list adapter here
        super.onResume();
        dateListViewModel = ViewModelProvider.provideDateListViewModel(getContext());
        ResultObservable<List<Transaction>> resultObservable = dateListViewModel.fetchTransactionByMonth(2018, 10);
        resultObservable.setResultObserver(new ResultObserver<List<Transaction>>() {
            @Override
            public void notifyUpdate(List<Transaction> transactions) {
                //@TODO: Update List View here

            }
        });
    }
}
