package com.jdkgroup.pocketaccount.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by lakhani on 10/31/2017.
 */

public class ModelUser extends RealmObject {
    @PrimaryKey
    private String uuiduser;
    private String name;
    private double creditamount, debitamount;
    private int status;
    private String date;

    public ModelUser() {
    }

    public ModelUser(String uuiduser, String name, double creditamount, double debitamount, int status, String date) {
        this.uuiduser = uuiduser;
        this.name = name;
        this.creditamount = creditamount;
        this.debitamount = debitamount;
        this.status = status;
        this.date = date;
    }

    public String getUuiduser() {
        return uuiduser;
    }

    public void setUuiduser(String uuiduser) {
        this.uuiduser = uuiduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCreditamount() {
        return creditamount;
    }

    public void setCreditamount(double creditamount) {
        this.creditamount = creditamount;
    }

    public double getDebitamount() {
        return debitamount;
    }

    public void setDebitamount(double debitamount) {
        this.debitamount = debitamount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
