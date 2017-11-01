package com.jdkgroup.pocketaccount.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.jdkgroup.baseclass.SimpleMVPActivity;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.pocketaccount.R;
import com.jdkgroup.pocketaccount.fragment.CreditTransactionListFragment;
import com.jdkgroup.pocketaccount.fragment.DebitTransactionListFragment;
import com.jdkgroup.pocketaccount.model.ModelUser;
import com.jdkgroup.pocketaccount.model.passdata.PassDataUser;
import com.jdkgroup.presenter.TabTransactionPresenter;
import com.jdkgroup.view.TabTransactionView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabTransaction extends SimpleMVPActivity<TabTransactionPresenter, TabTransactionView> implements TabTransactionView {

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.appTvCreditAmount)
    AppCompatTextView appTvCreditAmount;
    @BindView(R.id.appTvDebitAmount)
    AppCompatTextView appTvDebitAmount;
    @BindView(R.id.appTvTransactionAmount)
    AppCompatTextView appTvTransactionAmount;

    private List<PassDataUser> parcelableUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_transaction);

        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        toolBarSetFont(toolBar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        parcelableUser = getParcelable(AppConstant.BUNDLE_PARCELABLE);
        if (parcelableUser != null) {
            setTitle(parcelableUser.get(0).getName());
            getPresenter().getDBListUserUuid(this, parcelableUser.get(0).getUuidhome());
        }

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        toolBar.setNavigationOnClickListener(arrow -> finish());
    }

    @NonNull
    @Override
    public TabTransactionPresenter createPresenter() {
        return new TabTransactionPresenter();
    }

    @NonNull
    @Override
    public TabTransactionView attachView() {
        return this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        menu.setGroupVisible(R.id.action_add, true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                sentParcelsLaunchClear(TransactionAddUpdateActivity.class, AppConstant.BUNDLE_PARCELABLE, parcelableUser, 1);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CreditTransactionListFragment(parcelableUser.get(0).getUuidhome()), getString(R.string.tab_credit));
        adapter.addFragment(new DebitTransactionListFragment(parcelableUser.get(0).getUuidhome()), getString(R.string.tab_debit));
        viewPager.setAdapter(adapter);
    }

    @Override
    public void getUserListUserUuid(List<ModelUser> response) {
        appTvCreditAmount.setText(response.get(0).getCreditamount() + "");
        appTvDebitAmount.setText(response.get(0).getDebitamount() + "");
        appTvTransactionAmount.setText(Double.valueOf(response.get(0).getCreditamount()) - Double.valueOf(response.get(0).getDebitamount()) + "");
    }

    @Override
    public void onFailure(String message) {

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
