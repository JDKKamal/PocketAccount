package com.jdkgroup.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jdkgroup.constant.RestConstant;

import static android.content.Context.MODE_PRIVATE;

public class PreferenceUtils {
    private final static String SP_NAME = "cleanup";
    private final static String IS_LOGIN = "islogin";
    private static PreferenceUtils preferenceUtils;
    private SharedPreferences sharedPreferences;
    private Context mContext;

    private PreferenceUtils(Context mContext) {
        this.mContext = mContext;
        sharedPreferences = mContext.getSharedPreferences(SP_NAME, MODE_PRIVATE);
    }

    public static PreferenceUtils getInstance(Context mContext) {
        return preferenceUtils = (preferenceUtils == null ? new PreferenceUtils(mContext) : preferenceUtils);
    }

    private static void removeInstance() {
        preferenceUtils = null;
    }

    public void clearAllPrefs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        removeInstance();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }

    public void setIsLogin(boolean islogin) {
        sharedPreferences.edit().putBoolean(IS_LOGIN, islogin).apply();
    }

    public String getDeviceToken() {
        return sharedPreferences.getString(RestConstant.DEVICE_TOKEN, "");
    }

    public void setDeviceToken(String device_token) {
        sharedPreferences.edit().putString(RestConstant.DEVICE_TOKEN, device_token).apply();
    }

    public String getDeviceId() {
        return sharedPreferences.getString(RestConstant.DEVICE_ID, "");
    }

    public void setDeviceId(String deviceId) {
        sharedPreferences.edit().putString(RestConstant.DEVICE_ID, deviceId).apply();
    }

    public String getHomID() {
        return sharedPreferences.getString(RestConstant.HOME_ID, "");
    }

    public void setHomID(String home_id) {
        sharedPreferences.edit().putString(RestConstant.HOME_ID, home_id).apply();
    }

    public String getHomeDefault() {
        return sharedPreferences.getString(RestConstant.HOME_DEFAULT, "");
    }

    public void setHomeDefault(String home_default) {
        sharedPreferences.edit().putString(RestConstant.HOME_DEFAULT, home_default).apply();
    }

    public String getHomTitle() {
        return sharedPreferences.getString(RestConstant.HOME_TITLE, "");
    }

    public void setHomTitle(String homeTitle) {
        sharedPreferences.edit().putString(RestConstant.HOME_TITLE, homeTitle).apply();
    }


}
