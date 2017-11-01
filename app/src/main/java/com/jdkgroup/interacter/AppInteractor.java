package com.jdkgroup.interacter;

import android.app.Activity;

import com.jdkgroup.constant.AppConstant;
import com.jdkgroup.pocketaccount.database.DBQuery;
import com.jdkgroup.pocketaccount.model.ModelTransaction;
import com.jdkgroup.pocketaccount.model.ModelUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AppInteractor {

    //TODO Calendar
    private Date date;
    private SimpleDateFormat sdf;

    private Calendar calendar = Calendar.getInstance();
    private int mYear = calendar.get(Calendar.YEAR);
    private int mMonth = calendar.get(Calendar.MONTH);
    private int mDay = calendar.get(Calendar.DAY_OF_MONTH);

    public AppInteractor() {
    }

    private void sendResponse(InterActorCallback callback, Response response) {
        callback.onResponse(response);

    }

    private void displayRequestParams(HashMap<String, String> hashMap) {
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
        }
    }

    //START REALM DATABASE INSERT
    public void callDBUserCreate(final Activity activity, final ModelUser modelUser, final InterActorCallback callback) {
        callback.onStart();

        Observable<String> observable = Observable.just(getDBUserCreate(activity, modelUser));
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onError(Throwable e) {
                callback.onError(e.toString());
            }

            @Override
            public void onComplete() {
                callback.onFinish();
            }

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String response) {
                callback.onResponse(response);
            }
        };
        observable.subscribe(observer);
    }

    public void callDBTransactionAdd(final Activity activity, final ModelTransaction modelTransaction, final InterActorCallback callback) {
        callback.onStart();

        Observable<String> observable = Observable.just(getDBTransactionAdd(activity, modelTransaction));
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onError(Throwable e) {
                callback.onError(e.toString());
            }

            @Override
            public void onComplete() {
                callback.onFinish();
            }

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(String response) {
                callback.onResponse(response);
            }
        };
        observable.subscribe(observer);
    }

    private String getDBUserCreate(final Activity activity, ModelUser modelUser) {
        DBQuery.with(activity).realmInsert(modelUser);
        return "";
    }

    private String getDBTransactionAdd(final Activity activity, ModelTransaction modelTransaction) {
        DBQuery.with(activity).realmInsert(modelTransaction);
        return "";
    }
    //FINISH REALM DATABASE INSERT

    public void getDBUserCreditAmountUpdate(final Activity activity, final String uuiduser, final Double creditamount, final InterActorCallback callback) {
        System.out.println("Tag" + uuiduser);
        DBQuery.with(activity).realmUpdateUserCreditAmount(uuiduser, creditamount);
        callback.onResponse("");
    }

    public void getDBUserDebitAmountUpdate(final Activity activity, final String uuiduser, final Double debitamount, final InterActorCallback callback) {
        DBQuery.with(activity).realmUpdateUserDebitAmount(uuiduser, debitamount);
        callback.onResponse("");
    }

    public void callDBListUserStatus(final Activity activity, final InterActorCallback callback) {
        ModelUser modelUser = new ModelUser();
        List<ModelUser> alUser = DBQuery.with(activity).realmListWhereUserStatus(modelUser.getClass(), "status", AppConstant.STATUS_DB_SHOW);
        callback.onResponse(alUser);
    }

    public void callDBListUserUuid(final Activity activity, final String uuiduser, final InterActorCallback callback) {
        ModelUser modelUser = new ModelUser();
        List<ModelUser> alUser = DBQuery.with(activity).realmListWhereUserUuid(modelUser.getClass(), "uuiduser", uuiduser);
        callback.onResponse(alUser);
    }

    public void callDBCreditTransactionListVisible(final Activity activity, final String uuiduser, final InterActorCallback callback) {
        ModelTransaction modelTransaction = new ModelTransaction();
        List<ModelTransaction> alUser = DBQuery.with(activity).realmListWhereTransactionUuidCreditstatus(modelTransaction.getClass(), "uuidUser", uuiduser);
        callback.onResponse(alUser);
    }

    public void callDBDebitTransactionListVisible(final Activity activity, String uuiduser, final InterActorCallback callback) {
        ModelTransaction modelTransaction = new ModelTransaction();
        List<ModelTransaction> alUser = DBQuery.with(activity).realmListWhereTransactionUuidDebitstatus(modelTransaction.getClass(), "uuidUser", uuiduser);
        callback.onResponse(alUser);
    }
}

