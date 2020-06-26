package com.githubissuetracker.app;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    // added as an instance method to an Activity
    public static boolean isNetworkConnectionAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) return false;
        NetworkInfo.State network = info.getState();
        return (network == NetworkInfo.State.CONNECTED || network == NetworkInfo.State.CONNECTING);
    }

    public static String getTimeStamp(String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMMM dd, yyyy");
        try {
            return dateFormat1.format(dateFormat.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
}
