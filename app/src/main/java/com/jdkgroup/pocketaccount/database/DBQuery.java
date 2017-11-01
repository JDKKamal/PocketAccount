package com.jdkgroup.pocketaccount.database;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.jdkgroup.pocketaccount.model.ModelUser;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by lakhani on 9/26/2017.
 */

public class DBQuery {

    private static DBQuery instance;
    private final Realm realm;

    public DBQuery(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static DBQuery with(Fragment fragment) {
        if (instance == null) {
            instance = new DBQuery(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static DBQuery with(Activity activity) {
        if (instance == null) {
            instance = new DBQuery(activity.getApplication());
        }
        return instance;
    }

    public static DBQuery with(Application application) {
        if (instance == null) {
            instance = new DBQuery(application);
        }
        return instance;
    }

    public static DBQuery getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    void closeRealm() {
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }

    public long realmVersion() {
        return realm.getVersion();
    }

    public void realmInsert(RealmModel object) {
        realm.beginTransaction();
        realm.insert(object);
        realm.commitTransaction();
    }

    public void realmInsertList(List<? extends RealmModel> object) {
        realm.beginTransaction();
        realm.insert(object);
        realm.commitTransaction();
    }

    public RealmResults realmList(Class classObject) {
        return realm.where(classObject).findAll();
    }

    public RealmResults realmListWhereUserStatus(Class classObject, String fieldName, int search) {
        return realm.where(classObject).equalTo(fieldName, search).findAll();
    }

    public RealmResults realmListWhereUserUuid(Class classObject, String fieldName, String search) {
        return realm.where(classObject).equalTo(fieldName, search).findAll();
    }

    public RealmResults realmListWhereTransactionUuidCreditstatus(Class classObject, String fieldName, String search) {
        return realm.where(classObject).equalTo(fieldName, search).equalTo("creditstatus", 1).findAll();
    }

    public RealmResults realmListWhereTransactionUuidDebitstatus(Class classObject, String fieldName, String search) {
        return realm.where(classObject).equalTo(fieldName, search).equalTo("debitstatus", 1).findAll();
    }

    public void realmUpdateUserCreditAmount(String uuiduser, Double creditamount) {
        realm.beginTransaction();
        ModelUser person = realm.where(ModelUser.class).equalTo("uuiduser", uuiduser).findFirst();
        person.setCreditamount(creditamount);
        realm.copyToRealmOrUpdate(person);
        realm.commitTransaction();
    }

    public void realmUpdateUserDebitAmount(String uuiduser, Double debitamount) {
        realm.beginTransaction();
        ModelUser person = realm.where(ModelUser.class).equalTo("uuiduser", uuiduser).findFirst();
        person.setDebitamount(debitamount);
        realm.copyToRealmOrUpdate(person);
        realm.commitTransaction();
    }
}