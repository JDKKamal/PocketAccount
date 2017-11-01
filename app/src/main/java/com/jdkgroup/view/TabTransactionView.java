package com.jdkgroup.view;

import com.jdkgroup.baseclass.BaseView;
import com.jdkgroup.pocketaccount.model.ModelUser;

import java.util.List;

public interface TabTransactionView extends BaseView {
    void getUserListUserUuid(List<ModelUser> response);
}

