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
import com.jdkgroup.pocketaccount.model.ModelTransaction;
import com.jdkgroup.presenter.CreditTransactionListPresenter;
import com.jdkgroup.view.CreditTransactionListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class CreditTransactionListFragment extends MVPFragment<CreditTransactionListPresenter, CreditTransactionListView> implements CreditTransactionListView, CreditTransactionListAdapter.ItemListener {

    Unbinder unbinder;
    @BindView(R.id.llDataPresent)
    LinearLayout llDataPresent;
    @BindView(R.id.llDataNo)
    LinearLayout llDataNo;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private String uuiduser;
    private CreditTransactionListAdapter creditTransactionListAdapter;

    @SuppressLint("ValidFragment")
    public CreditTransactionListFragment(String uuiduser) {
        this.uuiduser = uuiduser;
    }

    @NonNull
    @Override
    public CreditTransactionListPresenter createPresenter() {
        return new CreditTransactionListPresenter();
    }

    @NonNull
    @Override
    public CreditTransactionListView attachView() {
        return this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credit_transaction_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        getPresenter().getDBCreditTransactionListVisible(getActivity(), uuiduser);
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
    public void getCreditTransactionListVisible(List<ModelTransaction> response) {
        if (!response.isEmpty()) {
            isData(llDataPresent, llDataNo, 1);
            creditTransactionListAdapter = new CreditTransactionListAdapter(getActivity(), response);
            creditTransactionListAdapter.setOnListener(this);
            recyclerView.setAdapter(creditTransactionListAdapter);
        } else {
            isData(llDataPresent, llDataNo, 0);
        }
    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onClickCreditTransaction(ModelTransaction modelTransaction) {

    }
}