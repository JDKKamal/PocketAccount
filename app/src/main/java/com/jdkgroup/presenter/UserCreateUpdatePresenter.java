package com.jdkgroup.presenter;

import android.app.Activity;

import com.jdkgroup.baseclass.BasePresenter;
import com.jdkgroup.interacter.InterActorCallback;
import com.jdkgroup.pocketaccount.model.ModelUser;
import com.jdkgroup.view.UserCreateUpdateView;

public class UserCreateUpdatePresenter extends BasePresenter<UserCreateUpdateView> {

    public void getDBUserCreate(final Activity activity, final ModelUser modelUser) {
        getAppInteractor().callDBUserCreate(activity, modelUser, new InterActorCallback<String>() {
            @Override
            public void onStart() {
                getView().showProgressDialog(true);
            }

            @Override
            public void onResponse(String response) {
                getView().setUserCreate(response);
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