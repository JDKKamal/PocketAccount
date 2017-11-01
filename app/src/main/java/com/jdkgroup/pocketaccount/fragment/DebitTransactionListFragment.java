package com.jdkgroup.pocketaccount.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jdkgroup.baseclass.MVPFragment;
import com.jdkgroup.pocketaccount.R;
import com.jdkgroup.pocketaccount.adapter.CreditTransactionListAdapter;
import com.jdkgroup.pocketaccount.adapter.DebitTransactionListAdapter;
import com.jdkgroup.pocketaccount.model.ModelTransaction;
import com.jdkgroup.presenter.CreditTransactionListPresenter;
import com.jdkgroup.presenter.DebitTransactionListPresenter;
import com.jdkgroup.view.CreditTransactionListView;
import com.jdkgroup.view.DebitTransactionListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class DebitTransactionListFragment extends MVPFragment<DebitTransactionListPresenter, DebitTransactionListView> implements DebitTransactionListView, DebitTransactionListAdapter.ItemListener {

    Unbinder unbinder;
    @BindView(R.id.llDataPresent)
    LinearLayout llDataPresent;
    @BindView(R.id.llDataNo)
    LinearLayout llDataNo;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private final String uuidhome;

    private DebitTransactionListAdapter debitTransactionListAdapter;

    public DebitTransactionListFragment(String uuidhome) {
        this.uuidhome = uuidhome;
    }

    @NonNull
    @Override
    public DebitTransactionListPresenter createPresenter() {
        return new DebitTransactionListPresenter();
    }

    @NonNull
    @Override
    public DebitTransactionListView attachView() {
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debit_transaction_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        getPresenter().getDBDebitTransactionListVisible(getActivity(), uuidhome);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onClickCreditTransaction(ModelTransaction modelTransaction) {

    }

    @Override
    public void getDebitTransactionListVisible(List<ModelTransaction> response) {
        if (!response.isEmpty()) {
            isData(llDataPresent, llDataNo, 1);
            debitTransactionListAdapter = new DebitTransactionListAdapter(getActivity(), response);
            debitTransactionListAdapter.setOnListener(this);
            recyclerView.setAdapter(debitTransactionListAdapter);
        } else {
            isData(llDataPresent, llDataNo, 0);
        }
    }
}