package com.jdkgroup.pocketaccount.model;

import io.realm.RealmObject;

/**
 * Created by lakhani on 10/31/2017.
 */

public class ModelTransaction extends RealmObject {
    private String uuidtransaction, uuidUser, title;
    private double creditdebitamount;
    private String descripton;
    private int creditstatus, debitstatus;
    private String date;

    public ModelTransaction() {
    }

    public ModelTransaction(String uuidtransaction, String uuidUser, String title, double creditdebitamount, String descripton, int creditstatus, int debitstatus, String date) {
        this.uuidtransaction = uuidtransaction;
        this.uuidUser = uuidUser;
        this.title = title;
        this.creditdebitamount = creditdebitamount;
        this.descripton = descripton;
        this.creditstatus = creditstatus;
        this.debitstatus = debitstatus;
        this.date = date;
    }

    public String getUuidtransaction() {
        return uuidtransaction;
    }

    public void setUuidtransaction(String uuidtransaction) {
        this.uuidtransaction = uuidtransaction;
    }

    public String getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(String uuidUser) {
        this.uuidUser = uuidUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCreditdebitamount() {
        return creditdebitamount;
    }

    public void setCreditdebitamount(double creditdebitamount) {
        this.creditdebitamount = creditdebitamount;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public int getCreditstatus() {
        return creditstatus;
    }

    public void setCreditstatus(int creditstatus) {
        this.creditstatus = creditstatus;
    }

    public int getDebitstatus() {
        return debitstatus;
    }

    public void setDebitstatus(int debitstatus) {
        this.debitstatus = debitstatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
