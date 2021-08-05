package com.genesis.genesisexamsystem.data.shared_pref;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedData {

    public static final String DEVICE_ID = "device_id";
    public static final String TOKEN = "token";
    public static final String PASSWORD = "password";
    public static final String USER_NAME = "user_name";
    public static final String BMDC_NO = "bmdc_no";

    public static final String SKIPPED_QUES_ID_STORED = "skipped_ques_id_stored";

    public static final String EXAM_NAME = "exam_name";
    public static final String EXAM_DURATION = "exam_duration";
    public static final String MCQ_NUMBER = "mcq_number";
    public static final String MCQ_MARK = "mcq_mark";
    public static final String MCQ_NEGATIVE_MARK = "mcq_negative_mark";
    public static final String SBA_NUMBER = "sba_number";
    public static final String SBA_MARK = "sba_mark";
    public static final String SBA_NEGATIVE_MARK = "sba_negative_mark";


    public static void saveTOKEN(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(TOKEN, value);
        editor.commit();
    }

    public static String getTOKEN(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(TOKEN, null);
    }

    ///
    public static void saveEXAM_NAME(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(EXAM_NAME, value);
        editor.commit();
    }

    public static String getEXAM_NAME(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(EXAM_NAME, null);
    }

    public static void saveEXAM_DURATION(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(EXAM_DURATION, value);
        editor.commit();
    }

    public static String getEXAM_DURATION(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(EXAM_DURATION, null);
    }


    public static void saveMCQ_NUMBER(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(MCQ_NUMBER, value);
        editor.commit();
    }

    public static String getMCQ_NUMBER(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(MCQ_NUMBER, null);
    }

    public static void saveMCQ_MARK(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(MCQ_MARK, value);
        editor.commit();
    }

    public static String getMCQ_MARK(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(MCQ_MARK, null);
    }

    public static void saveMCQ_NEGATIVE_MARK(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(MCQ_NEGATIVE_MARK, value);
        editor.commit();
    }

    public static String getMCQ_NEGATIVE_MARK(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(MCQ_NEGATIVE_MARK, null);
    }

    public static void saveSBA_NUMBER(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(SBA_NUMBER, value);
        editor.commit();
    }

    public static String getSBA_NUMBER(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(SBA_NUMBER, null);
    }

    public static void saveSBA_MARK(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(SBA_MARK, value);
        editor.commit();
    }

    public static String getSBA_MARK(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(SBA_MARK, null);
    }

    public static void saveSBA_NEGATIVE_MARK(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(SBA_NEGATIVE_MARK, value);
        editor.commit();
    }

    public static String getSBA_NEGATIVE_MARK(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(SBA_NEGATIVE_MARK, null);
    }

    ///




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



    public static void saveSKIPPED_QUES_ID_STORED(Context context, String value) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(SKIPPED_QUES_ID_STORED, value);
        editor.commit();
    }

    public static String getSKIPPED_QUES_ID_STORED(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(SKIPPED_QUES_ID_STORED, "0");
    }






}
