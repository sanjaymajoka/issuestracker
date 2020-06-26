package com.githubissuetracker.app;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private static final String DATA_FETCHED_TIME = "_Data_Fetched_time";

    private static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getSharedPreference(context).edit();
    }

    private static void saveString(Context context, String key, String value) {
        getEditor(context).putString(key, value).apply();
    }

    private static String getString(Context context, String key, String defaultValue) {
        return getSharedPreference(context).getString(key, defaultValue);
    }

    private static void saveInt(Context context, String key, int value) {
        getEditor(context).putInt(key, value).apply();
    }

    private static int getInt(Context context, String key, int defaultValue) {
        return getSharedPreference(context).getInt(key, defaultValue);
    }

    private static void saveLong(Context context, String key, long value) {
        getEditor(context).putLong(key, value).apply();
    }

    private static long getLong(Context context, String key, long defaultValue) {
        return getSharedPreference(context).getLong(key, defaultValue);
    }

    public static void saveDataFetchedTime(Context context, long time) {
        saveLong(context, DATA_FETCHED_TIME, time);
    }

    public static long getLastDataFetchedTime(Context context) {
        return getLong(context, DATA_FETCHED_TIME, 0);

    }


}
