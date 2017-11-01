package com.jdkgroup.presenter;

import android.app.Activity;

import com.jdkgroup.baseclass.BasePresenter;
import com.jdkgroup.interacter.InterActorCallback;
import com.jdkgroup.pocketaccount.model.ModelUser;
import com.jdkgroup.view.UserListView;

import java.util.List;

public class UserListPresenter extends BasePresenter<UserListView> {

    public void getDBListUserStatus(final Activity activity) {
        getAppInteractor().callDBListUserStatus(activity, new InterActorCallback<List<ModelUser>>() {
            @Override
            public void onStart() {
                getView().showProgressDialog(true);
            }

            @Override
            public void onResponse(List<ModelUser> response) {
                getView().getUserListStatus(response);
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