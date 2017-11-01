package com.jdkgroup.view;

import com.jdkgroup.baseclass.BaseView;
import com.jdkgroup.pocketaccount.model.ModelTransaction;
import com.jdkgroup.pocketaccount.model.ModelUser;

import java.util.List;

public interface CreditTransactionListView extends BaseView {
    void getCreditTransactionListVisible(List<ModelTransaction> response);
}

