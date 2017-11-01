package com.jdkgroup.pocketaccount.model.passdata;

import org.parceler.Parcel;

/**
 * Created by lakhani on 11/1/2017.
 */

@Parcel
public class PassDataUser {
    public String uuidhome, name;

    public PassDataUser() {
    }

    public PassDataUser(String uuidhome, String name) {
        this.uuidhome = uuidhome;
        this.name = name;
    }

    public String getUuidhome() {
        return uuidhome;
    }

    public void setUuidhome(String uuidhome) {
        this.uuidhome = uuidhome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
