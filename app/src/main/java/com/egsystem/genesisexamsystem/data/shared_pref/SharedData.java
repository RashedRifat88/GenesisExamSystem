package com.egsystem.genesisexamsystem.data.shared_pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedData {

    public static final String DEVICE_ID = "device_id";
    public static final String TOKEN = "token";
    public static final String PASSWORD = "password";
    public static final String USER_NAME = "user_name";
    public static final String BMDC_NO = "bmdc_no";


    public static void saveTOKEN(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(TOKEN, value);
        editor.commit();
    }

    public static String getTOKEN(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(TOKEN, null);
    }



    public static void savePASSWORD(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(PASSWORD, value);
        editor.commit();
    }

    public static String getPASSWORD(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(PASSWORD, null);
    }


    public static void saveUSER_NAME(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(USER_NAME, value);
        editor.commit();
    }

    public static String getUSER_NAME(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(USER_NAME, null);
    }


    public static void saveBMDC_NO(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(BMDC_NO, value);
        editor.commit();
    }

    public static String getBMDC_NO(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(BMDC_NO, null);
    }




}
