package com.jdkgroup.view;

import com.jdkgroup.baseclass.BaseView;
import com.jdkgroup.pocketaccount.model.ModelUser;

import java.util.List;

public interface TransactionAddUpdateView extends BaseView {
    void getUserListUserUuid(List<ModelUser> response);
    void setTransactionAdd(String response);
    void userCreditAmountUpdate(String response);
    void userDebitAmountUpdate(String response);
}

