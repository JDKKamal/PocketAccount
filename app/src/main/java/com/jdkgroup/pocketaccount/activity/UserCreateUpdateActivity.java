package com.jdkgroup.pocketaccount.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jdkgroup.baseclass.SimpleMVPActivity;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.pocketaccount.R;
import com.jdkgroup.pocketaccount.model.ModelUser;
import com.jdkgroup.presenter.UserCreateUpdatePresenter;
import com.jdkgroup.utils.AppUtils;
import com.jdkgroup.view.UserCreateUpdateView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserCreateUpdateActivity extends SimpleMVPActivity<UserCreateUpdatePresenter, UserCreateUpdateView> implements UserCreateUpdateView, View.OnClickListener {

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.appEdtName)
    AppCompatEditText appEdtName;
    @BindView(R.id.appEdtAmount)
    AppCompatEditText appEdtAmount;
    @BindView(R.id.appBtnAdd)
    AppCompatButton appBtnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create_update);

        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        toolBarSetFont(toolBar);

        setTitle(R.string.app_name);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolBar.setNavigationOnClickListener(arrow -> finish());
        appBtnAdd.setOnClickListener(this);
    }

    @NonNull
    @Override
    public UserCreateUpdatePresenter createPresenter() {
        return new UserCreateUpdatePresenter();
    }

    @NonNull
    @Override
    public UserCreateUpdateView attachView() {
        return this;
    }

    @Override
    public void setUserCreate(String response) {

    }

    @Override
    public void onFailure(String message) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appBtnAdd:
                String name, amount;
                name = appEdtName.getText().toString().toLowerCase();
                amount = appEdtAmount.getText().toString();

                ModelUser modelUser = new ModelUser(AppUtils.getUUIDRandom().toString(), name, Double.valueOf(amount), Double.valueOf(0), AppConstant.STATUS_DB_SHOW, AppUtils.getCurrentDate().toString());
                getPresenter().getDBUserCreate(this, modelUser);

                appEdtName.setText(null);
                appEdtAmount.setText(null);
                break;
        }
    }
}
