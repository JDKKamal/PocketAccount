package com.jdkgroup.view;

import com.jdkgroup.baseclass.BaseView;
import com.jdkgroup.pocketaccount.model.ModelTransaction;

import java.util.List;

public interface DebitTransactionListView extends BaseView {
    void getDebitTransactionListVisible(List<ModelTransaction> response);
}

