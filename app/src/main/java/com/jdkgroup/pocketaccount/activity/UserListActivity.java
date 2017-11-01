package com.jdkgroup.pocketaccount.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.jdkgroup.baseclass.BaseActivity;
import com.jdkgroup.baseclass.SimpleMVPActivity;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.pocketaccount.R;
import com.jdkgroup.pocketaccount.adapter.UserAdapter;
import com.jdkgroup.pocketaccount.database.DBQuery;
import com.jdkgroup.pocketaccount.model.ModelUser;
import com.jdkgroup.pocketaccount.model.passdata.PassDataUser;
import com.jdkgroup.presenter.UserListPresenter;
import com.jdkgroup.utils.AppUtils;
import com.jdkgroup.view.UserListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserListActivity extends SimpleMVPActivity<UserListPresenter, UserListView> implements UserListView, UserAdapter.ItemListener {

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.llDataPresent)
    LinearLayout llDataPresent;
    @BindView(R.id.llDataNo)
    LinearLayout llDataNo;
    @BindView(R.id.rvHome)
    RecyclerView rvHome;

    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        toolBarSetFont(toolBar);

        setTitle(R.string.app_name);

        getPresenter().getDBListUserStatus(this);
    }

    @NonNull
    @Override
    public UserListPresenter createPresenter() {
        return new UserListPresenter();
    }

    @NonNull
    @Override
    public UserListView attachView() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        menu.setGroupVisible(R.id.group_pocket_account, true);

        menu.setGroupVisible(R.id.action_add, true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                AppUtils.startActivity(this, UserCreateUpdateActivity.class);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickHome(ModelUser modelUser) {
        List<PassDataUser> alDesktop = new ArrayList<>();
        alDesktop.add(new PassDataUser(modelUser.getUuiduser(), modelUser.getName()));
        sentParcelsLaunchClear(TabTransaction.class, AppConstant.BUNDLE_PARCELABLE, alDesktop, 1);
    }

    @Override
    public void getUserListStatus(List<ModelUser> response) {
        if (!response.isEmpty()) {
            isData(llDataPresent, llDataNo, 1);
            rvHome.setLayoutManager(new LinearLayoutManager(this));
            rvHome.setHasFixedSize(true);
            userAdapter = new UserAdapter(this, response);
            userAdapter.setOnListener(this);
            rvHome.setAdapter(userAdapter);
        } else {
            isData(llDataPresent, llDataNo, 0);
        }
    }

    @Override
    public void onFailure(String message) {

    }
}
