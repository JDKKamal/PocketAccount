package com.jdkgroup.presenter;

import android.app.Activity;

import com.jdkgroup.baseclass.BasePresenter;
import com.jdkgroup.interacter.InterActorCallback;
import com.jdkgroup.pocketaccount.model.ModelUser;
import com.jdkgroup.view.TabTransactionView;

import java.util.List;

public class TabTransactionPresenter extends BasePresenter<TabTransactionView> {

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
}