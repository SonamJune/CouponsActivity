package com.example.couponactivitygeminitest.utils;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;

import com.example.couponbase.R;
import com.example.couponbase.PurplleApplication;
import com.example.couponbase.preference.PreferenceUtil;
import com.example.couponbase.preference.PurpllePrefKey;
import com.example.couponbase.util.localization.LocaleManager;

import java.util.HashMap;

public class Network {


    //Error codes
    public static final int TOKEN_EXPIRED = 406;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int INVALID_TOKEN = 401;
    public static final int CLOUDFARE_RATE_LIMIT_ERROR = 429;
    public static final String SOMETHING_WENT_WRONG_MSG = "something_went_wrong";

    private static NetworkChangeReceiver mNetworkChangeReceiver;

    private static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo();
    }

    public static boolean isConnected(Context context) {
        if (context == null) return true;
        NetworkInfo networkInfo = getNetworkInfo(context);
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static void registerNetworkReceiver(TextView button, Context context) {
        if (mNetworkChangeReceiver == null) {
            mNetworkChangeReceiver = new NetworkChangeReceiver(context);
        }
        mNetworkChangeReceiver.addListener(button);
    }

    public static void unregisterNetworkReceiver(Context context) {
        if (mNetworkChangeReceiver != null) {
            context.unregisterReceiver(mNetworkChangeReceiver);
            mNetworkChangeReceiver = null;
        }
    }

    public static boolean checkForRetry(int statusCode) {
        switch (statusCode) {
            case Network.INTERNAL_SERVER_ERROR:
            case 503:
            case 504:
            case 401:
            case 0:
            case 400:
            case 404:
                return true;
            default:
                return false;
        }
    }

    public static String getStatusCodeMessage(int code, Context context) {
        String msg = "";
        switch (code) {
            case 502:
            case Network.INTERNAL_SERVER_ERROR:
            case 0:
            case 404:
            case 400:
                if (context != null) {
                    msg = context.getString(R.string.please_try_again);
                } else {
                    msg = PurplleApplication.getAppContext().getString(R.string.please_try_again);
                }
                break;
            case Network.CLOUDFARE_RATE_LIMIT_ERROR:
                if (context != null) {
                    msg = context.getString(R.string.rate_limit_msg);
                } else {
                    msg = PurplleApplication.getAppContext().getString(R.string.rate_limit_msg);
                }
                break;
        }
        return msg;
    }

    public static HashMap<String, String> getHeader(Context context, boolean isTokenReuiqred) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(context.getString(R.string.type), "android");
        headers.put(context.getString(R.string.source), "app");
        headers.put(context.getString(com.example.couponbase.R.string.version), "");
        headers.put(context.getString(R.string.device_id), PreferenceUtil.getDeviceId(context));
        headers.put(context.getString(R.string.mac_id), "");
        headers.put(context.getString(com.example.couponbase.R.string.build_number), "");
        headers.put("is_logged_in", PreferenceUtil.isUserLogin(context) ? "1" : "0");
//        if (PreferenceUtil.getReblazeHash().isEmpty()) {
//            String hash = reblaze.generateHash();
//            headers.put(reblaze.reblazeHeader, hash);
//            PreferenceUtil.setReblazeHash(hash);
//        } else {
//            headers.put(reblaze.reblazeHeader, PreferenceUtil.getReblazeHash());
//        }
        if (isTokenReuiqred) {
            headers.put(context.getString(R.string.user_id), PreferenceUtil.getUserId(context));
            headers.put(context.getString(R.string.email), PreferenceUtil.getUserEmail(context));
            headers.put(context.getString(R.string.token), "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkZXZpY2VfaWQiOiJlMjZlN2Y3NjVmN2I2MjQzIiwibW9kZV9kZXZpY2UiOiJhbmRyb2lkIiwibW9kZV9kZXZpY2VfdHlwZSI6ImFuZHJvaWQiLCJpYXQiOjE3MTc1MDQxODUsImV4cCI6MTcyNTI4MDE4NSwiYXVkIjoiYW5kcm9pZCIsImlzcyI6InRva2VubWljcm9zZXJ2aWNlIn0.597DEFiCurb-bFJVu8Ybx9csAy9Xk7B0dS6Il0Rfud4");
            headers.put(PurpllePrefKey.AUTH_CODE, PreferenceUtil.getAuthCode(context));
        }

        headers.put(context.getString(R.string.android_id), "");
        String isEmulator = "no";
            isEmulator = "yes";

        headers.put(context.getString(R.string.is_emulator), isEmulator);
        headers.put(context.getString(R.string.lang_code), LocaleManager.getLanguagePref(context));
        return headers;
    }
}
