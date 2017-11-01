package com.jdkgroup.presenter;

import android.app.Activity;

import com.jdkgroup.baseclass.BasePresenter;
import com.jdkgroup.interacter.InterActorCallback;
import com.jdkgroup.pocketaccount.model.ModelTransaction;
import com.jdkgroup.pocketaccount.model.ModelUser;
import com.jdkgroup.view.TransactionAddUpdateView;

import java.util.List;

public class TransactionAddUpdatePresenter extends BasePresenter<TransactionAddUpdateView> {

    public void getDBListUserUuid(final Activity activity, final String uuiduser) {
        getAppInteractor().callDBListUserUuid(activity, uuiduser, new InterActorCallback<List<ModelUser>>() {
            @Override
            public void onStart() {
                getView().showProgressDialog(true);
            }

            @Override
            public void onResponse(List<ModelUser> response) {
                getView().getUserListUserUuid(response);
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

    public void getDBTransactionAdd(final Activity activity, final ModelTransaction modelTransaction) {
        getAppInteractor().callDBTransactionAdd(activity, modelTransaction, new InterActorCallback<String>() {
            @Override
            public void onStart() {
                getView().showProgressDialog(true);
            }

            @Override
            public void onResponse(String response) {
                getView().setTransactionAdd(response);
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

    public void getDBUserCreditAmountUpdate(final Activity activity, final String uuiduser, final Double creditamount) {
        getAppInteractor().getDBUserCreditAmountUpdate(activity, uuiduser, creditamount, new InterActorCallback<String>() {
            @Override
            public void onStart() {
                getView().showProgressDialog(true);
            }

            @Override
            public void onResponse(String response) {
                getView().userCreditAmountUpdate(response);
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

    public void getDBUserDebitAmountUpdate(final Activity activity, final String uuiduser, final Double debitamount) {
        getAppInteractor().getDBUserDebitAmountUpdate(activity, uuiduser, debitamount, new InterActorCallback<String>() {
            @Override
            public void onStart() {
                getView().showProgressDialog(true);
            }

            @Override
            public void onResponse(String response) {
                getView().userDebitAmountUpdate(response);
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