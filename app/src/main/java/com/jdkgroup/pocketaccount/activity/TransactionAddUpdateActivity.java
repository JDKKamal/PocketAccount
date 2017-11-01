package com.jdkgroup.pocketaccount.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jdkgroup.baseclass.SimpleMVPActivity;
import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.interacter.AppInteractor;
import com.jdkgroup.pocketaccount.R;
import com.jdkgroup.pocketaccount.model.ModelTransaction;
import com.jdkgroup.pocketaccount.model.ModelUser;
import com.jdkgroup.pocketaccount.model.passdata.PassDataUser;
import com.jdkgroup.presenter.TransactionAddUpdatePresenter;
import com.jdkgroup.utils.AppUtils;
import com.jdkgroup.view.TransactionAddUpdateView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAddUpdateActivity extends SimpleMVPActivity<TransactionAddUpdatePresenter, TransactionAddUpdateView> implements TransactionAddUpdateView, View.OnClickListener {

    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.appEdtTitle)
    AppCompatEditText appEdtTitle;
    @BindView(R.id.appEdtAmount)
    AppCompatEditText appEdtAmount;
    @BindView(R.id.appEdtDescription)
    AppCompatEditText appEdtDescription;
    @BindView(R.id.rgTransaction)
    RadioGroup rgTransaction;
    @BindView(R.id.rbCredit)
    RadioButton rbCredit;
    @BindView(R.id.rbDebit)
    RadioButton rbDebit;
    @BindView(R.id.appBtnSubmit)
    AppCompatButton appBtnSubmit;

    private List<PassDataUser> parcelableUser;
    private double creditamount, debitamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_add_update);

        ButterKnife.bind(this);
        setSupportActionBar(toolBar);
        toolBarSetFont(toolBar);

        setTitle(R.string.title_transaction);
        parcelableUser = getParcelable(AppConstant.BUNDLE_PARCELABLE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolBar.setNavigationOnClickListener(arrow -> finish());
        appBtnSubmit.setOnClickListener(this);
    }

    @NonNull
    @Override
    public TransactionAddUpdatePresenter createPresenter() {
        return new TransactionAddUpdatePresenter();
    }

    @NonNull
    @Override
    public TransactionAddUpdateView attachView() {
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appBtnSubmit:
                String title, amount, description;

                title = appEdtTitle.getText().toString().toLowerCase();
                amount = appEdtAmount.getText().toString();
                description = appEdtDescription.getText().toString();

                int selectedId = rgTransaction.getCheckedRadioButtonId();
                RadioButton rbCreditDebit = findViewById(selectedId);

                String strIsCreditDebit = rbCreditDebit.getText().toString().substring(0, 1);
                int credit, debit;
                if (strIsCreditDebit.equals(getString(R.string.is_c))) {
                    credit = 1;
                    debit = 0;
                } else {
                    credit = 0;
                    debit = 1;
                }

                getPresenter().getDBListUserUuid(this, parcelableUser.get(0).getUuidhome());
                if (credit == 1) {
                    getPresenter().getDBUserCreditAmountUpdate(this, parcelableUser.get(0).getUuidhome(), Double.valueOf(amount) + creditamount);
                } else {
                    getPresenter().getDBUserDebitAmountUpdate(this, parcelableUser.get(0).getUuidhome(), Double.valueOf(amount) + debitamount);
                }

                ModelTransaction modelTransaction = new ModelTransaction(AppUtils.getUUIDRandom().toString(), parcelableUser.get(0).getUuidhome(), title, Double.valueOf(amount), description, credit, debit, AppUtils.getCurrentDate().toString());
                getPresenter().getDBTransactionAdd(this, modelTransaction);

                appEdtTitle.setText(null);
                appEdtAmount.setText(null);
                appEdtDescription.setText(null);

                break;
        }
    }

    @Override
    public void getUserListUserUuid(List<ModelUser> response) {
        creditamount = response.get(0).getCreditamount();
        debitamount = response.get(0).getDebitamount();
    }

    @Override
    public void setTransactionAdd(String response) {

    }

    @Override
    public void userCreditAmountUpdate(String response) {

    }

    @Override
    public void userDebitAmountUpdate(String response) {

    }

    @Override
    public void onFailure(String message) {

    }
}
