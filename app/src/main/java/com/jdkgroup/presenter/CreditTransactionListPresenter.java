package com.jdkgroup.presenter;

import android.app.Activity;

import com.jdkgroup.baseclass.BasePresenter;
import com.jdkgroup.interacter.InterActorCallback;
import com.jdkgroup.pocketaccount.model.ModelTransaction;
import com.jdkgroup.view.CreditTransactionListView;

import java.util.List;

public class CreditTransactionListPresenter extends BasePresenter<CreditTransactionListView> {

    public void getDBCreditTransactionListVisible(final Activity activity, final String uuiduser) {
        getAppInteractor().callDBCreditTransactionListVisible(activity, uuiduser, new InterActorCallback<List<ModelTransaction>>() {
            @Override
            public void onStart() {
                getView().showProgressDialog(true);
            }

            @Override
            public void onResponse(List<ModelTransaction> response) {
                getView().getCreditTransactionListVisible(response);
            }

            @Override
            public void onFinish() {
                getView().showProgressDialog(false);
            }

            @Override
            public void onError(String message) {
                getView().onFailure(message);
            }
        });
    }
}